package com.vbtn.taskunite.service.api.task;

import com.vbtn.taskunite.domain.Room;
import com.vbtn.taskunite.domain.Task;
import com.vbtn.taskunite.repository.custom.CustomTaskRepository;
import com.vbtn.taskunite.service.dto.TaskDTO;
import com.vbtn.taskunite.service.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomTaskService {
    @Autowired
    CustomTaskRepository customTaskRepository;

    private final TaskMapper taskMapper;

    private final Logger log = LoggerFactory.getLogger(CustomTaskService.class);

    public CustomTaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Task");
        return customTaskRepository.findAll(pageable)
            .map(taskMapper::toDto);
    }

    public Task save(Task task) {
        task.setRoom(new Room());
        task = customTaskRepository.save(task);
        return task;
    }
}
