package com.vbtn.taskunite.service;

import com.vbtn.taskunite.service.dto.TaskerCategoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vbtn.taskunite.domain.TaskerCategory}.
 */
public interface TaskerCategoryService {

    /**
     * Save a taskerCategory.
     *
     * @param taskerCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    TaskerCategoryDTO save(TaskerCategoryDTO taskerCategoryDTO);

    /**
     * Get all the taskerCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskerCategoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" taskerCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskerCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" taskerCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
