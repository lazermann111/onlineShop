package com.skillsup.view;

import com.skillsup.model.Gender;

import java.util.Scanner;

public interface Menu {
    void show();

    void back();

    default void ifContinueMenu(Scanner s) {
        double choiceContinue = getDoubleInput("Input \"1\" to continue or \"0\" to exit to super menu: ", s);
        if (choiceContinue != 0 && choiceContinue != 1) {ifContinueMenu(s);}
        else if (choiceContinue==0) {back();}
    }

    default Gender getGenderInputMenu(Scanner s) {
        System.out.println("Input your gender (m/f):");
        Gender gender = Gender.fromCode(s.nextLine());
        return (gender == null ? getGenderInputMenu(s) : gender);
    }

    default String chooseUsersDBmenu(Scanner s) {
        System.out.println("Select the option of storing user information:\n" +
                "0 - storage in RAM\n" +
                "1 - storage in file");
        String string = s.nextLine();
        return (!string.equals("0") && !string.equals("1") ? chooseUsersDBmenu(s) : string);
    }

    default String chooseYesOrNoMenu(String request, Scanner s) {
        System.out.println(request);
        String string = s.nextLine();
        return (!"y".equalsIgnoreCase(string) && !"n".equalsIgnoreCase(string) ? chooseYesOrNoMenu(request, s) : string);
    }

    default double getDoubleInput(String request, Scanner s) {
        System.out.println(request);
        try {
            String numb = s.nextLine();
            return Double.parseDouble(numb);
        } catch (NumberFormatException e) {
            return getDoubleInput(request, s);
        }
    }

    default void exitProgram() {
        System.exit(0);
    }
}
