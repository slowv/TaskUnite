package org.t1708e.taskunite.web.rest;

import org.t1708e.taskunite.TaskUniteApp;
import org.t1708e.taskunite.domain.UserExtend;
import org.t1708e.taskunite.repository.UserExtendRepository;
import org.t1708e.taskunite.service.UserExtendService;
import org.t1708e.taskunite.service.dto.UserExtendDTO;
import org.t1708e.taskunite.service.mapper.UserExtendMapper;
import org.t1708e.taskunite.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.t1708e.taskunite.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserExtendResource} REST controller.
 */
@SpringBootTest(classes = TaskUniteApp.class)
public class UserExtendResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private UserExtendRepository userExtendRepository;

    @Autowired
    private UserExtendMapper userExtendMapper;

    @Autowired
    private UserExtendService userExtendService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUserExtendMockMvc;

    private UserExtend userExtend;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserExtendResource userExtendResource = new UserExtendResource(userExtendService);
        this.restUserExtendMockMvc = MockMvcBuilders.standaloneSetup(userExtendResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExtend createEntity(EntityManager em) {
        UserExtend userExtend = new UserExtend()
            .name(DEFAULT_NAME);
        return userExtend;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExtend createUpdatedEntity(EntityManager em) {
        UserExtend userExtend = new UserExtend()
            .name(UPDATED_NAME);
        return userExtend;
    }

    @BeforeEach
    public void initTest() {
        userExtend = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserExtend() throws Exception {
        int databaseSizeBeforeCreate = userExtendRepository.findAll().size();

        // Create the UserExtend
        UserExtendDTO userExtendDTO = userExtendMapper.toDto(userExtend);
        restUserExtendMockMvc.perform(post("/api/user-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtendDTO)))
            .andExpect(status().isCreated());

        // Validate the UserExtend in the database
        List<UserExtend> userExtendList = userExtendRepository.findAll();
        assertThat(userExtendList).hasSize(databaseSizeBeforeCreate + 1);
        UserExtend testUserExtend = userExtendList.get(userExtendList.size() - 1);
        assertThat(testUserExtend.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createUserExtendWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userExtendRepository.findAll().size();

        // Create the UserExtend with an existing ID
        userExtend.setId(1L);
        UserExtendDTO userExtendDTO = userExtendMapper.toDto(userExtend);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserExtendMockMvc.perform(post("/api/user-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtendDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserExtend in the database
        List<UserExtend> userExtendList = userExtendRepository.findAll();
        assertThat(userExtendList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserExtends() throws Exception {
        // Initialize the database
        userExtendRepository.saveAndFlush(userExtend);

        // Get all the userExtendList
        restUserExtendMockMvc.perform(get("/api/user-extends?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userExtend.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getUserExtend() throws Exception {
        // Initialize the database
        userExtendRepository.saveAndFlush(userExtend);

        // Get the userExtend
        restUserExtendMockMvc.perform(get("/api/user-extends/{id}", userExtend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userExtend.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserExtend() throws Exception {
        // Get the userExtend
        restUserExtendMockMvc.perform(get("/api/user-extends/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserExtend() throws Exception {
        // Initialize the database
        userExtendRepository.saveAndFlush(userExtend);

        int databaseSizeBeforeUpdate = userExtendRepository.findAll().size();

        // Update the userExtend
        UserExtend updatedUserExtend = userExtendRepository.findById(userExtend.getId()).get();
        // Disconnect from session so that the updates on updatedUserExtend are not directly saved in db
        em.detach(updatedUserExtend);
        updatedUserExtend
            .name(UPDATED_NAME);
        UserExtendDTO userExtendDTO = userExtendMapper.toDto(updatedUserExtend);

        restUserExtendMockMvc.perform(put("/api/user-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtendDTO)))
            .andExpect(status().isOk());

        // Validate the UserExtend in the database
        List<UserExtend> userExtendList = userExtendRepository.findAll();
        assertThat(userExtendList).hasSize(databaseSizeBeforeUpdate);
        UserExtend testUserExtend = userExtendList.get(userExtendList.size() - 1);
        assertThat(testUserExtend.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingUserExtend() throws Exception {
        int databaseSizeBeforeUpdate = userExtendRepository.findAll().size();

        // Create the UserExtend
        UserExtendDTO userExtendDTO = userExtendMapper.toDto(userExtend);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserExtendMockMvc.perform(put("/api/user-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtendDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserExtend in the database
        List<UserExtend> userExtendList = userExtendRepository.findAll();
        assertThat(userExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserExtend() throws Exception {
        // Initialize the database
        userExtendRepository.saveAndFlush(userExtend);

        int databaseSizeBeforeDelete = userExtendRepository.findAll().size();

        // Delete the userExtend
        restUserExtendMockMvc.perform(delete("/api/user-extends/{id}", userExtend.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserExtend> userExtendList = userExtendRepository.findAll();
        assertThat(userExtendList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExtend.class);
        UserExtend userExtend1 = new UserExtend();
        userExtend1.setId(1L);
        UserExtend userExtend2 = new UserExtend();
        userExtend2.setId(userExtend1.getId());
        assertThat(userExtend1).isEqualTo(userExtend2);
        userExtend2.setId(2L);
        assertThat(userExtend1).isNotEqualTo(userExtend2);
        userExtend1.setId(null);
        assertThat(userExtend1).isNotEqualTo(userExtend2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExtendDTO.class);
        UserExtendDTO userExtendDTO1 = new UserExtendDTO();
        userExtendDTO1.setId(1L);
        UserExtendDTO userExtendDTO2 = new UserExtendDTO();
        assertThat(userExtendDTO1).isNotEqualTo(userExtendDTO2);
        userExtendDTO2.setId(userExtendDTO1.getId());
        assertThat(userExtendDTO1).isEqualTo(userExtendDTO2);
        userExtendDTO2.setId(2L);
        assertThat(userExtendDTO1).isNotEqualTo(userExtendDTO2);
        userExtendDTO1.setId(null);
        assertThat(userExtendDTO1).isNotEqualTo(userExtendDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userExtendMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userExtendMapper.fromId(null)).isNull();
    }
}
