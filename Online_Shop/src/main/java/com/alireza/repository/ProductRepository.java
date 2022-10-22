package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Categories;
import com.alireza.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String CREATE_QUERY = "insert into product (name, category_id, stock, price) VALUES (?,?,?,?)";
    private static final String FIND_ALL_QUERY = "select * from product p join categories c on c.id = p.category_id";
    private static final String FIND_QUERY = "select * from product p join categories c on c.id = p.category_id where p.name = ? and (c.category = ? and c.sub_category = ?)";
    private static final String UPDATE_QUERY = "update product set name = ? , category_id = ? , stock = ? , price = ? where id = ?";
    private static final String DELETE_QUERY = "delete from users where id = ?";
    private static final String UPDATE_STOCK_QUERY = "update product set stock = stock - ? where id = ?";

    private static final String FIND_PRICE_QUERY = "select price from product where id = ?";


    public static void createProduct(Product product) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCategoryId().getId());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getPrice());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static List<Product> findAll() {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product newProduct = new Product();
                newProduct.setId(resultSet.getInt(1));
                newProduct.setName(resultSet.getString(2));
                newProduct.setCategoryId(new Categories(
                        resultSet.getString(7),
                        resultSet.getString(8)));
                newProduct.setStock(resultSet.getInt(4));
                newProduct.setPrice(resultSet.getInt(5));
                productList.add(newProduct);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return productList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static void update(Product product) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCategoryId().getId());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setInt(5, product.getId());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static Product find(Product product) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, String.valueOf(product.getCategoryId().getCategory()));
            preparedStatement.setString(3, String.valueOf(product.getCategoryId().getSubCategory()));

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Product newProduct = new Product();
                newProduct.setId(resultSet.getInt(1));
                newProduct.setName(resultSet.getString(2));
                newProduct.setCategoryId(new Categories(
                        resultSet.getString(7),
                        resultSet.getString(8)));
                newProduct.setStock(resultSet.getInt(4));
                newProduct.setPrice(resultSet.getInt(5));
                return newProduct;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();


        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static Product findPrice(int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRICE_QUERY);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Product newProduct = new Product();
            while (resultSet.next()) {
                newProduct.setPrice(resultSet.getInt("price"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return newProduct;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static void updateStock(int stock, int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK_QUERY);
            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, id);


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

}
