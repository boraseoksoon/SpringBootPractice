package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.UserRepository;
import com.boraseoksoon.spring.boot.practice.util.HttpSessionUtility;
import com.boraseoksoon.spring.boot.practice.util.ModelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.boraseoksoon.spring.boot.practice.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public String create(User user) {
        System.out.println("User : " + user);
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute(ModelUtility.USER_MODEL_IDENTIFIER, userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/loginForm")
    public String loginForm() { return "/user/login"; }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session) == false) {
            return "redirect:/users/loginForm";
        }
        
        User loginUser = HttpSessionUtility.getUserFromSession(session);
        if (id.equals(loginUser.getId()) == false) {
            throw new IllegalStateException("you can't change other user's information!");
        }

        model.addAttribute(ModelUtility.USER_MODEL_IDENTIFIER, userRepository.findOne(id));
        return "/user/updateForm";
    }

    @PutMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, User updatedUser, HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session) == false) {
            return "redirect:/users/loginForm";
        }

        User loginUser = HttpSessionUtility.getUserFromSession(session);
        if (id.equals(loginUser.getId()) == false) {
            throw new IllegalStateException("you can't change other user's information!");
        }

        User previousUser = userRepository.findOne(id);
        User newUser = previousUser.update(updatedUser);

        userRepository.save(newUser);

        model.addAttribute(ModelUtility.USER_MODEL_IDENTIFIER, newUser);

        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        User loginUser = userRepository.findOneByUserId(userId);

        if (!loginUser.isMatchPassword(password)) {
            System.out.println("[Error] User password is not matched to login.");
            return "redirect:/users/loginForm";
        }

        System.out.println(loginUser + " is login success!");

        session.setAttribute(HttpSessionUtility.USER_SESSION_KEY, loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtility.USER_SESSION_KEY);

        return "redirect:/";
    }
}