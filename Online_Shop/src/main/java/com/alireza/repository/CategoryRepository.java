package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Categories;
import com.alireza.model.enumeration.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static final String CREATE_QUERY = "insert into categories (category, sub_category) VALUES (?,?)";
    private static final String FIND_QUERY = "select * from categories where category = ?";
    private static final String UPDATE_QUERY = "update categories set category = ? , sub_category = ? where id = ?";
    private static final String FIND_ALL_QUERY = "select * from categories";


    public static void createCategory(Categories category) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, String.valueOf(category.getCategory()));
            preparedStatement.setString(2, String.valueOf(category.getSubCategory()));

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static Categories findCategory(String category) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setString(1, category.toUpperCase());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Categories newCategory = new Categories();
                newCategory.setId(resultSet.getInt("id"));
                newCategory.setCategory(Category.valueOf(resultSet.getString("category")));
                newCategory.setSubCategory(Category.valueOf(resultSet.getString("sub_category")));
                return newCategory;
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

    public static void updateCategory(Categories category) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, String.valueOf(category.getCategory()));
            preparedStatement.setString(2, String.valueOf(category.getSubCategory()));
            preparedStatement.setInt(3, category.getId());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
    public static List<Categories> findAllCategory() {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Categories> categoriesList = new ArrayList<>();
            while (resultSet.next()) {
                Categories newCategory = new Categories();
                newCategory.setId(resultSet.getInt("id"));
                newCategory.setCategory(Category.valueOf(resultSet.getString("category")));
                newCategory.setSubCategory(Category.valueOf(resultSet.getString("sub_category")));
                categoriesList.add(newCategory);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return categoriesList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
}
