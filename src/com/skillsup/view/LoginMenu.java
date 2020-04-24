package com.skillsup.view;

import com.skillsup.model.*;
import com.skillsup.service.UserService;
import com.skillsup.service.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class LoginMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserServiceImpl();

    String[] menuItems = new String[]{
            "1. Login + enter to product menu",
            "2. Register new user + enter to product menu",
            "3. Update user info + back to login menu",
            "4. Delete user + back to login menu",
            "5. Change account balance + back to login menu",
            "6. Get all users + back to login menu",
            "7. Back = exit",
            "8. Exit"
    };

    @Override
    public void show() {
        while (true) {
            for (String item : menuItems) {
                System.out.println(item);
            }
            System.out.println("Select action with user information:");

            switch (scanner.nextLine()) {
                case "1":
                    loginSubMenu(chooseUsersDBmenu(scanner));
                    break;
                case "2":
                    registerUserSubMenu(chooseUsersDBmenu(scanner));
                    break;
                case "3":
                    updateUserSubMenu(chooseUsersDBmenu(scanner));
                    break;
                case "4":
                    deleteUserSubMenu(chooseUsersDBmenu(scanner));
                    break;
                case "5":
                    changeAccountBalanceSubMenu(chooseUsersDBmenu(scanner));
                    break;
                case "6":
                    getAllUsersSubMenu(chooseUsersDBmenu(scanner));
                    break;
                case "7":
                    back();
                    break;
                case "8":
                    exitProgram();
                    break;
            }
        }
    }

    private void loginSubMenu(String choiceUserDB) {
        System.out.println("Input your login:");
        String login = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        Response<User> loginResponse = userService.login(login, password, choiceUserDB);
        if (loginResponse.isSuccess()) {
            User user = loginResponse.getData();
            System.out.println("User logged in :" + user);
            new ProductMenu(user).show();
        } else {
            System.out.println(loginResponse.getFinalMessage());
        }
    }

    private void registerUserSubMenu(String choiceUserDB) {
        System.out.println("Input your login:");
        String login = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        String procceding = chooseYesOrNoMenu("Would You like to fill personal info? (y/n) ", scanner);
        User user;
        if ("y".equalsIgnoreCase(procceding)) {
            Gender gender = getGenderInputMenu(scanner);
            int age = (int) getDoubleInput("Input your age: ", scanner);
            BigDecimal accountBalance = BigDecimal.valueOf(getDoubleInput("Input your account balance: ", scanner));
            user = new User(login, password, UserRole.CUSTOMER, gender, age, accountBalance);
        } else {
            user = new User(login, password);
        }
        Response<User> registerResponse = userService.register(user, choiceUserDB);
        if (registerResponse.isSuccess()) {
            System.out.println(registerResponse.getFinalMessage());
            new ProductMenu(user).show();
        } else {
            System.out.println(registerResponse.getFinalMessage());
        }
    }

    private void updateUserSubMenu(String choiceUserDB) {
        System.out.println("Input your login:");
        String login = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        Response<User> loginResponse = userService.login(login, password, choiceUserDB);
        if (loginResponse.isSuccess()) {
            User user = loginResponse.getData();
            System.out.println("Current user info: " + user.toStringTotal());
            String procceding = chooseYesOrNoMenu("Would You like to change user's info? (y/n): ", scanner);
            if ("y".equalsIgnoreCase(procceding)) {
                userService.delete(user, choiceUserDB);
                System.out.println("Input your new login:");
                String newLogin = scanner.nextLine();
                System.out.println("Input your new password:");
                String newPassword = scanner.nextLine();
                String procced = chooseYesOrNoMenu("Would You like to fill personal info? (y/n) ", scanner);
                User newUser;
                if ("y".equalsIgnoreCase(procced)) {
                    Gender newGender = getGenderInputMenu(scanner);
                    int newAge = (int) getDoubleInput("Input your new age: ", scanner);
                    BigDecimal newAccountBalance = BigDecimal.valueOf(getDoubleInput("Input your new account balance: ", scanner));
                    newUser = new User(newLogin, newPassword, UserRole.CUSTOMER, newGender, newAge, newAccountBalance);
                } else {
                    newUser = new User(newLogin, newPassword);
                }
                Response<User> registerResponse = userService.register(newUser, choiceUserDB);
                System.out.println(registerResponse.getFinalMessage());
            } else System.out.println("Rejection of changes is accepted!");
        } else {
            System.out.println(loginResponse.getFinalMessage());
        }
    }

    private void deleteUserSubMenu(String choiceUserDB) {
        System.out.println("Input your login:");
        String login = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        Response<User> loginResponse = userService.login(login, password, choiceUserDB);
        if (loginResponse.isSuccess()) {
            User user = loginResponse.getData();
            System.out.println("User logged in :" + user);
            String procceding = chooseYesOrNoMenu("Would You like to delete this user? (y/n): ", scanner);
            if ("y".equalsIgnoreCase(procceding)) {
                System.out.println(userService.delete(user, choiceUserDB).getFinalMessage());
            } else System.out.println("Rejection of deleting is accepted!");
        } else {
            System.out.println(loginResponse.getFinalMessage());
        }
    }

    private void changeAccountBalanceSubMenu(String choiceUserDB) {
        System.out.println("Input your login:");
        String login = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        Response<User> loginResponse = userService.login(login, password, choiceUserDB);
        if (loginResponse.isSuccess()) {
            User user = loginResponse.getData();
            System.out.println("Current user info: " + user.toStringTotal());
            String procceding = chooseYesOrNoMenu("Would You like to change account balance? (y/n): ", scanner);
            if ("y".equalsIgnoreCase(procceding)) {
                userService.delete(user, choiceUserDB);
                user.setAccount(BigDecimal.valueOf(getDoubleInput("Input your new account balance: ", scanner)));
                Response<User> changeAccountResponse = userService.changeAccountBalance(user, choiceUserDB);
                System.out.println(changeAccountResponse.getFinalMessage());
            } else System.out.println("Rejection of changes is accepted!");
        } else {
            System.out.println(loginResponse.getFinalMessage());
        }
    }

    private void getAllUsersSubMenu(String choiceUserDB) {
        System.out.println("Input your login:");
        String login = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        Response<User> loginResponse = userService.login(login, password, choiceUserDB);
        if (loginResponse.isSuccess()) {
            userService.printAllUsers(choiceUserDB);
        } else {
            System.out.println(loginResponse.getFinalMessage());
        }
    }

    @Override
    public void back() {
        exitProgram();
    }

}
