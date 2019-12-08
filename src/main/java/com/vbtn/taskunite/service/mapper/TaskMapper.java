package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.TaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Task} and its DTO {@link TaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserInformationMapper.class, TaskerCategoryMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "tasker.id", target = "taskerId")
    @Mapping(source = "master.id", target = "masterId")
    @Mapping(source = "taskerCategory.id", target = "taskerCategoryId")
    TaskDTO toDto(Task task);

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "removeReviews", ignore = true)
    @Mapping(target = "adminProfits", ignore = true)
    @Mapping(target = "removeAdminProfits", ignore = true)
    @Mapping(source = "taskerId", target = "tasker")
    @Mapping(source = "masterId", target = "master")
    @Mapping(source = "taskerCategoryId", target = "taskerCategory")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }
}
