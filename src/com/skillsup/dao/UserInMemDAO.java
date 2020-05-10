package com.skillsup.dao;

import com.skillsup.model.Gender;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInMemDAO implements UserDAO {
    static Map<String, User> userMap = new HashMap<>();

    static {
        User user = new User("admin", "admin", UserRole.ADMIN);
        userMap.put("admin", user);
    }

    @Override
    public void save(User user) {
        userMap.put(user.getUsername(), user);
    }

    @Override
    public void update(User user) {
        userMap.put(user.getUsername(), user);
    }

    @Override
    public void delete(User user) {
        userMap.remove(user.getUsername());
    }

    @Override
    public User get(String username) {
        return userMap.get(username);
    }
}
