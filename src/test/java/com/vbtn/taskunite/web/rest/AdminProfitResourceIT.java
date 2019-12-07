package com.vbtn.taskunite.web.rest;

import com.vbtn.taskunite.TaskUniteApp;
import com.vbtn.taskunite.domain.AdminProfit;
import com.vbtn.taskunite.repository.AdminProfitRepository;
import com.vbtn.taskunite.service.AdminProfitService;
import com.vbtn.taskunite.service.dto.AdminProfitDTO;
import com.vbtn.taskunite.service.mapper.AdminProfitMapper;
import com.vbtn.taskunite.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.vbtn.taskunite.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdminProfitResource} REST controller.
 */
@SpringBootTest(classes = TaskUniteApp.class)
public class AdminProfitResourceIT {

    private static final Double DEFAULT_TASKER_PROFIT = 1D;
    private static final Double UPDATED_TASKER_PROFIT = 2D;

    private static final Double DEFAULT_MASTER_PROFIT = 1D;
    private static final Double UPDATED_MASTER_PROFIT = 2D;

    private static final Double DEFAULT_TOTAL_PROFIT = 1D;
    private static final Double UPDATED_TOTAL_PROFIT = 2D;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELETED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELETED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AdminProfitRepository adminProfitRepository;

    @Autowired
    private AdminProfitMapper adminProfitMapper;

    @Autowired
    private AdminProfitService adminProfitService;

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

    private MockMvc restAdminProfitMockMvc;

    private AdminProfit adminProfit;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdminProfitResource adminProfitResource = new AdminProfitResource(adminProfitService);
        this.restAdminProfitMockMvc = MockMvcBuilders.standaloneSetup(adminProfitResource)
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
    public static AdminProfit createEntity(EntityManager em) {
        AdminProfit adminProfit = new AdminProfit()
            .taskerProfit(DEFAULT_TASKER_PROFIT)
            .masterProfit(DEFAULT_MASTER_PROFIT)
            .totalProfit(DEFAULT_TOTAL_PROFIT)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .deletedAt(DEFAULT_DELETED_AT);
        return adminProfit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminProfit createUpdatedEntity(EntityManager em) {
        AdminProfit adminProfit = new AdminProfit()
            .taskerProfit(UPDATED_TASKER_PROFIT)
            .masterProfit(UPDATED_MASTER_PROFIT)
            .totalProfit(UPDATED_TOTAL_PROFIT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        return adminProfit;
    }

    @BeforeEach
    public void initTest() {
        adminProfit = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminProfit() throws Exception {
        int databaseSizeBeforeCreate = adminProfitRepository.findAll().size();

        // Create the AdminProfit
        AdminProfitDTO adminProfitDTO = adminProfitMapper.toDto(adminProfit);
        restAdminProfitMockMvc.perform(post("/api/admin-profits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminProfitDTO)))
            .andExpect(status().isCreated());

        // Validate the AdminProfit in the database
        List<AdminProfit> adminProfitList = adminProfitRepository.findAll();
        assertThat(adminProfitList).hasSize(databaseSizeBeforeCreate + 1);
        AdminProfit testAdminProfit = adminProfitList.get(adminProfitList.size() - 1);
        assertThat(testAdminProfit.getTaskerProfit()).isEqualTo(DEFAULT_TASKER_PROFIT);
        assertThat(testAdminProfit.getMasterProfit()).isEqualTo(DEFAULT_MASTER_PROFIT);
        assertThat(testAdminProfit.getTotalProfit()).isEqualTo(DEFAULT_TOTAL_PROFIT);
        assertThat(testAdminProfit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAdminProfit.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAdminProfit.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
    }

    @Test
    @Transactional
    public void createAdminProfitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminProfitRepository.findAll().size();

        // Create the AdminProfit with an existing ID
        adminProfit.setId(1L);
        AdminProfitDTO adminProfitDTO = adminProfitMapper.toDto(adminProfit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminProfitMockMvc.perform(post("/api/admin-profits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminProfitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminProfit in the database
        List<AdminProfit> adminProfitList = adminProfitRepository.findAll();
        assertThat(adminProfitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdminProfits() throws Exception {
        // Initialize the database
        adminProfitRepository.saveAndFlush(adminProfit);

        // Get all the adminProfitList
        restAdminProfitMockMvc.perform(get("/api/admin-profits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminProfit.getId().intValue())))
            .andExpect(jsonPath("$.[*].taskerProfit").value(hasItem(DEFAULT_TASKER_PROFIT.doubleValue())))
            .andExpect(jsonPath("$.[*].masterProfit").value(hasItem(DEFAULT_MASTER_PROFIT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalProfit").value(hasItem(DEFAULT_TOTAL_PROFIT.doubleValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].deletedAt").value(hasItem(DEFAULT_DELETED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getAdminProfit() throws Exception {
        // Initialize the database
        adminProfitRepository.saveAndFlush(adminProfit);

        // Get the adminProfit
        restAdminProfitMockMvc.perform(get("/api/admin-profits/{id}", adminProfit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adminProfit.getId().intValue()))
            .andExpect(jsonPath("$.taskerProfit").value(DEFAULT_TASKER_PROFIT.doubleValue()))
            .andExpect(jsonPath("$.masterProfit").value(DEFAULT_MASTER_PROFIT.doubleValue()))
            .andExpect(jsonPath("$.totalProfit").value(DEFAULT_TOTAL_PROFIT.doubleValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.deletedAt").value(DEFAULT_DELETED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdminProfit() throws Exception {
        // Get the adminProfit
        restAdminProfitMockMvc.perform(get("/api/admin-profits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminProfit() throws Exception {
        // Initialize the database
        adminProfitRepository.saveAndFlush(adminProfit);

        int databaseSizeBeforeUpdate = adminProfitRepository.findAll().size();

        // Update the adminProfit
        AdminProfit updatedAdminProfit = adminProfitRepository.findById(adminProfit.getId()).get();
        // Disconnect from session so that the updates on updatedAdminProfit are not directly saved in db
        em.detach(updatedAdminProfit);
        updatedAdminProfit
            .taskerProfit(UPDATED_TASKER_PROFIT)
            .masterProfit(UPDATED_MASTER_PROFIT)
            .totalProfit(UPDATED_TOTAL_PROFIT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        AdminProfitDTO adminProfitDTO = adminProfitMapper.toDto(updatedAdminProfit);

        restAdminProfitMockMvc.perform(put("/api/admin-profits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminProfitDTO)))
            .andExpect(status().isOk());

        // Validate the AdminProfit in the database
        List<AdminProfit> adminProfitList = adminProfitRepository.findAll();
        assertThat(adminProfitList).hasSize(databaseSizeBeforeUpdate);
        AdminProfit testAdminProfit = adminProfitList.get(adminProfitList.size() - 1);
        assertThat(testAdminProfit.getTaskerProfit()).isEqualTo(UPDATED_TASKER_PROFIT);
        assertThat(testAdminProfit.getMasterProfit()).isEqualTo(UPDATED_MASTER_PROFIT);
        assertThat(testAdminProfit.getTotalProfit()).isEqualTo(UPDATED_TOTAL_PROFIT);
        assertThat(testAdminProfit.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAdminProfit.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAdminProfit.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminProfit() throws Exception {
        int databaseSizeBeforeUpdate = adminProfitRepository.findAll().size();

        // Create the AdminProfit
        AdminProfitDTO adminProfitDTO = adminProfitMapper.toDto(adminProfit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminProfitMockMvc.perform(put("/api/admin-profits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminProfitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminProfit in the database
        List<AdminProfit> adminProfitList = adminProfitRepository.findAll();
        assertThat(adminProfitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdminProfit() throws Exception {
        // Initialize the database
        adminProfitRepository.saveAndFlush(adminProfit);

        int databaseSizeBeforeDelete = adminProfitRepository.findAll().size();

        // Delete the adminProfit
        restAdminProfitMockMvc.perform(delete("/api/admin-profits/{id}", adminProfit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminProfit> adminProfitList = adminProfitRepository.findAll();
        assertThat(adminProfitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
