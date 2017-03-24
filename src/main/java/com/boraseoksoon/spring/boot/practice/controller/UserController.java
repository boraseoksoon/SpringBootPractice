package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.UserRepository;
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
        model.addAttribute("Users", userRepository.findAll());
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
        Object loginUserObject= session.getAttribute("loginUser");

        if (loginUserObject == null) {
            return "redirect:/users/loginForm";
        }

        User loginUser = (User)loginUserObject;
        if (id.equals(loginUser.getId()) == false) {
            throw new IllegalStateException("you can't change other user's information!");
        }

        model.addAttribute("Users", userRepository.findOne(id));
        return "/user/updateForm";
    }

    @PutMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, User updatedUser, HttpSession session) {
        Object loginUserObject= session.getAttribute("loginUser");

        if (loginUserObject == null) {
            return "redirect:/users/loginForm";
        }

        User loginUser = (User)loginUserObject;
        if (id.equals(loginUser.getId()) == false) {
            throw new IllegalStateException("you can't change other user's information!");
        }

        User previousUser = userRepository.findOne(id);
        User newUser = previousUser.update(updatedUser);
        userRepository.save(newUser);
        model.addAttribute("Users", newUser);

        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User loginUser = userRepository.findOneByUserId(userId);

        if (loginUser == null) {
            System.out.println("[Error] User is null.");
            return "redirect:/users/loginForm";
        }

        if (loginUser.getPassword().equals(password) == false) {
            System.out.println("[Error] User password is not matched to login.");
            return "redirect:/users/loginForm";
        }

        System.out.println(loginUser + " is login success!");
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}