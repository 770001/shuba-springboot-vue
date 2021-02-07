package com.khilkevichigor.shubaspringbootvue.controller;

import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    /**
     * Получает всех юзеров
     * @return список юзеров
     */
    @GetMapping(path = "/admin/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
