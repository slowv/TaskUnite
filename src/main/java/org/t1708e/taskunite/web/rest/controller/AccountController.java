package org.t1708e.taskunite.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(){
        return "account/login/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register(){
        return "account/register/register";
    }
}
