package com.alireza.model;

public class Product {
    private int id;
    private String name;
    private Categories categoryId;
    private int stock;
    private int price;

    public Product(int id, String name, Categories categoryId, int stock, int price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.stock = stock;
        this.price = price;
    }

    public Product(String name, Categories categoryId, int stock, int price) {
        this.name = name;
        this.categoryId = categoryId;
        this.stock = stock;
        this.price = price;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
