package com.example.adventures.dao.queries;

import java.sql.*;

public class CRUDQueries {

    private static PreparedStatement preparedStatement = null;
    private CRUDQueries() {}

    // rimetti il parametro per la guida e idTRip
    public static int insertTrip(Connection connection, String name, String city, String category, Date outboundDate, Date returnDate, String price, String guide, String country) throws SQLException {
        String insertStatement = "INSERT INTO Trips (tripName, departureCity, category, outboundDate, returnDate, price, guide, country) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,city);
        preparedStatement.setString(3,category);
        preparedStatement.setDate(4,outboundDate);
        preparedStatement.setDate(5,returnDate);
        preparedStatement.setString(6,price);
        preparedStatement.setString(7,guide);
        preparedStatement.setString(8,country);
        return preparedStatement.executeUpdate();
    }

    public static int insertStop(Connection connection, int trip, String city, Date arrival, Date departure) throws SQLException {
        String insertStatement = "INSERT INTO Itinerari (trip, city, arrival, departure) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,trip);
        preparedStatement.setString(2,city);
        preparedStatement.setDate(3,arrival);
        preparedStatement.setDate(4,departure);
        return preparedStatement.executeUpdate();
    }

    public static int deleteTrip(Connection connection, int idTrip) throws SQLException{
        String insertStatement = "DELETE FROM Trips WHERE idTrip = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idTrip);
        return preparedStatement.executeUpdate();
    }

    public static int deleteItinerary(Connection connection, int trip) throws SQLException{
        String insertStatement = "DELETE FROM Itinerari WHERE trip = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,trip);
        return preparedStatement.executeUpdate();
    }

   /* public static int insertItinenray(){
        String insertStatement = "";
        preparedStatement= connection.p
        return
    }*/

   public static int insertParticipant(Connection connection, int trip, int person) throws SQLException{
        String insertStatement = "INSERT INTO Partecipanti (trip, traveler) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,trip);
        preparedStatement.setInt(2,person);
        return preparedStatement.executeUpdate();
    }

    public static int sendRequest(Connection connection, int person, int trip) throws SQLException{
        String insertStatement = "INSERT INTO Request (traveler, trip, status) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,person);
        preparedStatement.setInt(2,trip);
        preparedStatement.setInt(3,0);
        return preparedStatement.executeUpdate();
    }

    public static void acceptRequest(Connection connection, int idRequest) throws SQLException{
        String insertStatement = "UPDATE Request SET status = 1 WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idRequest);
        preparedStatement.executeUpdate();
    }

    public static void declineRequest(Connection connection, int idRequest) throws SQLException{
        String insertStatement = "UPDATE Request SET status = 2 WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idRequest);
        preparedStatement.executeUpdate();
    }



}
