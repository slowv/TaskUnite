package com.vbtn.taskunite.web.rest.custom;

import com.vbtn.taskunite.domain.Task;
import com.vbtn.taskunite.service.custom.task.CustomTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    CustomTaskService customTaskService;

    @RequestMapping("/{id}")
    public String roomTask(@PathVariable("id") Long id,
                           Model model){
        Task task = customTaskService.findOne(id);
        model.addAttribute("task", task);
        return "task/room/room";
    }
}
