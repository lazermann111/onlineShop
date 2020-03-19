package com.skillsup.view;

import com.skillsup.dao.ProductDao;
import com.skillsup.dao.ProductInMemDaoImpl;

import java.util.Scanner;

public class ProductMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    ProductDao productDao = new ProductInMemDaoImpl(); // todo replace with ProductService

    String[] menuItems = new String[]{
            "1. List all categories",
            "2. List all products",
            "3. List products by category",
            "4. Search product by name",
            "5. Filter products by price",
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
                    System.out.println("to be done!");
                    break;

                case "2":
                    productDao
                            .getAllProducts()
                            .forEach(System.out::println);
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
}
