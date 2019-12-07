package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.TaskerCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskerCategory} and its DTO {@link TaskerCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskCategoryMapper.class, TaskerMapper.class})
public interface TaskerCategoryMapper extends EntityMapper<TaskerCategoryDTO, TaskerCategory> {

    @Mapping(source = "taskCategory.id", target = "taskCategoryId")
    @Mapping(source = "tasker.id", target = "taskerId")
    TaskerCategoryDTO toDto(TaskerCategory taskerCategory);

    @Mapping(source = "taskCategoryId", target = "taskCategory")
    @Mapping(source = "taskerId", target = "tasker")
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
