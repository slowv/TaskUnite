package com.t1708e.taskunite.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(){
        return "create-task";
    }
}
