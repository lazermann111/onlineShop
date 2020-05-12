package com.skillsup.view;

import com.skillsup.service.AdminService;
import com.skillsup.service.AdminServiceImpl;

import java.util.Scanner;

public class AdminMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private AdminService adminService = new AdminServiceImpl();

    String[] menuItems = new String[]{
            "1. Delete User",
            "2. Show Users",
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
                    userToDelete();
                    break;
                case "2":
                    showUsers();
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

    @Override
    public void back() {
        new LoginMenu().show();
    }

    private void userToDelete(){
        System.out.println("User to delete(his username):");
        String username = scanner.nextLine();
        adminService.deleteUser(username);
    }

    private void showUsers(){
        adminService.getUsers().forEach((s, user) -> System.out.println(user.toString()));
    }
}
