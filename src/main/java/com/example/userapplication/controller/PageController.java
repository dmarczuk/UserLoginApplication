package com.example.userapplication.controller;

import com.example.userapplication.model.MyUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @GetMapping("/remove")
    public String remove() {
        return "remove";
    }

}
