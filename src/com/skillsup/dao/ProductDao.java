package com.skillsup.dao;

import com.skillsup.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductDao {
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Product getByName(String productName);
    Product getById(long productName);
    List<Product> getByNameSubstring(String productName);
    List<Product> getAllProductz();
    Set<String> getAllProductCategories();
    List<Product> getByCategoryName(String categoryName);
    List<Product> filterProductsByPrice(BigDecimal min, BigDecimal max);
}
