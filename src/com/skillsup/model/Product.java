package com.skillsup.model;

import java.math.BigDecimal;

public class Product {
    private static long count=0;
    private long id;
    private String name;
    private String description = "None";
    private String category = "UNKNOWN";
    private BigDecimal cost;
    private long amount;

    public Product(String name, String description, String category, BigDecimal cost, long amount) {
        this.id=count;
        this.name = name;
        this.description = description.equals("") ? "None" : description;
        this.category = category.equals("") ? "UNKNOWN" : category;
        this.cost = cost;
        this.amount = amount;
        count++;
    }

    public Product(String name, BigDecimal cost, long amount) {
        this.id=count;
        this.name = name;
        this.cost = cost;
        this.amount = amount;
        count++;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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
