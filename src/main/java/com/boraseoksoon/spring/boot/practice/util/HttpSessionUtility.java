package com.boraseoksoon.spring.boot.practice.util;

import com.boraseoksoon.spring.boot.practice.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by seoksoonjang on 2017. 3. 24..
 */
public class HttpSessionUtility {
    public static final String USER_SESSION_KEY = "loginUser";

    public static boolean isLoginUser(HttpSession session) {
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);

        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session) {
        if (isLoginUser(session) == false) {
            throw new IllegalStateException("you can't get session while not logged in.");
        }

        return (User)session.getAttribute(USER_SESSION_KEY);
    }

}
