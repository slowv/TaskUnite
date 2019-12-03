package com.vbtn.taskunite.service.api.task;

import com.vbtn.taskunite.domain.Tasker;
import com.vbtn.taskunite.repository.custom.CustomTaskerRepository;
import com.vbtn.taskunite.service.dto.TaskerDTO;
import com.vbtn.taskunite.service.mapper.TaskerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomTaskerService {
    @Autowired
    CustomTaskerRepository customTaskerRepository;

    private final TaskerMapper taskerMapper;

    private final Logger log = LoggerFactory.getLogger(CustomTaskerService.class);

    public CustomTaskerService(TaskerMapper taskerMapper) {
        this.taskerMapper = taskerMapper;
    }

    @Transactional(readOnly = true)
    public Page<TaskerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tasker");
        return customTaskerRepository.findAll(pageable)
            .map(taskerMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<Tasker> findOne(Long id) {
        log.debug("Request to get Tasker : {}", id);
        return customTaskerRepository.findOneWithEagerRelationships(id);
    }
}
