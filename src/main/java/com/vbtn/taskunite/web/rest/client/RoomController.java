package com.vbtn.taskunite.web.rest.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {
    @RequestMapping("/{id}")
    public String roomTask(@PathVariable("id") Long id){
        return "task/room/room";
    }
}
