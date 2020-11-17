package com.comit.controller;

import com.comit.model.User;
import com.comit.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User newUser)
    {
        return userService.createUser(newUser);
                /*EntityModel.of(newUser,
                linkTo(methodOn(RegisterController.class).getPreschoolById(newPreschool.getId())).withSelfRel(),
                linkTo(methodOn(RegisterController.class).listOfPreschools()).withRel("preschools"));*/
    }
}
