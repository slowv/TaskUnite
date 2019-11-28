package org.t1708e.taskunite.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.t1708e.taskunite.service.UserService;
import org.t1708e.taskunite.web.rest.vm.ManagedUserVM;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(AccountController.class.getSimpleName());

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(){
        return "account/login/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register(Model model){
        ManagedUserVM userInfo = new ManagedUserVM();
        model.addAttribute("userInfo", userInfo);
        return "account/register/register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String createUser(ManagedUserVM userInfo){
        logger.info(userInfo.toString());
        userService.registerUser(userInfo, userInfo.getPassword());
        // Later need validate and process after register success or fail
        return "redirect:/";
    }
}
