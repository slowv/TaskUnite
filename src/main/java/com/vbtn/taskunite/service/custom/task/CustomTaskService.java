package com.vbtn.taskunite.service.custom.task;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.PaymentInformationRepository;
import com.vbtn.taskunite.repository.StatisticRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomTaskService {
    @Autowired
    CustomTaskRepository customTaskRepository;
    @Autowired
    PaymentInformationRepository paymentInformationRepository;
    @Autowired
    StatisticRepository statisticRepository;

    private final TaskMapper taskMapper;

    private final Logger log = LoggerFactory.getLogger(CustomTaskService.class);

    public CustomTaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> findAll(Pageable pageable) {
        return customTaskRepository.findAll(pageable)
            .map(taskMapper::toDto);
    }

    public List<Task> findAllWithStatus1(Long id) {
        List<Task> tasks = customTaskRepository.findAllByStatusEquals1();
        return tasks.stream().filter(task -> task.getTasker().getId() == id || task.getMaster().getId() == id).collect(Collectors.toList());
    }

    public Task findOne(Long id){
        return customTaskRepository.findById(id).orElse(null);
    }

    public Task save(Task task) {
        task.setStatus(1);
        task = customTaskRepository.save(task);
        return task;
    }

    public void saveAll(Task task, PaymentInformation paymentTasker, Statistic statisticTasker, PaymentInformation paymentMaster, Statistic statisticMaster) {
        customTaskRepository.save(task);
        paymentInformationRepository.save(paymentMaster);
        statisticRepository.save(statisticMaster);
        paymentInformationRepository.save(paymentTasker);
        statisticRepository.save(statisticTasker);
    }
}
