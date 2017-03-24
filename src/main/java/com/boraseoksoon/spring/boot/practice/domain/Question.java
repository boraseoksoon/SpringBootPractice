package com.boraseoksoon.spring.boot.practice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String writer;
    private String contents;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question() {}

    public Question(String writer, String contents, String title) {
        super();

        this.writer = writer;
        this.contents = contents;
        this.title = title;
    }
}
