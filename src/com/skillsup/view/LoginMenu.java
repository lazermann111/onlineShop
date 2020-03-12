package com.skillsup.view;

import com.skillsup.model.Response;
import com.skillsup.model.User;
import com.skillsup.sluzhba.UserService;
import com.skillsup.sluzhba.UserServiceImpl;

import java.util.Scanner;

public class LoginMenu implements Menu {
    String[] menuItems = new String[]{
            "1. Login",
            "2. Register",
            "9. Back",
            "0. Exit"
    };
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserServiceImpl();

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
                    System.out.println("Registered!");
                    break;

            }
        }

    }

    private void loginSubMenu()
    {
        System.out.println("your login:");
        String login = scanner.nextLine();

        System.out.println("your password:");
        String password = scanner.nextLine();

        Response<User> loginResponse = userService.login(login, password);
        if (loginResponse.isSuccess()) {
            User u = loginResponse.getData();
            System.out.println("user logged in :" + u);
            //todo add product menu
        } else {
            System.out.println(loginResponse.getErrorMessage());
        }
    }

    @Override
    public void back() {
        exitProgram();
    }
}
