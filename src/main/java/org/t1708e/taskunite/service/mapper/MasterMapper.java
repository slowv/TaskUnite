package org.t1708e.taskunite.service.mapper;

import org.t1708e.taskunite.domain.*;
import org.t1708e.taskunite.service.dto.MasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Master} and its DTO {@link MasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserExtendMapper.class})
public interface MasterMapper extends EntityMapper<MasterDTO, Master> {

    @Mapping(source = "user.id", target = "userId")
    MasterDTO toDto(Master master);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "removeTasks", ignore = true)
    Master toEntity(MasterDTO masterDTO);

    default Master fromId(Long id) {
        if (id == null) {
            return null;
        }
        Master master = new Master();
        master.setId(id);
        return master;
    }
}
