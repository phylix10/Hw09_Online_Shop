package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.User;
import com.alireza.model.enumeration.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private static final String CREATE_QUERY = "insert into users (username,password,national_code,role) VALUES (?,?,?,'USER') returning id";
    private static final String FIND_QUERY = "select * from users where username = ? and national_code = ?";


    public static Integer create(User user) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNationalCode());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");

            preparedStatement.close();
            connection.close();

            return id;

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static User find(String username, String nationalCode) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, nationalCode);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                User newUser = new User();
                newUser.setId(resultSet.getInt("id"));
                newUser.setUsername(resultSet.getString("username"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setNationalCode(resultSet.getString("national_code"));
                newUser.setRole(Role.valueOf(resultSet.getString("role")));
                return newUser;
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
}
