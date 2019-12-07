package com.vbtn.taskunite.service;

import com.vbtn.taskunite.service.dto.AdminProfitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vbtn.taskunite.domain.AdminProfit}.
 */
public interface AdminProfitService {

    /**
     * Save a adminProfit.
     *
     * @param adminProfitDTO the entity to save.
     * @return the persisted entity.
     */
    AdminProfitDTO save(AdminProfitDTO adminProfitDTO);

    /**
     * Get all the adminProfits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdminProfitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adminProfit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdminProfitDTO> findOne(Long id);

    /**
     * Delete the "id" adminProfit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
