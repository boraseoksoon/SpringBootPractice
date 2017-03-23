package com.boraseoksoon.spring.boot.practice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 15)
    private String userId;
    private String email;
    private String password;
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
        this.name = updatedUser.name;
        this.email = updatedUser.email;
        this.userId = updatedUser.userId;
        this.password = updatedUser.password;

        return this;
    }

    @Override
    public String toString() {
        return "user object description : [userId : " + getUserId() + " password : " +
        getPassword() + " name : " + getName() + " email : " + getEmail() + "]";
    }
}
