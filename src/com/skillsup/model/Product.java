package com.skillsup.model;

import java.math.BigDecimal;

public class Product {
    private long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal cost;
    private long amount;

    public Product(String name, String description, String category, BigDecimal cost, long amount) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.cost = cost;
        this.amount = amount;
    }

    public Product(String name, BigDecimal cost, long amount) {
        this.name = name;
        this.category = "UNKNOWN";
        this.cost = cost;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", cost=" + cost +
                ", amount=" + amount +
                '}';
    }
}
