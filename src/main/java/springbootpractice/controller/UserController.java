package springbootpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springbootpractice.model.User;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */
@Controller
public class UserController {
//    @GetMapping("/create")
    @PostMapping("/create")
    public String create(User user, Model model){
        model.addAttribute("name", user.getName());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("email", user.getEmail());

        System.out.println("User : " + user);

        return "index";
    }
}
