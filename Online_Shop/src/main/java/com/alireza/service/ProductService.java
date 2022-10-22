package com.alireza.service;

import com.alireza.model.Product;
import com.alireza.repository.ProductRepository;

import java.util.List;

public class ProductService {
    public boolean createProduct(Product product) {
        if (ProductRepository.find(product) == null) {
            ProductRepository.createProduct(product);
            return true;
        }
        else {
            return false;
        }
    }

    public void updateProduct(Product product){
        ProductRepository.update(product);
    }

    public void deleteProduct(int id){
        ProductRepository.delete(id);
    }

    public List<Product> showAllIProduct(){
        return ProductRepository.findAll();
    }

    public Product showPrice(int id){
        return ProductRepository.findPrice(id);
    }

    public void updateProductStock(int stock, int id){
        ProductRepository.updateStock(stock, id);
    }
}
