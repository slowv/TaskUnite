package com.vbtn.taskunite.service.api;

import com.vbtn.taskunite.domain.Tasker;
import com.vbtn.taskunite.repository.api.TaskerApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskerApiService {

    @Autowired
    private TaskerApiRepository taskerApiRepository;

    public List<Tasker> getList(Specification specification){
        return taskerApiRepository.findAll(specification);
    }
}
