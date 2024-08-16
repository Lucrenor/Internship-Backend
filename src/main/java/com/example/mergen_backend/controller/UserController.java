package com.example.mergen_backend.controller;

import com.example.mergen_backend.entity.User;
import com.example.mergen_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User postFormData(@RequestBody User user) {
        return userService.saveFormData(user);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}