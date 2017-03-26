package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.*;
import com.boraseoksoon.spring.boot.practice.util.HttpSessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by seoksoonjang on 2017. 3. 26..
 */
@Controller
@RequestMapping("/questions/{questionId}/answer")
public class AnswerController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("")
    public String createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session) == false) {
            return "redirect:/users/loginForm";
        }

        User loginUser = HttpSessionUtility.getUserFromSession(session);
        Question question = questionRepository.findOne(questionId);
        Answer answer = new Answer(loginUser, question, contents);
        answerRepository.save(answer);

        return String.format("redirect:/questions/%d", questionId);
    }
}
