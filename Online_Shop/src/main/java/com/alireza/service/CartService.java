package com.alireza.service;

import com.alireza.model.Cart;
import com.alireza.repository.CartRepository;

import java.util.List;

public class CartService {
    public void addProductToCart(int userId, int productId, int productCount, int totalPrice){
        CartRepository.addProductToCart(userId, productId, productCount, totalPrice);
    }
    public List<Cart> showCartDetail(int userId){
        return CartRepository.showDetailCart(userId);
    }

    public void deleteProduct(int id){
        CartRepository.deleteProductFromCart(id);
    }

    public void updateConfirmation(int id){
        CartRepository.updateConfirmationPurchases(id);
    }

    public void deleteCart(int userId){
        CartRepository.deleteCart(userId);
    }
}
