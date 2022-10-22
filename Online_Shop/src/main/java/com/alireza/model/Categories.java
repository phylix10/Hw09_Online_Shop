package com.alireza.model;

import com.alireza.model.enumeration.Category;

public class Categories {
    private int id;
    private Category category;
    private Category subCategory;

    public Categories(String category, String subCategory) {
        this.category = Category.valueOf(category);
        this.subCategory = Category.valueOf(subCategory);
    }

    public Categories() {
    }

    public Categories(int id) {
        this.id = id;
    }

    public Categories(int id, String category, String subCategory) {
        this.id = id;
        this.category = Category.valueOf(category);
        this.subCategory = Category.valueOf(subCategory);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }
}
