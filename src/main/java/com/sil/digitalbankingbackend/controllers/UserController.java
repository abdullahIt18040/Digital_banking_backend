package com.sil.digitalbankingbackend.controllers;

import com.sil.digitalbankingbackend.entities.User;
import com.sil.digitalbankingbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user)
    {
        userService.save(user);
    }
}
