package com.skillsup.service;

import com.skillsup.dao.UserDAO;
import com.skillsup.dao.UserFileDao;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.io.IOException;
import java.util.Map;

public class AdminServiceImpl implements AdminService {
    static UserDAO userFileDao = new UserFileDao();
    @Override
    public Map<String, User> getUsers() {
        try {
            return userFileDao.getMapOfUsersFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(String username) {
        Map<String, User> allUsers = null;
        try {
            allUsers = userFileDao.getMapOfUsersFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (allUsers.containsKey(username)){
            allUsers.forEach((s, user) -> {
                if (user.getUsername().equals(username) && !user.getUserRole().equals(UserRole.ADMIN)) {
                        userFileDao.delete(user);
                }
            });
        }
    }
}
