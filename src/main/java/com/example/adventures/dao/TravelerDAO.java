package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Traveler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelerDAO {

    private static final String ID = "Id_viaggiatore";
    private static final String NAME = "Nome_viaggiatore";
    private static final String SURNAME = "Cognome_viaggiatore";
    private static final String EMAIL = "email";

    private TravelerDAO() {}

    private static Traveler setTravelerData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        String email = resultSet.getString(EMAIL);

        return (new Traveler(id, name, surname, email));
    }

    public static Traveler retrieveTravelerByUsername(String username) throws NotFoundException {
        Connection connection;
        Traveler traveler = null;
        ResultSet resultSet;

        try {
            connection = ConnectionDB.getConnection();

            resultSet = SimpleQueries.retrieveTravelerByUsername(connection, username);

            if (!resultSet.first()) {
                throw new NotFoundException("No traveler found with username: " + username);
            }

            resultSet.first();
            do {
                traveler = setTravelerData(resultSet);

                // Aggiungi un messaggio di debug per stampare i dati del viaggiatore recuperati
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return traveler;
    }
}
