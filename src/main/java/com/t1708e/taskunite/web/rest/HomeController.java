package com.t1708e.taskunite.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "/become-task", method = RequestMethod.GET)
    public String registerTask() {
        return "registerTask";

    }
}
