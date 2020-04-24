package com.skillsup.dao;

import com.skillsup.model.Gender;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UserInFileDaoImpl implements UserDAO {
    private static final String PATH_USER_DB_FILE = "E:\\onlineShop\\src\\com\\skillsup\\resources\\userDB.txt";
    private static final User ADMIN = new User("admin", "admin", UserRole.ADMIN);

    @Override
    public void saveUser(User user) {
        try {
            FileWriter fileWriter = new FileWriter(PATH_USER_DB_FILE, true);
            String userString = user.getUsername() + "!" + user.getPassword() + "!" +
                    user.getUserRole() + "!" + user.getGender() + "!" + user.getAge() + "!" + user.getAccount() + "\n";
            fileWriter.append(userString);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        Map<String, User> userMap = getUserMapFromFile();
        userMap.remove(user.getUsername());
        try {
            Files.delete(Paths.get(PATH_USER_DB_FILE));
            if (userMap.size() > 1) {
                userMap.forEach((s, user1) -> saveUser(user1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) {
        return getUserMapFromFile().get(username);
    }

    @Override
    public Collection<User> getUserList() {
        Map<String, User> userMap = getUserMapFromFile();
        return userMap.values();
    }

    public Map<String, User> getUserMapFromFile() {
        Map<String, User> userMap = new HashMap<>();
        List<String> userLines = new ArrayList<>();
        try {
            if (new File(PATH_USER_DB_FILE).canRead()) {
                userLines = Files.readAllLines(Paths.get(PATH_USER_DB_FILE));
            } else {
                new File(PATH_USER_DB_FILE);
                saveUser(ADMIN);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String ul : userLines) {
            String[] str = ul.split("!");
            User user = new User(str[0], str[1], UserRole.valueOf(str[2]), Gender.valueOf(str[3]),
                    Integer.parseInt(str[4]), BigDecimal.valueOf(Double.parseDouble(str[5])));
            userMap.put(user.getUsername(), user);
        }
        return userMap;
    }
}
