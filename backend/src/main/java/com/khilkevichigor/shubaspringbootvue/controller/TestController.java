package com.khilkevichigor.shubaspringbootvue.controller;

import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/ping2")
    public String ping() {
        User user = userService.findUserByUsername("admin");
        return user.getUsername() + " is pong";
    }

    @GetMapping("/api/username")
    public String getUserName() {
        User user = userService.findUserByUsername("admin");
        return user.getUsername();
    }
}
