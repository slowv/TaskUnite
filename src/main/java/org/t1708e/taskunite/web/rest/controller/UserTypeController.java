package org.t1708e.taskunite.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-type")
public class UserTypeController {

    @GetMapping("")
    public String userType() {
        return "user-type/user-type";
    }
}
