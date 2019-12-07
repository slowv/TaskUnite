package com.vbtn.taskunite.service.custom.task;

import com.vbtn.taskunite.repository.custom.CustomTaskCategoryRepository;
import com.vbtn.taskunite.service.dto.TaskCategoryDTO;
import com.vbtn.taskunite.service.mapper.TaskCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TaskCategoryDTO> findAll() {
        log.debug("Request to get all Task category");
        return customTaskCategoryRepository.findAll().stream()
            .map(taskCategoryMapper::toDto).collect(Collectors.toList());
    }
}
