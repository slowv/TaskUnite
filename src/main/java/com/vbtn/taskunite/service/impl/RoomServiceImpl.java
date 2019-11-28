package com.vbtn.taskunite.service.impl;

import com.vbtn.taskunite.service.RoomService;
import com.vbtn.taskunite.domain.Room;
import com.vbtn.taskunite.repository.RoomRepository;
import com.vbtn.taskunite.service.dto.RoomDTO;
import com.vbtn.taskunite.service.mapper.RoomMapper;
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
 * Service Implementation for managing {@link Room}.
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    /**
     * Save a room.
     *
     * @param roomDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        log.debug("Request to save Room : {}", roomDTO);
        Room room = roomMapper.toEntity(roomDTO);
        room = roomRepository.save(room);
        return roomMapper.toDto(room);
    }

    /**
     * Get all the rooms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RoomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rooms");
        return roomRepository.findAll(pageable)
            .map(roomMapper::toDto);
    }



    /**
    *  Get all the rooms where Task is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<RoomDTO> findAllWhereTaskIsNull() {
        log.debug("Request to get all rooms where Task is null");
        return StreamSupport
            .stream(roomRepository.findAll().spliterator(), false)
            .filter(room -> room.getTask() == null)
            .map(roomMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one room by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RoomDTO> findOne(Long id) {
        log.debug("Request to get Room : {}", id);
        return roomRepository.findById(id)
            .map(roomMapper::toDto);
    }

    /**
     * Delete the room by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Room : {}", id);
        roomRepository.deleteById(id);
    }
}
