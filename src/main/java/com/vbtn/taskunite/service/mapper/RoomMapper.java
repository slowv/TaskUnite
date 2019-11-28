package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.RoomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Room} and its DTO {@link RoomDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskerMapper.class, MasterMapper.class})
public interface RoomMapper extends EntityMapper<RoomDTO, Room> {

    @Mapping(source = "tasker.id", target = "taskerId")
    @Mapping(source = "master.id", target = "masterId")
    RoomDTO toDto(Room room);

    @Mapping(target = "task", ignore = true)
    @Mapping(source = "taskerId", target = "tasker")
    @Mapping(source = "masterId", target = "master")
    Room toEntity(RoomDTO roomDTO);

    default Room fromId(Long id) {
        if (id == null) {
            return null;
        }
        Room room = new Room();
        room.setId(id);
        return room;
    }
}
