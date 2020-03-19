package com.skillsup.dao;

import com.skillsup.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductInMemDaoImpl implements ProductDao {
    private static List<Product> database = new ArrayList<>();
    static {
        database.add(new Product("Bread", BigDecimal.valueOf(15), 100 ));
        database.add(new Product("Salt", BigDecimal.valueOf(6), 100 ));
        database.add(new Product("Shampoo","Shampoo", "Chemicals", BigDecimal.valueOf(55), 100 ));
    }

    @Override
    public void save(Product product) {
        if (!database.contains(product)) {
            database.add(product);
        }
    }

    @Override
    public void update(Product product) {
        // todo impl!
    }

    @Override
    public void delete(Product product) {
        database.remove(product);
    }

    @Override
    public Product get(String productName) {
        for (Product p : database) {
            if (p.getName().equals(productName)) {
                return p;
            }
        }
        return null;
        //return database.stream()
        // .filter(p -> p.getName().equals(productName))
        // .findAny()
        // .orElse(null); todo learn about Stream API!
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(database);
    }

    @Override
    public List<Product> getByCategoryName(String categoryName) {
        List<Product> result = new ArrayList<>();
        for (Product p : database) {
            if (p.getCategory().equals(categoryName)) {
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
    public List<Product> filterProductsByPrice(int min, int max) {
        List<Product> result = new ArrayList<>();
        for (Product p : database) {
            if (p.getCost().intValueExact() > min &&
                    p.getCost().intValueExact() < max) {
                result.add(p);
            }
        }
        return result;
    }
}
