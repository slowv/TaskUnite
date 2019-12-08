package com.vbtn.taskunite.service.impl;

import com.vbtn.taskunite.service.AdminProfitService;
import com.vbtn.taskunite.domain.AdminProfit;
import com.vbtn.taskunite.repository.AdminProfitRepository;
import com.vbtn.taskunite.service.dto.AdminProfitDTO;
import com.vbtn.taskunite.service.mapper.AdminProfitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdminProfit}.
 */
@Service
@Transactional
public class AdminProfitServiceImpl implements AdminProfitService {

    private final Logger log = LoggerFactory.getLogger(AdminProfitServiceImpl.class);

    private final AdminProfitRepository adminProfitRepository;

    private final AdminProfitMapper adminProfitMapper;

    public AdminProfitServiceImpl(AdminProfitRepository adminProfitRepository, AdminProfitMapper adminProfitMapper) {
        this.adminProfitRepository = adminProfitRepository;
        this.adminProfitMapper = adminProfitMapper;
    }

    /**
     * Save a adminProfit.
     *
     * @param adminProfitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdminProfitDTO save(AdminProfitDTO adminProfitDTO) {
        log.debug("Request to save AdminProfit : {}", adminProfitDTO);
        AdminProfit adminProfit = adminProfitMapper.toEntity(adminProfitDTO);
        adminProfit = adminProfitRepository.save(adminProfit);
        return adminProfitMapper.toDto(adminProfit);
    }

    /**
     * Get all the adminProfits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdminProfitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdminProfits");
        return adminProfitRepository.findAll(pageable)
            .map(adminProfitMapper::toDto);
    }


    /**
     * Get one adminProfit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdminProfitDTO> findOne(Long id) {
        log.debug("Request to get AdminProfit : {}", id);
        return adminProfitRepository.findById(id)
            .map(adminProfitMapper::toDto);
    }

    /**
     * Delete the adminProfit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdminProfit : {}", id);
        adminProfitRepository.deleteById(id);
    }
}
