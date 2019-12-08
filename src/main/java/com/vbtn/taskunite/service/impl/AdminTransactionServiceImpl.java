package com.vbtn.taskunite.service.impl;

import com.vbtn.taskunite.service.AdminTransactionService;
import com.vbtn.taskunite.domain.AdminTransaction;
import com.vbtn.taskunite.repository.AdminTransactionRepository;
import com.vbtn.taskunite.service.dto.AdminTransactionDTO;
import com.vbtn.taskunite.service.mapper.AdminTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdminTransaction}.
 */
@Service
@Transactional
public class AdminTransactionServiceImpl implements AdminTransactionService {

    private final Logger log = LoggerFactory.getLogger(AdminTransactionServiceImpl.class);

    private final AdminTransactionRepository adminTransactionRepository;

    private final AdminTransactionMapper adminTransactionMapper;

    public AdminTransactionServiceImpl(AdminTransactionRepository adminTransactionRepository, AdminTransactionMapper adminTransactionMapper) {
        this.adminTransactionRepository = adminTransactionRepository;
        this.adminTransactionMapper = adminTransactionMapper;
    }

    /**
     * Save a adminTransaction.
     *
     * @param adminTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdminTransactionDTO save(AdminTransactionDTO adminTransactionDTO) {
        log.debug("Request to save AdminTransaction : {}", adminTransactionDTO);
        AdminTransaction adminTransaction = adminTransactionMapper.toEntity(adminTransactionDTO);
        adminTransaction = adminTransactionRepository.save(adminTransaction);
        return adminTransactionMapper.toDto(adminTransaction);
    }

    /**
     * Get all the adminTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdminTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdminTransactions");
        return adminTransactionRepository.findAll(pageable)
            .map(adminTransactionMapper::toDto);
    }


    /**
     * Get one adminTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdminTransactionDTO> findOne(Long id) {
        log.debug("Request to get AdminTransaction : {}", id);
        return adminTransactionRepository.findById(id)
            .map(adminTransactionMapper::toDto);
    }

    /**
     * Delete the adminTransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdminTransaction : {}", id);
        adminTransactionRepository.deleteById(id);
    }
}
