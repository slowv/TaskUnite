package com.vbtn.taskunite.service;

import com.vbtn.taskunite.service.dto.AdminTransactionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vbtn.taskunite.domain.AdminTransaction}.
 */
public interface AdminTransactionService {

    /**
     * Save a adminTransaction.
     *
     * @param adminTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    AdminTransactionDTO save(AdminTransactionDTO adminTransactionDTO);

    /**
     * Get all the adminTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdminTransactionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adminTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdminTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" adminTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
