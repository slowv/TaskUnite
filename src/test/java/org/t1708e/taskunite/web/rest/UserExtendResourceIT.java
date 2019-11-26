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
import java.time.LocalDate;
import java.time.ZoneId;
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

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DELETED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELETED_AT = LocalDate.now(ZoneId.systemDefault());

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
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .deletedAt(DEFAULT_DELETED_AT);
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
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
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
        assertThat(testUserExtend.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testUserExtend.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserExtend.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserExtend.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testUserExtend.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testUserExtend.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
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
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].deletedAt").value(hasItem(DEFAULT_DELETED_AT.toString())));
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
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.deletedAt").value(DEFAULT_DELETED_AT.toString()));
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
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        UserExtendDTO userExtendDTO = userExtendMapper.toDto(updatedUserExtend);

        restUserExtendMockMvc.perform(put("/api/user-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtendDTO)))
            .andExpect(status().isOk());

        // Validate the UserExtend in the database
        List<UserExtend> userExtendList = userExtendRepository.findAll();
        assertThat(userExtendList).hasSize(databaseSizeBeforeUpdate);
        UserExtend testUserExtend = userExtendList.get(userExtendList.size() - 1);
        assertThat(testUserExtend.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testUserExtend.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserExtend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserExtend.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testUserExtend.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testUserExtend.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
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
}
