package com.alireza.model;

public class Cart {
    private int id;
    private User userId;
    private Product productId;
    private int productCount;
    private int totalPrice;
    private boolean finalApproval;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isFinalApproval() {
        return finalApproval;
    }

    public void setFinalApproval(boolean finalApproval) {
        this.finalApproval = finalApproval;
    }
}
