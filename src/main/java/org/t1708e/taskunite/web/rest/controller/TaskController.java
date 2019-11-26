package org.t1708e.taskunite.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "/create/step1")
    public String createStep1(){
        return "task/create/step-1";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/step2")
    public String createStep2(){
        return "task/create/step-2";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/step3")
    public String createStep3(){
        return "task/create/step-3";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/step4")
    public String createStep4(){
        return "task/create/step-4";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/room/{id}")
    public String roomTask(@PathVariable("id") Long id){
        return "task/room/task-room";
    }
}
