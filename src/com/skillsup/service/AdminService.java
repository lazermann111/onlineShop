package com.skillsup.service;

import com.skillsup.model.User;

import java.util.Map;

public interface AdminService {
    Map<String, User> getUsers();
    void deleteUser(String username);
}
