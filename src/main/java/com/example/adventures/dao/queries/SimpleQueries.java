package com.example.adventures.dao.queries;

import java.sql.*;

public class SimpleQueries {

    private static PreparedStatement preparedStatement = null;
    private SimpleQueries(){}

    public static ResultSet checkUser(Connection connection, String username, String password) throws SQLException{
        String sql = "SELECT role FROM Users WHERE username = ? AND password = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }
}
