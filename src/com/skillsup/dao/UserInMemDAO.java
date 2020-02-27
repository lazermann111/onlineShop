package com.skillsup.dao;

import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.util.HashMap;
import java.util.Map;

public class UserInMemDAO implements UserDAO {
    static  Map<String, User> userMap = new HashMap<>();
    static
    {
       userMap.put("admin", new User("admin", "admin", UserRole.ADMIN));
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void get(String username) {

    }
}
