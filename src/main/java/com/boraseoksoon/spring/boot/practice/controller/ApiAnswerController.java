package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.*;
import com.boraseoksoon.spring.boot.practice.util.HttpSessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by seoksoonjang on 2017. 4. 3..
 */

@RestController
@RequestMapping("/api/questions/{questionId}/answer")
public class ApiAnswerController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("")
    public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
        System.out.println("answer created!");
        if (!HttpSessionUtility.isLoginUser(session)) {
            return null;
        }

        User loginUser = HttpSessionUtility.getUserFromSession(session);
        Question question = questionRepository.findOne(questionId);
        Answer answer = new Answer(loginUser, question, contents);

        question.addAnswer();
        questionRepository.save(question);

        return answerRepository.save(answer);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
        if (!HttpSessionUtility.isLoginUser(session)) {
            return Result.fail("you should log-in first");
        }

        Answer answer = answerRepository.findOne(id);
        User loginUser = HttpSessionUtility.getUserFromSession(session);
        if (!answer.isSameWriter(loginUser)) {
            return Result.fail("you can delete articles that you wrote by yourself.");
        }

        answerRepository.delete(id);

        Question question = questionRepository.findOne(questionId);
        question.deleteAnswer();
        questionRepository.save(question);

        return Result.ok();
    }
}
