package com.skillsup.view;

import com.skillsup.model.Gender;
import com.skillsup.model.Response;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;
import com.skillsup.service.UserService;
import com.skillsup.service.UserServiceImpl;

import java.util.Scanner;

public class LoginMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserServiceImpl();

    String[] menuItems = new String[]{
            "1. Login",
            "2. Register",
            "9. Back",
            "0. Exit"
    };

    @Override
    public void show() {
        while (true) {
            for (String item : menuItems) {
                System.out.println(item);
            }
            System.out.println("Choose option:");

            switch (scanner.nextLine()) {
                case "1":
                    loginSubMenu();
                    break;
                case "2":
                    registerSubMenu();
                    break;
                case "9":
                    back();
                    break;
                case "0":
                    exitProgram();
                    break;
            }
        }
    }

    private void registerSubMenu() {
        System.out.println("your login:");
        String login = scanner.nextLine();

        System.out.println("your password:");
        String password = scanner.nextLine();

        System.out.println("do you want to fill personal info? (y/n) ");
        String procceding = scanner.nextLine();
        User user;
        if ("y".equalsIgnoreCase(procceding)) {
            Gender gender = getGenderInput();
            int age = (int) getDoubleInput("your age", scanner);
            user = new User(login, password, UserRole.CUSTOMER, gender, age);
        } else {
            user = new User(login, password, UserRole.CUSTOMER);
        }
        Response<User> registerResponse = userService.register(user);
        if (registerResponse.isSuccess()) {
            new ProductMenu().show();
        } else {
            System.out.println(registerResponse.getErrorMessage());
        }
    }

    private Gender getGenderInput() {
        System.out.println("your gender (m/f):");
        Gender gender = Gender.fromCode(scanner.nextLine());
        if (gender == null) {
            return getGenderInput();
        }
        return gender;
    }


    private void loginSubMenu() {
        System.out.println("your login:");
        String login = scanner.nextLine();

        System.out.println("your password:");
        String password = scanner.nextLine();

        Response<User> loginResponse = userService.login(login, password);
        if (loginResponse.isSuccess()) {
            User u = loginResponse.getData();
            System.out.println("user logged in :" + u);
            if (u.getUserRole().equals(UserRole.ADMIN)) {
                new AdminMenu().show();
            } else {
                new ProductMenu().show();
            }
        } else {
            System.out.println(loginResponse.getErrorMessage());
        }
    }

    @Override
    public void back() {
        exitProgram();
    }
}
