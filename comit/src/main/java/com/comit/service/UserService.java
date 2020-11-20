package com.comit.service;

import com.comit.execption.UserNotFoundException;
import com.comit.model.ERole;
import com.comit.model.Role;
import com.comit.model.User;
import com.comit.payload.UserForm;
import com.comit.repository.RoleRepository;
import com.comit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(UserForm model)
    {
        User newUser = new User(model.getUsername(),model.getPassword(),
                model.getName(), model.getSurName());
        newUser.setRoles(new HashSet<>(Collections.singletonList(new Role(ERole.USER))));
        userRepository.save(newUser);
        return newUser;
    }

    public void createUserWithSecurity(User model)
    {
        userRepository.save(model);
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
