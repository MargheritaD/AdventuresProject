package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.model.UserProfile;
import com.example.adventures.dao.queries.SimpleQueries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOJDBC implements LoginDAO{

    @Override
    public UserProfile checkUser(String username, String password) throws UserNotFoundException{
        System.out.println("Pippo1");
        Connection connection;
        System.out.println("Pippo2");
        int role;
        UserProfile userProfile = null;
        try{
            System.out.println("Pippo3");
            connection = ConnectionDB.getConnection();
            System.out.println("Pippo4");
            ResultSet resultSet = SimpleQueries.checkUser(connection, username, password);
            System.out.println("Pippo5");
            if(!resultSet.isBeforeFirst()){
                throw new UserNotFoundException();
            }
            resultSet.first();
            String nameRole = resultSet.getString(1);
            switch (nameRole){
                case "guide" -> role = 1;
                case "traveler" -> role = 2;
                default -> throw new NotFoundException("No role found");
            }
            resultSet.close();
            userProfile = new UserProfile(role, username);
        }catch (NotFoundException | SQLException e){
            Printer.printError(e.getMessage());
        }
        return userProfile;
    }

    /*

    @Override
    public UserProfile checkUser(String username, String password) throws UserNotFoundException{
        Connection connection;
        int role;
        UserProfile userProfile = null;

        try{

            connection = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.checkUser(connection, username, password);

            if(!resultSet.isBeforeFirst()){
                throw new UserNotFoundException();
            }

            resultSet.first();

            String nameRole = resultSet.getString(1);
            switch (nameRole){
                case "guide" -> role = 1;
                case "traveler" -> role = 2;
                default -> throw new NotFoundException("No role found");
            }

            resultSet.close();
            userProfile = new UserProfile(role, username);

        }catch (NotFoundException | SQLException e){
            Printer.printError(e.getMessage());
        }

        return userProfile;

    }

     */

}
