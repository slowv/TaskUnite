package org.t1708e.taskunite.service;

import org.t1708e.taskunite.service.dto.UserExtendDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.t1708e.taskunite.domain.UserExtend}.
 */
public interface UserExtendService {

    /**
     * Save a userExtend.
     *
     * @param userExtendDTO the entity to save.
     * @return the persisted entity.
     */
    UserExtendDTO save(UserExtendDTO userExtendDTO);

    /**
     * Get all the userExtends.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserExtendDTO> findAll(Pageable pageable);
    /**
     * Get all the UserExtendDTO where Tasker is {@code null}.
     *
     * @return the list of entities.
     */
    List<UserExtendDTO> findAllWhereTaskerIsNull();
    /**
     * Get all the UserExtendDTO where Master is {@code null}.
     *
     * @return the list of entities.
     */
    List<UserExtendDTO> findAllWhereMasterIsNull();
    /**
     * Get all the UserExtendDTO where Address is {@code null}.
     *
     * @return the list of entities.
     */
    List<UserExtendDTO> findAllWhereAddressIsNull();


    /**
     * Get the "id" userExtend.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserExtendDTO> findOne(Long id);

    /**
     * Delete the "id" userExtend.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
