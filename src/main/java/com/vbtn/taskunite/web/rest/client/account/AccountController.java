package com.vbtn.taskunite.web.rest.client.account;

import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.api.account.IntroService;
import com.vbtn.taskunite.service.api.account.RegisterTaskerService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class AccountController {
    @Autowired
    IntroService introService;
    @Autowired
    UserService userService;
    @Autowired
    RegisterTaskerService registerTaskerService;

    @GetMapping("")
    public String intro() {
        if (userService.getAuthorities().size() > 1) {
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
    public String promoteTasker() {
        registerTaskerService.promoteTasker(userService.getUserWithAuthorities().get());
        return "redirect:/?tasker=success";
    }
}
