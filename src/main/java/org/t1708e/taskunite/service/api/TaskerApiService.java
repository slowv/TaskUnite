package org.t1708e.taskunite.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.t1708e.taskunite.domain.Tasker;
import org.t1708e.taskunite.repository.api.TaskerApiRepository;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class TaskerApiService {

    @Autowired
    private TaskerApiRepository taskerApiRepository;


    public List<Tasker> getList(Specification specification){
        return taskerApiRepository.findAll(specification);
    }
}
