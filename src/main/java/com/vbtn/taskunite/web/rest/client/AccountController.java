package com.vbtn.taskunite.web.rest.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("login")
    public String login() {
        return "account/login/login";
    }

    @GetMapping("register")
    public String register() {
        return "account/register/register";
    }

    @GetMapping("intro")
    public String intro() {
        return "account/intro/intro";
    }
}
