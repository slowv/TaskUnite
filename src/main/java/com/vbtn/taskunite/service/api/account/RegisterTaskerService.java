package com.vbtn.taskunite.service.api.account;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.*;
import com.vbtn.taskunite.security.AuthoritiesConstants;
import com.vbtn.taskunite.service.TaskerCategoryService;
import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.web.rest.client.vm.TaskerCategoryVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RegisterTaskerService {
    @Autowired
    TaskerRepository taskerRepository;
    @Autowired
    UserInformationRepository userInformationRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    TaskerCategoryRepository taskerCategoryRepository;
    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    public void setTasker(Long districtId, String address, User user, Tasker tasker) {
        UserInformation userInformation = userInformationRepository.getOne(user.getId());

        District d = districtRepository.getOne(districtId);
        Address a = new Address();
        a.setContent(address);
        a.setDistrict(d);
        a.setUser(userInformation);
        addressRepository.save(a);

        tasker.setStatus(1);
        tasker.setUser(userInformation);
        taskerRepository.save(tasker);
    }

    public void saveTaskerCategory(User user, TaskerCategoryVM category) {
        UserInformation userInformation = userInformationRepository.getOne(user.getId());
        Tasker t = userInformation.getTasker();

        TaskCategory taskCategory = taskCategoryRepository.getOne(category.getCategoryId());

        TaskerCategory taskerCategory = new TaskerCategory();
        taskerCategory.setPrice(category.getPrice());
        taskerCategory.setDescription(category.getDescription());
        taskerCategory.setStatus(category.getHaveTool() != null ? 1 : 2);
        taskerCategory.setTaskCategory(taskCategory);
        taskerCategory.setTasker(t);
        taskerCategoryRepository.save(taskerCategory);
    }

    public void promoteTasker(User user) {
        Set<Authority> authorities = user.getAuthorities();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.TASKER));
        user.setAuthorities(authorities);
        userRepository.save(user);
    }
}
