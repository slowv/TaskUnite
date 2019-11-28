package com.vbtn.taskunite.service;

import com.vbtn.taskunite.service.dto.RoomDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.vbtn.taskunite.domain.Room}.
 */
public interface RoomService {

    /**
     * Save a room.
     *
     * @param roomDTO the entity to save.
     * @return the persisted entity.
     */
    RoomDTO save(RoomDTO roomDTO);

    /**
     * Get all the rooms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoomDTO> findAll(Pageable pageable);
    /**
     * Get all the RoomDTO where Task is {@code null}.
     *
     * @return the list of entities.
     */
    List<RoomDTO> findAllWhereTaskIsNull();


    /**
     * Get the "id" room.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoomDTO> findOne(Long id);

    /**
     * Delete the "id" room.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
