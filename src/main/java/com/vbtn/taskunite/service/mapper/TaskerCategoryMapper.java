package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.TaskerCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskerCategory} and its DTO {@link TaskerCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskerCategoryMapper extends EntityMapper<TaskerCategoryDTO, TaskerCategory> {


    @Mapping(target = "taskCategory", ignore = true)
    @Mapping(target = "taskers", ignore = true)
    @Mapping(target = "removeTaskers", ignore = true)
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
