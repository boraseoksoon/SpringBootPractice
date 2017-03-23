package springbootpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */

@Controller
public class HomeController {
    @GetMapping("")
    public String home() {
        return "index";
    }
}
