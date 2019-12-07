package com.vbtn.taskunite.web.rest.client.account;

import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.api.account.IntroService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class AccountController {
    @Autowired
    IntroService introService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String intro() {
        if (!userService.getUserWithAuthorities().isPresent()) {
            return "redirect:/login";
        }
        return "account/intro";
    }

    @GetMapping("/master")
    public String saveMaster() {
        if (!userService.getUserWithAuthorities().isPresent()) {
            return "redirect:/login";
        }
        introService.promoteMaster(userService.getUserWithAuthorities().get());
        return "redirect:/?master=success";
    }
}
