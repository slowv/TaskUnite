package com.vbtn.taskunite.web.rest.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {
    @RequestMapping("/create/step1")
    public String createStep1(){
        return "task/create/step1";
    }

    @RequestMapping("/create/step2")
    public String createStep2(){
        return "task/create/step2";
    }

    @RequestMapping("/create/step3")
    public String createStep3(){
        return "task/create/step3";
    }

    @RequestMapping("/create/step4")
    public String createStep4(){
        return "task/create/step4";
    }
}
