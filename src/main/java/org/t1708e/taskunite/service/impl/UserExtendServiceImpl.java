package org.t1708e.taskunite.service.impl;

import org.t1708e.taskunite.service.UserExtendService;
import org.t1708e.taskunite.domain.UserExtend;
import org.t1708e.taskunite.repository.UserExtendRepository;
import org.t1708e.taskunite.service.dto.UserExtendDTO;
import org.t1708e.taskunite.service.mapper.UserExtendMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link UserExtend}.
 */
@Service
@Transactional
public class UserExtendServiceImpl implements UserExtendService {

    private final Logger log = LoggerFactory.getLogger(UserExtendServiceImpl.class);

    private final UserExtendRepository userExtendRepository;

    private final UserExtendMapper userExtendMapper;

    public UserExtendServiceImpl(UserExtendRepository userExtendRepository, UserExtendMapper userExtendMapper) {
        this.userExtendRepository = userExtendRepository;
        this.userExtendMapper = userExtendMapper;
    }

    /**
     * Save a userExtend.
     *
     * @param userExtendDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserExtendDTO save(UserExtendDTO userExtendDTO) {
        log.debug("Request to save UserExtend : {}", userExtendDTO);
        UserExtend userExtend = userExtendMapper.toEntity(userExtendDTO);
        userExtend = userExtendRepository.save(userExtend);
        return userExtendMapper.toDto(userExtend);
    }

    /**
     * Get all the userExtends.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserExtendDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserExtends");
        return userExtendRepository.findAll(pageable)
            .map(userExtendMapper::toDto);
    }



    /**
    *  Get all the userExtends where Tasker is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<UserExtendDTO> findAllWhereTaskerIsNull() {
        log.debug("Request to get all userExtends where Tasker is null");
        return StreamSupport
            .stream(userExtendRepository.findAll().spliterator(), false)
            .filter(userExtend -> userExtend.getTasker() == null)
            .map(userExtendMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the userExtends where Master is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<UserExtendDTO> findAllWhereMasterIsNull() {
        log.debug("Request to get all userExtends where Master is null");
        return StreamSupport
            .stream(userExtendRepository.findAll().spliterator(), false)
            .filter(userExtend -> userExtend.getMaster() == null)
            .map(userExtendMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the userExtends where Address is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<UserExtendDTO> findAllWhereAddressIsNull() {
        log.debug("Request to get all userExtends where Address is null");
        return StreamSupport
            .stream(userExtendRepository.findAll().spliterator(), false)
            .filter(userExtend -> userExtend.getAddress() == null)
            .map(userExtendMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userExtend by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserExtendDTO> findOne(Long id) {
        log.debug("Request to get UserExtend : {}", id);
        return userExtendRepository.findById(id)
            .map(userExtendMapper::toDto);
    }

    /**
     * Delete the userExtend by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserExtend : {}", id);
        userExtendRepository.deleteById(id);
    }
}
