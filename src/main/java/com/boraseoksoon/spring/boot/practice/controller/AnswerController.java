package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.*;
import com.boraseoksoon.spring.boot.practice.util.HttpSessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session) == false) {
            throw new IllegalStateException("You are not Logged-in.");
        }

        User loginUser = HttpSessionUtility.getUserFromSession(session);
        Question question = questionRepository.findOne(questionId);
        Answer answer = new Answer(loginUser, question, contents);

        return answerRepository.save(answer);
    }
}
