package com.comit.controller;

import com.comit.model.User;
import com.comit.model.UserForm;
import com.comit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody UserForm userForm)
    {
        return userService.createUser(userForm);

    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/users/username")
    public boolean isUsernameExist(@RequestParam("username") String username)
    {
        return userService.getUserByUserName(username) == null;
    }
}
