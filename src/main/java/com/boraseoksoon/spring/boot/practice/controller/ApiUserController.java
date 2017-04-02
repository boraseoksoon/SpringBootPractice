package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.User;
import com.boraseoksoon.spring.boot.practice.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by seoksoonjang on 2017. 3. 28..
 */
@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return userRepository.findOne(id);
    }
}
