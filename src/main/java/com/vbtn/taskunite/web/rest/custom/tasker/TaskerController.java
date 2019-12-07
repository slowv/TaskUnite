package com.vbtn.taskunite.web.rest.custom.tasker;

import com.vbtn.taskunite.service.*;
import com.vbtn.taskunite.service.custom.tasker.TaskerService;
import com.vbtn.taskunite.web.rest.custom.vm.TaskerVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tasker")
public class TaskerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    DistrictService districtService;
    @Autowired
    CityService cityService;
    @Autowired
    UserService userService;
    @Autowired
    TaskerService taskerService;
    @Autowired
    TaskCategoryService taskCategoryService;

    @GetMapping("/step1")
    public String step1(Model model) {
        model.addAttribute("tasker", new TaskerVM());
        model.addAttribute("districts", districtService.findAll(Pageable.unpaged()));
        model.addAttribute("cities", cityService.findAll(Pageable.unpaged()));
        return "account/tasker/step1";
    }

    @PostMapping("/step1")
    public String saveTasker(TaskerVM tasker) {
        taskerService.setTasker(userService.getUserWithAuthorities().get(), tasker);
        return "redirect:/tasker/step2";
    }

    @GetMapping("/step2")
    public String step2(Model model) {
        model.addAttribute("categories", taskCategoryService.findAll(Pageable.unpaged()));
        return "account/tasker/step2";
    }
}
