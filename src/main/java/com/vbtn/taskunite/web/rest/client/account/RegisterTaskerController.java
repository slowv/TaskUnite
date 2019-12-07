package com.vbtn.taskunite.web.rest.client.account;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.*;
import com.vbtn.taskunite.service.api.account.RegisterTaskerService;
import com.vbtn.taskunite.service.dto.UserInformationDTO;
import com.vbtn.taskunite.web.rest.client.vm.TaskerCategoryVM;
import org.checkerframework.checker.units.qual.A;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/tasker")
public class RegisterTaskerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    DistrictService districtService;
    @Autowired
    CityService cityService;
    @Autowired
    UserService userService;
    @Autowired
    RegisterTaskerService registerTaskerService;
    @Autowired
    TaskCategoryService taskCategoryService;

    @GetMapping("/step1")
    public String step1(Model model) {
        model.addAttribute("tasker", new Tasker());
        model.addAttribute("districts", districtService.findAll(Pageable.unpaged()));
        model.addAttribute("cities", cityService.findAll(Pageable.unpaged()));
        return "account/tasker/step1";
    }

    @PostMapping("/step1")
    public String saveTasker(Model model, Tasker tasker, @RequestParam("district") Long districtId, @RequestParam("city") Long cityId, @RequestParam("address") String address) {
        if (userService.getUserWithAuthorities().isPresent()) {
            registerTaskerService.setTasker(districtId, address, userService.getUserWithAuthorities().get(), tasker);
            return "redirect:/tasker/step2";
        }
        return "account/tasker/step1";
    }

    @GetMapping("/step2")
    public String step2(Model model) {
        model.addAttribute("categories", taskCategoryService.findAll(Pageable.unpaged()));
        return "account/tasker/step2";
    }
}
