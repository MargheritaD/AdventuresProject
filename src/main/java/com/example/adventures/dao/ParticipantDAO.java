package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.model.Guide;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAO {

    private static final String TRIP = "idTrip";
    private static final String PARTICIPANT = "idParticipant";

    private ParticipantDAO() {}
    public static  void insertParticipant(int trip, int participant) {

        System.out.println("Sono entrata nel DAO partecipanti");

        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            //Date arrival = Date.valueOf(itineraryStop.getArrival());
            //Date departure = Date.valueOf(itineraryStop.getDeparture());

            CRUDQueries.insertParticipant(connection, trip, participant);

        } catch(SQLException e) {
            Printer.printError(e.getMessage());
        }
    }
}
