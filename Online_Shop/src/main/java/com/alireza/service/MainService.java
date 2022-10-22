package com.alireza.service;

import com.alireza.model.Cart;
import com.alireza.model.Categories;
import com.alireza.model.Product;
import com.alireza.model.User;

import java.util.List;

public class MainService {
    private User loggedInUser = null;
    private User registeredUser = null;
    UserService userService = new UserService();
    ProductService productService = new ProductService();
    CartService cartService = new CartService();

    CategoryService categoryService = new CategoryService();

    public void register(User user) throws RuntimeException{
        if (userService.findUser(user.getUsername(),user.getNationalCode()) == null) {
            int userId = userService.createUser(user);
            user.setId(userId);
            registeredUser = user;
        } else {
            throw new RuntimeException();
        }
    }

    public void showAllProduct () {
        List<Product> productList = productService.showAllIProduct();
        System.out.println("________________________________________________________________________");
        System.out.println("| Id  |    Name     |  Category    | SubCategory |  Stock |  Price     |");
        System.out.println("________________________________________________________________________");
        for (Product product : productList) {
            System.out.printf("|  %-3s|  %-11s|  %-12s|    %-9s|   %-5s|  %-10s|%n",
                    product.getId(),
                    product.getName(),
                    product.getCategoryId().getCategory(),
                    product.getCategoryId().getSubCategory(),
                    product.getStock(),
                    product.getPrice());
            System.out.println("________________________________________________________________________");
        }
    }

    public void addProductToCartForRegister(int productId, int productCount, int totalPrice){
        cartService.addProductToCart(registeredUser.getId(), productId, productCount, totalPrice);
    }

    public Product showProductPrice(int id){
        return productService.showPrice(id);
    }

    public List<Cart> showCartDetailForRegister(){
        return cartService.showCartDetail(registeredUser.getId());
    }

    public void showCartForRegister () {
        List<Cart> cartList = cartService.showCartDetail(registeredUser.getId());
        if (cartList.size() != 0) {
            System.out.println("__________________________________________________________________________________________________________________________________");
            System.out.println("| Id  |   Username    |   Product Id   |  Product Name   |   Category    | SubCategory |  Price     |   Count   |  Total Price   |");
            System.out.println("__________________________________________________________________________________________________________________________________");
            for (Cart cart : cartList) {
                System.out.printf("|  %-3s|    %-11s|      %-10s|  %-15s|   %-12s|    %-9s|  %-10s|     %-6s|  %-14s|%n",
                        cart.getId(),
                        cart.getUserId().getUsername(),
                        cart.getProductId().getId(),
                        cart.getProductId().getName(),
                        cart.getProductId().getCategoryId().getCategory(),
                        cart.getProductId().getCategoryId().getSubCategory(),
                        cart.getProductId().getPrice(),
                        cart.getProductCount(),
                        cart.getTotalPrice());
                System.out.println("__________________________________________________________________________________________________________________________________");
            }
        }
        else {
            System.out.println("You have no product in your cart");
        }
    }

    public void deleteProductFromCart(int id){
        cartService.deleteProduct(id);
    }

    public void updateConfirmationForRegister(){
        cartService.updateConfirmation(registeredUser.getId());
    }

    public void updateProductStockForRegister(){
        List<Cart> cartList = cartService.showCartDetail(registeredUser.getId());

        for (Cart cart : cartList){
            int id = cart.getProductId().getId();
            int stock = cart.getProductCount();
            productService.updateProductStock(stock, id);
        }
    }

    public void deleteCartForRegister(){
        cartService.deleteCart(registeredUser.getId());
    }

    public boolean login(String username, String password, String nationalCode) {
        User user = userService.findUser(username, nationalCode);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            } else {
                throw new RuntimeException("The password is not correct!");
            }
        } else {
            return false;
        }
    }

    public User findUser(String username, String nationalCode){
        return userService.findUser(username, nationalCode);
    }

    public void addProductToCartForLogin(int productId, int productCount, int totalPrice){
        cartService.addProductToCart(loggedInUser.getId(), productId, productCount, totalPrice);
    }

    public List<Cart> showCartDetailForLogin(){
        return cartService.showCartDetail(loggedInUser.getId());
    }

    public void showCartForLogin () {
        List<Cart> cartList = cartService.showCartDetail(loggedInUser.getId());
        if (cartList.size() != 0) {
            System.out.println("__________________________________________________________________________________________________________________________________");
            System.out.println("| Id  |   Username    |   Product Id   |  Product Name   |   Category    | SubCategory |  Price     |   Count   |  Total Price   |");
            System.out.println("__________________________________________________________________________________________________________________________________");
            for (Cart cart : cartList) {
                System.out.printf("|  %-3s|    %-11s|      %-10s|  %-15s|   %-12s|    %-9s|  %-10s|     %-6s|  %-14s|%n",
                        cart.getId(),
                        cart.getUserId().getUsername(),
                        cart.getProductId().getId(),
                        cart.getProductId().getName(),
                        cart.getProductId().getCategoryId().getCategory(),
                        cart.getProductId().getCategoryId().getSubCategory(),
                        cart.getProductId().getPrice(),
                        cart.getProductCount(),
                        cart.getTotalPrice());
                System.out.println("__________________________________________________________________________________________________________________________________");
            }
        }
        else {
            System.out.println("You have no product in your cart");
        }
    }

    public void updateConfirmationForLogin(){
        cartService.updateConfirmation(loggedInUser.getId());
    }

    public void updateProductStockForLogin(){
        List<Cart> cartList = cartService.showCartDetail(loggedInUser.getId());

        for (Cart cart : cartList){
            int id = cart.getProductId().getId();
            int stock = cart.getProductCount();
            productService.updateProductStock(stock, id);
        }
    }

    public void deleteCartForLogin(){
        cartService.deleteCart(loggedInUser.getId());
    }

    public boolean addProduct(Product product){
        return productService.createProduct(product);
    }

    public void showAllCategory(){
        List<Categories> categoryList = categoryService.findAll();
        System.out.println("_____________________________________");
        System.out.println("| Id  |   Category    | SubCategory |");
        System.out.println("_____________________________________");
        for (Categories categories : categoryList) {
            System.out.printf("|  %-3s|   %-12s|  %-11s|%n",
                    categories.getId(),
                    categories.getCategory(),
                    categories.getSubCategory());
            System.out.println("_____________________________________");
        }
    }

    public void editProductDetail(Product product){
        productService.updateProduct(product);
    }

    public void deleteProduct(int id){
        productService.deleteProduct(id);
    }

    public boolean addCategory(Categories categories){
        return categoryService.createCategory(categories);
    }

    public void editCategory(Categories categories){
        categoryService.updateCategory(categories);
    }
}
