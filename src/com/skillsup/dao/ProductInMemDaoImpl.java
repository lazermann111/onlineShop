package com.skillsup.dao;

import com.skillsup.model.Product;
import com.skillsup.model.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductInMemDaoImpl implements ProductDao {
    private static List<Product> daoInMem = new ArrayList<>();

    static {
        daoInMem.add(new Product("Bread", BigDecimal.valueOf(15), 100));
        daoInMem.add(new Product("Salt", BigDecimal.valueOf(6), 100));
        daoInMem.add(new Product("Shampoo", "Shampoo", "Chemicals", BigDecimal.valueOf(55), 100));
    }

    @Override
    public void save(Product product) {
        if (product == null || product.getName().equals("")) {
            System.out.println("The product is not saved! PRODUCT IS ABSENT OR EMPTY PRODUCT NAME! ");
        } else {
            if (!daoInMem.contains(product)) {
                daoInMem.add(product);
                System.out.println("The product is saved! ");
            } else {
                System.out.println("The product is not saved! ");
            }
        }
    }

    @Override
    public void update(Product product) {
        if (product == null || product.getName().equals("")) {
            System.out.println("The product is not updated! PRODUCT IS ABSENT OR EMPTY PRODUCT NAME! ");
        } else {
            int i = 0;
            for (Product p : daoInMem) {
                if (p.getId() == product.getId()) {
                    p = product;
                    i++;
                    System.out.println("Product info is updated! ");
                    break;
                }
            }
            if (i == 0) {
                System.out.println("Product info is not updated! ");
            }
        }
    }

    @Override
    public void delete(Product product) {
        daoInMem.remove(product);
        System.out.println("Product is deleted! ");
    }

    @Override
    public Product getByName(String productName) {
        for (Product p : daoInMem) {
            if (p.getName().equals(productName)) {
                return p;
            }
        }
        return null;
//        return database.stream()
        // .filter(p -> p.getName().equals(productName))
        // .findAny()
        // .orElse(null); todo learn about Stream API!
    }

    public Product getById(long id) {
        return daoInMem.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Product> getByNameSubstring(String productName) {
        return daoInMem.stream()
                .filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProductz() {
        return new ArrayList<>(daoInMem);
    }

    @Override
    public Set<String> getAllProductCategories() {
        Set<String> res = new HashSet<>();
        for (Product product : daoInMem) {
            res.add(product.getCategory());
        }
        return res;
    }

    @Override
    public List<Product> getByCategoryName(String categoryName) {
        List<Product> result = new ArrayList<>();
        for (Product p : daoInMem) {
            if (p.getCategory().equalsIgnoreCase(categoryName)) {
                result.add(p);
            }
        }
        return result;
        //return database
        // .stream()
        // .filter(p -> p.getCategory().equals(categoryName))
        // .collect(Collectors.toList()); todo learn about Stream API!
    }



    @Override
    public List<Product> filterProductsByPrice(BigDecimal min, BigDecimal max) {
        List<Product> result = new ArrayList<>();
        for (Product p : daoInMem) {
            if (p.getCost().compareTo(min) >= 0 &&
                    p.getCost().compareTo(max) <= 0) {
                result.add(p);
            }
        }
        return result;
    }


}
