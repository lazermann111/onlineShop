package com.skillsup.dao;

import com.skillsup.model.User;
import com.skillsup.model.UserRole;

import java.io.*;

public class UserFileDao {
    static FileWriter fileWriter;
    static FileReader fileReader;
    static File source = new File("USERS.txt");
    static File forUpdating = new File("USERS2.txt");

    static {
        User user = new User("admin", "admin", UserRole.ADMIN);
        try {
            fileWriter = new FileWriter(source, true);
            String usersInfo = user.getUsername() + " " + user.getPassword() + " "
                    + user.getAge() + " " + user.getGender() + " " + user.getUserRole() + "\n";
            fileWriter.write(usersInfo);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {
        String usersInfo = user.getUsername() + " " + user.getPassword() + " "
                + user.getAge() + " " + user.getGender() + " " + user.getUserRole() + "\n";
        try {
            fileWriter.write(usersInfo);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        try {
            String line;
            String usersInfo = user.getUsername() + " " + user.getPassword() + " "
                    + user.getAge() + " " + user.getGender() + " " + user.getUserRole();
            fileReader = new FileReader(source);
            fileWriter = new FileWriter(forUpdating, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals(usersInfo)) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
