package com.skillsup.view;

import java.util.Scanner;

public interface Menu {
    void show();
    void back();

    default void exitProgram() {
        System.exit(0);
    }

    default double getDoubleInput(String request, Scanner s) {
        System.out.println(request);
        try {
            String age = s.nextLine();
            return Integer.parseInt(age);
        } catch (NumberFormatException e) {
            return getDoubleInput(request, s);
        }
    }
}
