package com.vbtn.taskunite.web.rest.client.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasker")
public class RegisterTaskerController {
    @GetMapping("/step1")
    public String step1() {
        return "account/tasker/step1";
    }

    @GetMapping("/step2")
    public String step2() {
        return "account/tasker/step2";
    }


}
