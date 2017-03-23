package com.boraseoksoon.spring.boot.practice.com.boraseoksoon.spring.boot.practice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
