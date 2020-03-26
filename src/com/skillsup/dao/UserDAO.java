package com.skillsup.dao;

import com.skillsup.model.User;

public interface UserDAO {
    void save(User user);
    void update(User user);
    void delete(User user);
    User get(String username);
}
