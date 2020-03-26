package com.skillsup.model;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private UserRole userRole;
    private Gender gender;
    private int age;

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }


    public User(String username, String password, UserRole userRole, Gender gender, int age) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.gender = gender;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userRole=" + userRole +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
