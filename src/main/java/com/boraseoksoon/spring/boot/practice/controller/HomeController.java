package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.QuestionRepository;
import com.boraseoksoon.spring.boot.practice.util.ModelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */

@Controller
public class HomeController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute(ModelUtility.QUESTION_MODEL_IDENTIFIER, questionRepository.findAll());
        return "index";
    }
}
