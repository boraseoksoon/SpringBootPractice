package com.boraseoksoon.spring.boot.practice.domain;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;
//    private String writer;

    @OneToMany
    @OrderBy("id ASC")
    private List<Answer> answers;

    private String contents;
    private String title;

    private LocalDateTime createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
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

    public Question(User writer, String contents, String title) {
        super();

        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.createDate = LocalDateTime.now();
    }

    public String getFormattedCreatedDate() {
        if (createDate == null) {
            throw new IllegalStateException("question createDate is NULL!");
        }

        return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public boolean isSameWriter(User loginUser) {
        return this.writer.equals(loginUser);
    }
}
