package springbootpractice.controller;

import com.boraseoksoon.spring.boot.practice.com.boraseoksoon.spring.boot.practice.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.boraseoksoon.spring.boot.practice.com.boraseoksoon.spring.boot.practice.domain.User;

import java.util.ArrayList;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */
@Controller
public class UserController {

    // Dependency Injection!
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public String create(User user) {
        System.out.println("User : " + user);
        userRepository.save(user);
        return "redirect:/users";
    }

    // request URL, ex : localhost:8080/user/list
    @GetMapping("/users")
    public String showList(Model model) {
        model.addAttribute("Users", userRepository.findAll());
        return "/user/list";    // template URL, ex : template/user/list.html
    }
}