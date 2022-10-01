package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.models.User;
import com.hal.CoachesWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "CoachManagement/User")
public class UserController {
    @Autowired
    private UserRepository userRes;
    @GetMapping("")
    List<User> getAllUser() {
        return userRes.findAll();
    }
}
