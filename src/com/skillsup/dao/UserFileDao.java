package com.skillsup.dao;

import com.skillsup.model.Gender;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFileDao implements UserDAO {
    static File source = new File("USERS.txt");

    static {
        User user = new User("admin", "admin", UserRole.ADMIN);
        try {
            FileWriter fileWriter = new FileWriter(source, true);
            String usersInfo = user.getUsername() + " " + user.getPassword() + " "
                    + user.getAge() + " " + user.getGender() + " " + user.getUserRole() + "\n";
            fileWriter.write(usersInfo);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {
        String usersInfo = user.getUsername() + " " + user.getPassword() + " "
                + user.getAge() + " " + user.getGender() + " " + user.getUserRole() + "\n";
        try {
            FileWriter fileWriter = new FileWriter(source, true);
            fileWriter.write(usersInfo);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            getMapOfUsersFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            getMapOfUsersFromFile().forEach((string, users) -> save(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        try {
            Map<String, User> userMap = getMapOfUsersFromFile();
            userMap.remove(user.getUsername());
            Files.delete(Paths.get("USERS.txt"));
            userMap.forEach((string, users) -> save(users));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String username) {
        Map<String, User> getUser = new HashMap<>();
        return getUser.get(username);
    }

    public Map<String, User> getMapOfUsersFromFile() throws IOException {
        Map<String, User> userMap = new HashMap<>();
        List<String> usersFromFile = new ArrayList<>();
        try {
            if (source.canRead()) {
                usersFromFile = Files.readAllLines(Paths.get("USERS.txt"));
            } else {
                usersFromFile = Files.readAllLines(Paths.get("USERS2.txt"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : usersFromFile) {
            String[] splittedString = s.split(" ");
            User user;
            if (splittedString[3].equalsIgnoreCase("male")){
                user = new User(splittedString[0], splittedString[1], UserRole.valueOf(splittedString[4]),
                        Gender.MALE, Integer.parseInt(splittedString[2]));
            } if (splittedString[3].equalsIgnoreCase("female")){
                user = new User(splittedString[0], splittedString[1], UserRole.valueOf(splittedString[4]),
                        Gender.FEMALE, Integer.parseInt(splittedString[2]));
            } else {
                user = new User(splittedString[0], splittedString[1], UserRole.valueOf(splittedString[4]),
                        Gender.NULL, Integer.parseInt(splittedString[2]));
            }
            userMap.put(user.getUsername(), user);
        }
        return userMap;
    }
}
