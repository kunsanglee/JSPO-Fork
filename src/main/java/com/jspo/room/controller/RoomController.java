package com.jspo.room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {

    @GetMapping("/hotel/{rId}")
    public String room(@PathVariable String rId) {

        return "room"; // 객실 html
    }
}
