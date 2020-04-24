package com.skillsup.service;

import com.skillsup.model.Response;
import com.skillsup.model.User;

public interface UserService {
    Response<User> login(String username, String pass, String choiceUserDB);

    Response<User> register(User user, String choiceUserDB);

    Response<User> delete(User user, String choiceUsersDB);

    Response<User> changeAccountBalance(User user, String choiceUsersDB);

    void printAllUsers(String choiceUsersDB);
}
