package com.vbtn.taskunite.web.rest;

import com.vbtn.taskunite.TaskUniteApp;
import com.vbtn.taskunite.domain.AdminTransaction;
import com.vbtn.taskunite.repository.AdminTransactionRepository;
import com.vbtn.taskunite.service.AdminTransactionService;
import com.vbtn.taskunite.service.dto.AdminTransactionDTO;
import com.vbtn.taskunite.service.mapper.AdminTransactionMapper;
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
 * Integration tests for the {@link AdminTransactionResource} REST controller.
 */
@SpringBootTest(classes = TaskUniteApp.class)
public class AdminTransactionResourceIT {

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELETED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELETED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AdminTransactionRepository adminTransactionRepository;

    @Autowired
    private AdminTransactionMapper adminTransactionMapper;

    @Autowired
    private AdminTransactionService adminTransactionService;

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

    private MockMvc restAdminTransactionMockMvc;

    private AdminTransaction adminTransaction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdminTransactionResource adminTransactionResource = new AdminTransactionResource(adminTransactionService);
        this.restAdminTransactionMockMvc = MockMvcBuilders.standaloneSetup(adminTransactionResource)
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
    public static AdminTransaction createEntity(EntityManager em) {
        AdminTransaction adminTransaction = new AdminTransaction()
            .amount(DEFAULT_AMOUNT)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .deletedAt(DEFAULT_DELETED_AT);
        return adminTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminTransaction createUpdatedEntity(EntityManager em) {
        AdminTransaction adminTransaction = new AdminTransaction()
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        return adminTransaction;
    }

    @BeforeEach
    public void initTest() {
        adminTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminTransaction() throws Exception {
        int databaseSizeBeforeCreate = adminTransactionRepository.findAll().size();

        // Create the AdminTransaction
        AdminTransactionDTO adminTransactionDTO = adminTransactionMapper.toDto(adminTransaction);
        restAdminTransactionMockMvc.perform(post("/api/admin-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the AdminTransaction in the database
        List<AdminTransaction> adminTransactionList = adminTransactionRepository.findAll();
        assertThat(adminTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        AdminTransaction testAdminTransaction = adminTransactionList.get(adminTransactionList.size() - 1);
        assertThat(testAdminTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAdminTransaction.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAdminTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdminTransaction.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAdminTransaction.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAdminTransaction.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
    }

    @Test
    @Transactional
    public void createAdminTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminTransactionRepository.findAll().size();

        // Create the AdminTransaction with an existing ID
        adminTransaction.setId(1L);
        AdminTransactionDTO adminTransactionDTO = adminTransactionMapper.toDto(adminTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminTransactionMockMvc.perform(post("/api/admin-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminTransaction in the database
        List<AdminTransaction> adminTransactionList = adminTransactionRepository.findAll();
        assertThat(adminTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdminTransactions() throws Exception {
        // Initialize the database
        adminTransactionRepository.saveAndFlush(adminTransaction);

        // Get all the adminTransactionList
        restAdminTransactionMockMvc.perform(get("/api/admin-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].deletedAt").value(hasItem(DEFAULT_DELETED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getAdminTransaction() throws Exception {
        // Initialize the database
        adminTransactionRepository.saveAndFlush(adminTransaction);

        // Get the adminTransaction
        restAdminTransactionMockMvc.perform(get("/api/admin-transactions/{id}", adminTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adminTransaction.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.deletedAt").value(DEFAULT_DELETED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdminTransaction() throws Exception {
        // Get the adminTransaction
        restAdminTransactionMockMvc.perform(get("/api/admin-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminTransaction() throws Exception {
        // Initialize the database
        adminTransactionRepository.saveAndFlush(adminTransaction);

        int databaseSizeBeforeUpdate = adminTransactionRepository.findAll().size();

        // Update the adminTransaction
        AdminTransaction updatedAdminTransaction = adminTransactionRepository.findById(adminTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedAdminTransaction are not directly saved in db
        em.detach(updatedAdminTransaction);
        updatedAdminTransaction
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        AdminTransactionDTO adminTransactionDTO = adminTransactionMapper.toDto(updatedAdminTransaction);

        restAdminTransactionMockMvc.perform(put("/api/admin-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the AdminTransaction in the database
        List<AdminTransaction> adminTransactionList = adminTransactionRepository.findAll();
        assertThat(adminTransactionList).hasSize(databaseSizeBeforeUpdate);
        AdminTransaction testAdminTransaction = adminTransactionList.get(adminTransactionList.size() - 1);
        assertThat(testAdminTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAdminTransaction.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAdminTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdminTransaction.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAdminTransaction.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAdminTransaction.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminTransaction() throws Exception {
        int databaseSizeBeforeUpdate = adminTransactionRepository.findAll().size();

        // Create the AdminTransaction
        AdminTransactionDTO adminTransactionDTO = adminTransactionMapper.toDto(adminTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminTransactionMockMvc.perform(put("/api/admin-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminTransaction in the database
        List<AdminTransaction> adminTransactionList = adminTransactionRepository.findAll();
        assertThat(adminTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdminTransaction() throws Exception {
        // Initialize the database
        adminTransactionRepository.saveAndFlush(adminTransaction);

        int databaseSizeBeforeDelete = adminTransactionRepository.findAll().size();

        // Delete the adminTransaction
        restAdminTransactionMockMvc.perform(delete("/api/admin-transactions/{id}", adminTransaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminTransaction> adminTransactionList = adminTransactionRepository.findAll();
        assertThat(adminTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
