package com.boraseoksoon.spring.boot.practice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @JsonProperty
    private User writer;
//    private String writer;

    @OneToMany(mappedBy = "question")
    // @OrderBy("id ASC")
    @OrderBy("id DESC")
    private List<Answer> answers;

    @Lob
    @JsonProperty
    private String contents;

    @JsonProperty
    private String title;

    @JsonProperty
    private Integer countOfAnswer = 0;

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

    public Integer getCountOfAnswer() {
        return countOfAnswer;
    }

    public void setCountOfAnswer(Integer countOfAnswer) {
        this.countOfAnswer = countOfAnswer;
    }

    public boolean isSameWriter(User loginUser) {
        return this.writer.equals(loginUser);
    }

    public void addAnswer() {
        this.countOfAnswer += 1;
    }

    public void deleteAnswer() {
        this.countOfAnswer -= 1;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", writer=" + writer +
                ", answers=" + answers +
                ", contents='" + contents + '\'' +
                ", title='" + title + '\'' +
                ", countOfAnswer=" + countOfAnswer +
                ", createDate=" + createDate +
                '}';
    }
}
