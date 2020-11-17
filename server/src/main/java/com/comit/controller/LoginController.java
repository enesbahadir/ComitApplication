package com.comit.controller;

import com.comit.model.LoginForm;
import com.comit.model.User;
import com.comit.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginForm user) {
        User findUser = userService.getUserByUserName(user.getUserName());
        return findUser != null && findUser.getPassword().equals(user.getPassword());
    }
}
