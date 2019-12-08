package com.vbtn.taskunite.service.custom.tasker;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.*;
import com.vbtn.taskunite.security.AuthoritiesConstants;
import com.vbtn.taskunite.web.rest.custom.vm.TaskerCategoryVM;
import com.vbtn.taskunite.web.rest.custom.vm.TaskerVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class TaskerService {
    @Autowired
    UserInformationRepository userInformationRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    TaskerCategoryRepository taskerCategoryRepository;
    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    public void setTasker(User u, TaskerVM taskerVM) {
        UserInformation userInformation = userInformationRepository.getOne(u.getId());

        userInformation.setBday(taskerVM.getBday());
        userInformation.setBmonth(taskerVM.getBmonth());
        userInformation.setByear(taskerVM.getByear());
        userInformation.setImage(taskerVM.getImage());
        userInformation.setDescription(taskerVM.getDescription());
        userInformation.setDistrict(districtRepository.getOne(taskerVM.getDistrict()));
        userInformationRepository.save(userInformation);
    }

    public void saveTaskerCategory(User u, TaskerCategoryVM category) {
        UserInformation userInformation = userInformationRepository.getOne(u.getId());

        TaskCategory taskCategory = taskCategoryRepository.getOne(category.getCategoryId());

        TaskerCategory taskerCategory = new TaskerCategory();
        taskerCategory.setPrice(category.getPrice());
        taskerCategory.setDescription(category.getDescription());
        taskerCategory.setType(category.getHaveTool() != null ? 1 : 2);
        taskerCategory.setTaskCategory(taskCategory);
        taskerCategory.setUser(userInformation);
        taskerCategoryRepository.save(taskerCategory);
    }

    public void promoteTasker(User u) {
        Set<Authority> authorities = u.getAuthorities();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.TASKER));
        u.setAuthorities(authorities);
        userRepository.save(u);
    }
}
