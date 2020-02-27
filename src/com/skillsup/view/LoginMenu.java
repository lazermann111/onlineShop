package com.skillsup.view;

import java.util.Scanner;

public class LoginMenu implements Menu {
    String[] menuItems = new String[]{
            "1. Login",
            "2. Register",
            "9. Back",
            "0. Exit"
    };

    @Override
    public void show() {
        for (String item : menuItems)
        {
            System.out.println(item);
        }

        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt())
        {
            case 1 :
                System.out.println("your login:");
                String login = scanner.nextLine();

                System.out.println("your password:");
                String password = scanner.nextLine();



                break;
            case 2 :
                System.out.println("Registered!");
                break;
        }

    }


    @Override
    public void back() {
        exitProgram();
    }
}
