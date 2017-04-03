package com.boraseoksoon.spring.boot.practice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */

@Entity
public class User extends AbstractEntity {
    @Column(nullable = false, length = 15, unique = true)
    @JsonProperty
    private String userId;
    @JsonProperty
    private String email;
    @JsonIgnore
    private String password;
    @JsonProperty
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User update(User updatedUser) {
        this.setName(updatedUser.getName());
        this.setEmail(updatedUser.getEmail());
        this.setUserId(updatedUser.getUserId());
        this.setPassword(updatedUser.getPassword());

        return this;
    }

    public boolean isMatchId(Long newId) {
        if (newId == null) {
            return false;
        }

        return newId.equals(super.getId());
    }

    public boolean isMatchPassword(String newPassword) {
        if (newPassword == null) {
            return false;
        }

        return newPassword.equals(this.getPassword());
    }

    @Override
    public String toString() {
        return "User : " + super.toString();
    }
}
