package com.boraseoksoon.spring.boot.practice.controller;

import com.boraseoksoon.spring.boot.practice.domain.Question;
import com.boraseoksoon.spring.boot.practice.domain.QuestionRepository;
import com.boraseoksoon.spring.boot.practice.domain.User;
import com.boraseoksoon.spring.boot.practice.util.HttpSessionUtility;
import com.boraseoksoon.spring.boot.practice.util.ModelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/form")
    public String form(HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session) == false) {
            return "redirect:/users/loginForm";
        }

        return "qna/form";
    }

    @PostMapping("")
    public String create(String contents, String title, HttpSession session) {
        if (HttpSessionUtility.isLoginUser(session) == false) {
            return "redirect:/users/loginForm";
        }

        User loginedUser = HttpSessionUtility.getUserFromSession(session);

        System.out.println("Question Repository loginedUser  : " + loginedUser);

        Question newQuestion = new Question(loginedUser, contents, title);
        questionRepository.save(newQuestion);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showDetailQuestion(@PathVariable Long id, Model model) {
        Question detailQuestion= questionRepository.findOne(id);
        model.addAttribute(ModelUtility.QUESTION_MODEL_IDENTIFIER, detailQuestion);

        return "/qna/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!HttpSessionUtility.isLoginUser(session)) {
            return "/users/loginForm";
        }

        User sessionedUser = HttpSessionUtility.getUserFromSession(session);
        Question question = questionRepository.findOne(id);

        if (!question.isSameWriter(sessionedUser)) {
            return "/users/loginForm";
        }

        model.addAttribute(ModelUtility.QUESTION_MODEL_IDENTIFIER, questionRepository.findOne(id));
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, String title, String contents, HttpSession session) {
        if (!HttpSessionUtility.isLoginUser(session)) {
            return "/users/loginForm";
        }

        User sessionedUser = HttpSessionUtility.getUserFromSession(session);
        Question question = questionRepository.findOne(id);

        if (!question.isSameWriter(sessionedUser)) {
            return "/users/loginForm";
        }

        question.update(title, contents);
        questionRepository.save(question);

        return String.format("redirect:/questions/%d", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (!HttpSessionUtility.isLoginUser(session)) {
            return "/users/loginForm";
        }

        User sessionedUser = HttpSessionUtility.getUserFromSession(session);
        Question question = questionRepository.findOne(id);

        if (!question.isSameWriter(sessionedUser)) {
            return "/users/loginForm";
        }

        questionRepository.delete(id);
        return "redirect:/";
    }
}
