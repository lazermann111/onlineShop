package com.skillsup.dao;

import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.util.*;

public class UserInMemDaoImpl implements UserDAO {
    static Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("admin", new User("admin", "admin", UserRole.ADMIN));
    }

    @Override
    public void saveUser(User user) {
        userMap.put(user.getUsername(), user);
    }

    @Override
    public void deleteUser(User user) {
        userMap.remove(user.getUsername());
    }

    @Override
    public User getUser(String username) {
        return userMap.get(username);
    }

    public Collection<User> getUserList() {
        return userMap.values();
    }
}
