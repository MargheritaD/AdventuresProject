package com.example.adventures.connection;

// import

import com.example.adventures.engineering.Printer;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.util.Properties;

public class ConnectionDB {

    private ConnectionDB() {}

    private static Connection connection;

    public static Connection getConnection(){
        String user;
        String password;
        String url;
        String driverClassName;

        if(connection == null){
            try{

                Properties db = loadProperties();
                user = db.getProperty("USER");
                url = db.getProperty("CONNECTION_URL");
                password = db.getProperty("PASS");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");

                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url, user, password);

            }catch (SQLException | ClassNotFoundException e){ // metti anche IOException
                Printer.printError(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        connection.close();
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();

        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/com/example/adventures/connection/db.properties.properties")){
            properties.load(fileInputStream);
        }catch (IOException e){
            Printer.printError(e.getMessage());
        }
        return properties;
    }
}
