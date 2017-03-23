package springbootpractice.controller;

import com.boraseoksoon.spring.boot.practice.com.boraseoksoon.spring.boot.practice.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.boraseoksoon.spring.boot.practice.com.boraseoksoon.spring.boot.practice.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

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

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("Users", userRepository.findOne(id));
        return "/user/updateForm";
    }
}