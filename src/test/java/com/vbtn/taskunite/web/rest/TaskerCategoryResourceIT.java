package com.vbtn.taskunite.web.rest;

import com.vbtn.taskunite.TaskUniteApp;
import com.vbtn.taskunite.domain.TaskerCategory;
import com.vbtn.taskunite.repository.TaskerCategoryRepository;
import com.vbtn.taskunite.service.TaskerCategoryService;
import com.vbtn.taskunite.service.dto.TaskerCategoryDTO;
import com.vbtn.taskunite.service.mapper.TaskerCategoryMapper;
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
 * Integration tests for the {@link TaskerCategoryResource} REST controller.
 */
@SpringBootTest(classes = TaskUniteApp.class)
public class TaskerCategoryResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELETED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELETED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TaskerCategoryRepository taskerCategoryRepository;

    @Autowired
    private TaskerCategoryMapper taskerCategoryMapper;

    @Autowired
    private TaskerCategoryService taskerCategoryService;

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

    private MockMvc restTaskerCategoryMockMvc;

    private TaskerCategory taskerCategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskerCategoryResource taskerCategoryResource = new TaskerCategoryResource(taskerCategoryService);
        this.restTaskerCategoryMockMvc = MockMvcBuilders.standaloneSetup(taskerCategoryResource)
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
    public static TaskerCategory createEntity(EntityManager em) {
        TaskerCategory taskerCategory = new TaskerCategory()
            .description(DEFAULT_DESCRIPTION)
            .price(DEFAULT_PRICE)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .deletedAt(DEFAULT_DELETED_AT);
        return taskerCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskerCategory createUpdatedEntity(EntityManager em) {
        TaskerCategory taskerCategory = new TaskerCategory()
            .description(UPDATED_DESCRIPTION)
            .price(UPDATED_PRICE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        return taskerCategory;
    }

    @BeforeEach
    public void initTest() {
        taskerCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaskerCategory() throws Exception {
        int databaseSizeBeforeCreate = taskerCategoryRepository.findAll().size();

        // Create the TaskerCategory
        TaskerCategoryDTO taskerCategoryDTO = taskerCategoryMapper.toDto(taskerCategory);
        restTaskerCategoryMockMvc.perform(post("/api/tasker-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskerCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the TaskerCategory in the database
        List<TaskerCategory> taskerCategoryList = taskerCategoryRepository.findAll();
        assertThat(taskerCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        TaskerCategory testTaskerCategory = taskerCategoryList.get(taskerCategoryList.size() - 1);
        assertThat(testTaskerCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTaskerCategory.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testTaskerCategory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskerCategory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTaskerCategory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testTaskerCategory.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
    }

    @Test
    @Transactional
    public void createTaskerCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taskerCategoryRepository.findAll().size();

        // Create the TaskerCategory with an existing ID
        taskerCategory.setId(1L);
        TaskerCategoryDTO taskerCategoryDTO = taskerCategoryMapper.toDto(taskerCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskerCategoryMockMvc.perform(post("/api/tasker-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskerCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskerCategory in the database
        List<TaskerCategory> taskerCategoryList = taskerCategoryRepository.findAll();
        assertThat(taskerCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTaskerCategories() throws Exception {
        // Initialize the database
        taskerCategoryRepository.saveAndFlush(taskerCategory);

        // Get all the taskerCategoryList
        restTaskerCategoryMockMvc.perform(get("/api/tasker-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskerCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].deletedAt").value(hasItem(DEFAULT_DELETED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getTaskerCategory() throws Exception {
        // Initialize the database
        taskerCategoryRepository.saveAndFlush(taskerCategory);

        // Get the taskerCategory
        restTaskerCategoryMockMvc.perform(get("/api/tasker-categories/{id}", taskerCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taskerCategory.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.deletedAt").value(DEFAULT_DELETED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTaskerCategory() throws Exception {
        // Get the taskerCategory
        restTaskerCategoryMockMvc.perform(get("/api/tasker-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaskerCategory() throws Exception {
        // Initialize the database
        taskerCategoryRepository.saveAndFlush(taskerCategory);

        int databaseSizeBeforeUpdate = taskerCategoryRepository.findAll().size();

        // Update the taskerCategory
        TaskerCategory updatedTaskerCategory = taskerCategoryRepository.findById(taskerCategory.getId()).get();
        // Disconnect from session so that the updates on updatedTaskerCategory are not directly saved in db
        em.detach(updatedTaskerCategory);
        updatedTaskerCategory
            .description(UPDATED_DESCRIPTION)
            .price(UPDATED_PRICE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT);
        TaskerCategoryDTO taskerCategoryDTO = taskerCategoryMapper.toDto(updatedTaskerCategory);

        restTaskerCategoryMockMvc.perform(put("/api/tasker-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskerCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the TaskerCategory in the database
        List<TaskerCategory> taskerCategoryList = taskerCategoryRepository.findAll();
        assertThat(taskerCategoryList).hasSize(databaseSizeBeforeUpdate);
        TaskerCategory testTaskerCategory = taskerCategoryList.get(taskerCategoryList.size() - 1);
        assertThat(testTaskerCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTaskerCategory.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testTaskerCategory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskerCategory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTaskerCategory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testTaskerCategory.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingTaskerCategory() throws Exception {
        int databaseSizeBeforeUpdate = taskerCategoryRepository.findAll().size();

        // Create the TaskerCategory
        TaskerCategoryDTO taskerCategoryDTO = taskerCategoryMapper.toDto(taskerCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskerCategoryMockMvc.perform(put("/api/tasker-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskerCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskerCategory in the database
        List<TaskerCategory> taskerCategoryList = taskerCategoryRepository.findAll();
        assertThat(taskerCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaskerCategory() throws Exception {
        // Initialize the database
        taskerCategoryRepository.saveAndFlush(taskerCategory);

        int databaseSizeBeforeDelete = taskerCategoryRepository.findAll().size();

        // Delete the taskerCategory
        restTaskerCategoryMockMvc.perform(delete("/api/tasker-categories/{id}", taskerCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskerCategory> taskerCategoryList = taskerCategoryRepository.findAll();
        assertThat(taskerCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
