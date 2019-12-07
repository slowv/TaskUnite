package com.vbtn.taskunite.service.impl;

import com.vbtn.taskunite.service.TaskerCategoryService;
import com.vbtn.taskunite.domain.TaskerCategory;
import com.vbtn.taskunite.repository.TaskerCategoryRepository;
import com.vbtn.taskunite.service.dto.TaskerCategoryDTO;
import com.vbtn.taskunite.service.mapper.TaskerCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TaskerCategory}.
 */
@Service
@Transactional
public class TaskerCategoryServiceImpl implements TaskerCategoryService {

    private final Logger log = LoggerFactory.getLogger(TaskerCategoryServiceImpl.class);

    private final TaskerCategoryRepository taskerCategoryRepository;

    private final TaskerCategoryMapper taskerCategoryMapper;

    public TaskerCategoryServiceImpl(TaskerCategoryRepository taskerCategoryRepository, TaskerCategoryMapper taskerCategoryMapper) {
        this.taskerCategoryRepository = taskerCategoryRepository;
        this.taskerCategoryMapper = taskerCategoryMapper;
    }

    /**
     * Save a taskerCategory.
     *
     * @param taskerCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TaskerCategoryDTO save(TaskerCategoryDTO taskerCategoryDTO) {
        log.debug("Request to save TaskerCategory : {}", taskerCategoryDTO);
        TaskerCategory taskerCategory = taskerCategoryMapper.toEntity(taskerCategoryDTO);
        taskerCategory = taskerCategoryRepository.save(taskerCategory);
        return taskerCategoryMapper.toDto(taskerCategory);
    }

    /**
     * Get all the taskerCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskerCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskerCategories");
        return taskerCategoryRepository.findAll(pageable)
            .map(taskerCategoryMapper::toDto);
    }


    /**
     * Get one taskerCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TaskerCategoryDTO> findOne(Long id) {
        log.debug("Request to get TaskerCategory : {}", id);
        return taskerCategoryRepository.findById(id)
            .map(taskerCategoryMapper::toDto);
    }

    /**
     * Delete the taskerCategory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskerCategory : {}", id);
        taskerCategoryRepository.deleteById(id);
    }
}
