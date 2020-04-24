package com.skillsup.model;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private UserRole userRole=UserRole.CUSTOMER;
    private Gender gender=Gender.UNKNOWN;
    private int age;
    private BigDecimal account=BigDecimal.valueOf(0);

    public User(String username, String password, UserRole userRole, Gender gender, int age, BigDecimal account) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.gender = gender;
        this.age = age;
        this.account = account;
    }
// этот конструктор нужен только ради админа в оперативке:
    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public BigDecimal getAccount() {
        return account;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setUserRole(UserRole userRole) {
//        this.userRole = userRole;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
    public void setAccount(BigDecimal account) {
        this.account = account;
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
        return " User{" +
                "username = '" + username + '\'' +
                ", userRole = " + userRole +
                ", gender = " + gender +
                ", age = " + age +
                ", account balance= " + account +
                '}';
    }

    public String toStringTotal() {
        return " User{" +
                "login = '" + username + '\'' +
                ", password = " + password +
                ", authority = " + userRole +
                ", gender = " + gender +
                ", age = " + age +
                ", account balance= " + account +
                '}';
    }
}
