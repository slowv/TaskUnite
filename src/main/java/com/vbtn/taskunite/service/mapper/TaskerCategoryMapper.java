package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.TaskerCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskerCategory} and its DTO {@link TaskerCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskCategoryMapper.class, UserInformationMapper.class})
public interface TaskerCategoryMapper extends EntityMapper<TaskerCategoryDTO, TaskerCategory> {

    @Mapping(source = "taskCategory.id", target = "taskCategoryId")
    @Mapping(source = "user.id", target = "userId")
    TaskerCategoryDTO toDto(TaskerCategory taskerCategory);

    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "removeTasks", ignore = true)
    @Mapping(source = "taskCategoryId", target = "taskCategory")
    @Mapping(source = "userId", target = "user")
    TaskerCategory toEntity(TaskerCategoryDTO taskerCategoryDTO);

    default TaskerCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaskerCategory taskerCategory = new TaskerCategory();
        taskerCategory.setId(id);
        return taskerCategory;
    }
}
