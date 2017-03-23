package springbootpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */

@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(String name, String age, Model model) {
        System.out.println("name : " + name + " age : " + age);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "welcome";
    }
}
