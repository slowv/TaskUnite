package com.t1708e.taskunite.service.mapper;

import com.t1708e.taskunite.domain.*;
import com.t1708e.taskunite.service.dto.TaskerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tasker} and its DTO {@link TaskerDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, AddressMapper.class, TaskCategoryMapper.class})
public interface TaskerMapper extends EntityMapper<TaskerDTO, Tasker> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "adress.id", target = "adressId")
    TaskerDTO toDto(Tasker tasker);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "adressId", target = "adress")
    @Mapping(target = "workingAddresses", ignore = true)
    @Mapping(target = "removeWorkingAddresses", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "removeTasks", ignore = true)
    @Mapping(target = "removeTaskCategories", ignore = true)
    Tasker toEntity(TaskerDTO taskerDTO);

    default Tasker fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tasker tasker = new Tasker();
        tasker.setId(id);
        return tasker;
    }
}
