package com.skillsup.dao;

import com.skillsup.model.User;

import java.io.IOException;
import java.util.Map;

public interface UserDAO {
    void save(User user);
    void update(User user);
    void delete(User user);
    User get(String username);
    Map<String, User> getMapOfUsersFromFile() throws IOException;
}
