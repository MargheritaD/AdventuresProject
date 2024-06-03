package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Guide;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideDAO {

    private static final String ID = "idGuide";
    private static final String NAME = "guideName";
    private static final String SURNAME = "guideSurname";
    private static final String EMAIL = "email";
    //private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    private GuideDAO() {}

    public static Guide retrieveGuideById(int idGuide) throws NotFoundException {
        Connection connection;
        Guide guide = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveGuideById(connection, idGuide);

            if (!resultSet.first()) {
                throw new NotFoundException("No guide found with id: " + idGuide);
            }

            resultSet.first();
            do {

                guide = setGuideData(resultSet);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return guide;
    }

    private static Guide setGuideData(ResultSet resultSet) throws NotFoundException, SQLException {
        int guideId = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        String email = resultSet.getString(EMAIL);

        Guide guide = new Guide(guideId, name, surname,email);

        return guide;
    }

    public static Guide retrieveGuideByUsername(String username) throws NotFoundException {
        Connection connection;
        Guide guide = null;
        ResultSet resultSet;

        try {
            connection = ConnectionDB.getConnection();

            resultSet = SimpleQueries.retrieveGuideByUsername(connection, username);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with username: " + username);
            }

            resultSet.first();
            do {
                guide = setGuideData(resultSet);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return guide;
    }
    //public static void addGuide(Guide guide){}
    // retrieve guide id

    public static Guide retrieveGuideID() {
        //rimetti il parametro String email
        Connection connection;
        Guide guide = null;

        String mail = guide.getEmail();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveGuideID(connection, mail);

            if (!resultSet.first()) {
                throw new NotFoundException("No guide found with email " + mail);
            }

            resultSet.first();

            int id = resultSet.getInt(ID);
            guide = new Guide();
            guide.setId(id);

            resultSet.close();


        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return guide;
    }

    public static int retrieveJustGuideID(String email) throws NotFoundException {
        Connection connection;
        int guideId = -1; // Valore di default nel caso in cui non venga trovata nessuna famiglia

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveGuideID(connection, email);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with email " + email);
            }

            guideId = resultSet.getInt(ID);

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return guideId;
    }

}
