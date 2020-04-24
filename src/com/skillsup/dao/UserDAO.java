package com.skillsup.dao;

import com.skillsup.model.User;

import java.util.Collection;

public interface UserDAO {
    void saveUser(User user);

    void deleteUser(User user);

    User getUser(String username);

    Collection<User> getUserList();
}
