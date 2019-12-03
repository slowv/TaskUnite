package com.vbtn.taskunite.service.api.task;

import com.vbtn.taskunite.repository.custom.CustomTaskCategoryRepository;
import com.vbtn.taskunite.service.dto.TaskCategoryDTO;
import com.vbtn.taskunite.service.mapper.TaskCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomTaskCategoryService {
    @Autowired
    CustomTaskCategoryRepository customTaskCategoryRepository;

    private final TaskCategoryMapper taskCategoryMapper;

    private final Logger log = LoggerFactory.getLogger(CustomTaskCategoryService.class);

    public CustomTaskCategoryService(TaskCategoryMapper taskCategoryMapper) {
        this.taskCategoryMapper = taskCategoryMapper;
    }

    @Transactional(readOnly = true)
    public Page<TaskCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Task category");
        return customTaskCategoryRepository.findAll(pageable)
            .map(taskCategoryMapper::toDto);
    }
}
