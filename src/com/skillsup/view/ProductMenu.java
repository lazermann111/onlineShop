package com.skillsup.view;

import com.skillsup.dao.ProductDao;
import com.skillsup.dao.ProductInMemDaoImpl;
import com.skillsup.model.Product;
import com.skillsup.model.User;
import com.sun.jdi.LongValue;

import java.math.BigDecimal;
import java.util.*;

public class ProductMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    ProductDao productDao = new ProductInMemDaoImpl(); // todo replace with ProductService
    private User currentUser;

    String[] menuItems = new String[]{
            "1. List all categories",
            "2. List all products",
            "3. List products by category",
            "4. Search product by name",
            "5. Filter products by price",
            "6. Add new product",
            "7. Update product",
            "8. Delete product",
            "9. Back",
            "0. Exit"
    };

    public ProductMenu (User user) {
        currentUser = user;
    }
    @Override
    public void show() {
        while (true) {
            for (String item : menuItems) {
                System.out.println(item);
            }
            System.out.println("Choose option:");

            switch (scanner.nextLine()) {
                case "1":
                    productDao
                            .getAllProductCategories()
                            .forEach(System.out::println);
                    ifContinueMenu(scanner);
                    break;
                case "2":
                    productDao
                            .getAllProductz()
                            .forEach(System.out::println);
                    ifContinueMenu(scanner);
                    break;
                case "3":
                    System.out.println("Input search category:");
                    String category = scanner.nextLine();
                    productDao
                            .getByCategoryName(category)
                            .forEach(System.out::println);
                    ifContinueMenu(scanner);
                    break;
                case "4":
                    System.out.println("Input search product:");
                    String name = scanner.nextLine();
                    productDao
                            .getByNameSubstring(name)
                            .forEach(System.out::println);
                    ifContinueMenu(scanner);
                    break;
                case "5":
                    BigDecimal min = BigDecimal.valueOf(getDoubleInput("input min price:", scanner)) ;
                    BigDecimal max = BigDecimal.valueOf(getDoubleInput("input max price:", scanner));
                    productDao
                            .filterProductsByPrice(min, max)
                            .forEach(System.out::println);
                    ifContinueMenu(scanner);
                    break;
                case "6":
//                    if (!currentUser.getUserRole().equals(UserRole.ADMIN)) {
//                    System.out.println("Sorry, You do not have the authority for this operation! ");
//                    break;
//                    }
                    System.out.println("Input new product's info: ");
                    System.out.println("Name: "); String newName = scanner.nextLine();
                    System.out.println("Description: "); String newDescription = scanner.nextLine();
                    System.out.println("Category: "); String newCategory = scanner.nextLine();
                    BigDecimal newCost = BigDecimal.valueOf(getDoubleInput("Cost (with \".\" as a decimal separator): ", scanner));
                    long newAmount = (long) getDoubleInput("Amount (integer - the fractional part will be discarded): ", scanner);
                    productDao.save(new Product(newName, newDescription, newCategory, newCost, newAmount));
                    ifContinueMenu(scanner);
                    break;
                case "7":
                    System.out.println("Input name of product You want to update: ");
                    name = scanner.nextLine();
                    new ArrayList<>(productDao.getByNameSubstring(name)).forEach(System.out::println);
//                    System.out.println("Choose and input ID of product You want to update: ");
                    long id = (long) getDoubleInput("Choose and input ID of product You want to update: ", scanner);
                    Product p = productDao.getById(id);
                    if (p!=null) {
                    System.out.println("Input new product information: \n"+
                                       "Name: "); p.setName(scanner.nextLine());
                    System.out.println("Description: "); p.setDescription(scanner.nextLine());
                    System.out.println("Category: "); p.setCategory(scanner.nextLine());
                    p.setCost(BigDecimal.valueOf(getDoubleInput("Cost (with \".\" as a decimal separator): ", scanner)));
                    p.setAmount((long) getDoubleInput("Amount (integer - the fractional part will be discarded): ", scanner));
                    productDao.update(p);}
                    else { System.out.println("Product with such ID is absent! ");}
                    ifContinueMenu(scanner);
                    break;
                case "8":
                    System.out.println("Input name of product You want to delete: ");
                    name = scanner.nextLine();
                    new ArrayList<>(productDao.getByNameSubstring(name)).forEach(System.out::println);
//                    System.out.println("Choose and input ID of product You want to delete: ");
//                    id = Long.parseLong(scanner.nextLine());
                    id = (long) getDoubleInput("Choose and input ID of product You want to delete: ", scanner);
                    p = productDao.getById(id);
                    if (p!=null) {productDao.delete(p);}
                    else { System.out.println("Product with such ID is absent! ");}
                    ifContinueMenu(scanner);
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
