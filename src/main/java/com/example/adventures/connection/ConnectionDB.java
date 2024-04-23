package com.example.adventures.connection;

// import

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
//COMMENTO SOTTO
                System.out.println("Connessione al database in corso...");

                Properties db = loadProperties();
                user = db.getProperty("USER");
                url = db.getProperty("CONNECTION_URL");
                password = db.getProperty("PASS");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");

                System.out.println("User: " + user);
                System.out.println("URL: " + url);
                System.out.println("Password: " + password);
                System.out.println("Driver Class Name: " + driverClassName);


                Class.forName(driverClassName);

                //
                System.out.println("class for Nameeee");

                connection = DriverManager.getConnection(url, user, password);

                //COMMENTO SOTTO
                System.out.println("Connessione top");

            }catch (SQLException | ClassNotFoundException e){ // metti anche IOException
                //Printer.printError(e.getMessage());
                System.err.println("Errore durante la connessione al database: ");
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
            System.out.println(" DAJE!!!! Properties loaded successfully");
        }catch (IOException e){
            System.out.println(" MANNAGGIA ");
            //Printer.printError(e.getMessage());
        }
        return properties;
    }
}
