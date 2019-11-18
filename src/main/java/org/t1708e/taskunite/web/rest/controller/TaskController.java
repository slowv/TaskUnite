package org.t1708e.taskunite.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "/create/step1")
    public String createStep1(){
        return "create-task-step-1";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/step2")
    public String createStep2(){
        return "create-task-step-2";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/step3")
    public String createStep3(){
        return "create-task-step-3";
    }
}
