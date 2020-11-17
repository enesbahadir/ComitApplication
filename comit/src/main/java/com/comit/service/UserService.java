package com.comit.service;

import com.comit.model.User;
import com.comit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User model)
    {
        User newUser = new User(model.getUsername(),model.getPassword(),
                model.getName(), model.getSurName(), model.getType());
        userRepository.save(newUser);
        return newUser;
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
