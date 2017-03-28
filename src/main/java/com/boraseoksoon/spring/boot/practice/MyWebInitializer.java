package com.boraseoksoon.spring.boot.practice;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * Created by seoksoonjang on 2017. 3. 28..
 */
public class MyWebInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootPracticeApplication.class);
    }
}
