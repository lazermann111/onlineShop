package com.skillsup.service;

import com.skillsup.model.Gender;
import com.skillsup.model.Response;
import com.skillsup.model.User;

public interface UserService {
    Response<User> login(String username, String pass);
    Response<User> register(User user);
    void deleteUser(User user);
}
