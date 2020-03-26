package com.skillsup.dao;

import com.skillsup.model.Product;
import com.skillsup.model.User;

import java.util.List;

public interface ProductDao {
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Product get(String productName);
    List<Product> getAllProducts();
    List<Product> getByCategoryName(String categoryName);
    List<Product> filterProductsByPrice(int min, int max);
}
