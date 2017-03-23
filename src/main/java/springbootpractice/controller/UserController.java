package springbootpractice.controller;

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

    private ArrayList<User> users = new ArrayList<User>();

//    @GetMapping("/create")
    @PostMapping("/create")
    public String create(User user) {
        System.out.println("User : " + user);
        users.add(user);
        return "redirect:/list";
    }

//    @PostMapping("/list")
    @GetMapping("list")
    public String showList(Model model) {
        model.addAttribute("Users", users);
        return "list";
    }
}