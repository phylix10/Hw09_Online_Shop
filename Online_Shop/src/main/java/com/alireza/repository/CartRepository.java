package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Cart;
import com.alireza.model.Categories;
import com.alireza.model.Product;
import com.alireza.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static final String ADD_PRODUCT_TO_CART_QUERY = "insert into cart(user_id,product_id,product_count,total_price) values (?,?,?,?)";
    private static final String SELECT_CART_PRODUCT_QUERY = "select * from cart c join product p on p.id = c.product_id " +
            "join users u on u.id = c.user_id join categories c2 on c2.id = p.category_id where u.id = ?";
    private static final String DELETE_PRODUCT_FROM_CART_QUERY = "delete from cart where product_id = ?";
    private static final String UPDATE_CONFIRMATION_QUERY = "update cart set final_approval = true where user_id = ?";
    private static final String DELETE_CART_QUERY = "delete from cart where user_id = ?";
    public static void addProductToCart(int userId, int productId, int productCount, int totalPrice) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_CART_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, productCount);
            preparedStatement.setInt(4, totalPrice);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();


        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static List<Cart> showDetailCart(int userId){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_PRODUCT_QUERY);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Cart> cartList = new ArrayList<>();
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getInt(1));
                cart.setUserId((new User(resultSet.getString(13))));
                cart.setProductId(new Product(
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        new Categories(resultSet.getString(18),resultSet.getString(19)),
                        resultSet.getInt(10),
                        resultSet.getInt(11)));
                cart.setProductCount(resultSet.getInt(4));
                cart.setTotalPrice(resultSet.getInt(5));
                cartList.add(cart);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return cartList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static void deleteProductFromCart(int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_FROM_CART_QUERY);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void updateConfirmationPurchases(int userId) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONFIRMATION_QUERY);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void deleteCart(int userId) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_QUERY);
            preparedStatement.setInt(1, userId);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}
