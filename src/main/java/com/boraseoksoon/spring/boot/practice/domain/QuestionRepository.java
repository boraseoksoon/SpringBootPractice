package com.boraseoksoon.spring.boot.practice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */
public interface QuestionRepository extends JpaRepository <Question, Long> {
}
