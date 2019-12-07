package com.vbtn.taskunite.web.rest.custom;

import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.custom.IntroService;
import com.vbtn.taskunite.service.custom.tasker.TaskerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class IntroController {
    @Autowired
    IntroService introService;
    @Autowired
    UserService userService;
    @Autowired
    TaskerService taskerService;

    @GetMapping("")
    public String intro() {
        if (userService.getUserWithAuthorities().get().getAuthorities().size() > 1) {
            return "redirect:/";
        }
        return "account/intro";
    }

    @GetMapping("/master")
    public String saveMaster() {
        introService.promoteMaster(userService.getUserWithAuthorities().get());
        return "redirect:/?master=success";
    }

    @PostMapping("/tasker")
    public String saveTasker() {
        taskerService.promoteTasker(userService.getUserWithAuthorities().get());
        return "redirect:/?tasker=success";
    }
}
