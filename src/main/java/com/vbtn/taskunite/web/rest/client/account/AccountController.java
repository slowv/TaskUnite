package com.vbtn.taskunite.web.rest.client.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("intro")
    public String intro() {
        return "account/intro";
    }
}
