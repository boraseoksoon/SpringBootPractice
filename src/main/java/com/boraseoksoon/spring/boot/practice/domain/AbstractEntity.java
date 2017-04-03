package com.boraseoksoon.spring.boot.practice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by seoksoonjang on 2017. 4. 4..
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    public String getFormattedCreatedDate() {
        if (createdDate == null) {
            throw new IllegalStateException("question createDate is NULL!");
        }

        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    // it will give Answer exception NULL to be inserted. so comments until it will stay stable.
    /*
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public String getFormattedModifiedDate() {
        if (modifiedDate == null) {
            throw new IllegalStateException("question modifiedDate is NULL!");
        }

        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
