package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.engineering.Printer;


import java.sql.Connection;
import java.sql.SQLException;

public class ParticipantDAO {

    private static final String TRIP = "idTrip";
    private static final String PARTICIPANT = "idParticipant";

    private ParticipantDAO() {}
    public static  void insertParticipant(int trip, int participant) {


        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            CRUDQueries.insertParticipant(connection, trip, participant);

        } catch(SQLException e) {
            Printer.printError(e.getMessage());
        }
    }
}
