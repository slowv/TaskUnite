package com.vbtn.taskunite.web.rest.custom.api;

import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.custom.tasker.TaskerService;
import com.vbtn.taskunite.web.rest.custom.vm.TaskerCategoryVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasker")
public class TaskerAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserService userService;
    @Autowired
    TaskerService taskerService;

    @PostMapping("/step2/submit")
    public String saveTaskerCategory(@Valid TaskerCategoryVM category) {
        if (category.getConfirm() == null || category.getConfirm() != 1 || category.getDescription() == null || category.getPrice() == null) {
            logger.info("just leave it be");
            return null;
        } else {
            logger.info("save!");
            taskerService.saveTaskerCategory(userService.getUserWithAuthorities().get(), category);
        }
        return "true";
    }
}
