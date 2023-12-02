package com.kanban.backend.controller;

import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
