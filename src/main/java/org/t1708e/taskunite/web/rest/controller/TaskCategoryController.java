package org.t1708e.taskunite.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task-category")
public class TaskCategoryController {
    
    @GetMapping("")
    public String example(){
        return "category/task-category";
    }
}
