package com.example.adventures.dao.queries;

import com.example.adventures.bean.RequestBean;
import javafx.scene.image.ImageView;

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

    public static ResultSet retrieveGuideByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM Guide WHERE email = ? ";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,username);
        return preparedStatement.executeQuery();
    }
    public static ResultSet retrieveTravelerByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM Viaggiatori WHERE Email = ? ";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,username);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveGuideById(Connection connection, int idGuide) throws SQLException {
        String sql = "SELECT * FROM Guide WHERE idGuide = ? ";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idGuide);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveGuideID(Connection connection, String email) throws SQLException {
        String sql = "SELECT idGuide FROM Guide WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,email);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveTripID(Connection connection, String name) throws SQLException {
        String sql = "SELECT idTrip FROM Trips WHERE tripName = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,name);
        return preparedStatement.executeQuery();
    }

   /* public static ResultSet retrieveTripListByCategory(Connection connection, String category) throws SQLException {
        String sql = "SELECT idTrip, tripName, outboundDate, returnDate, departureCity, guide FROM Trips WHERE category = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,category);
        return preparedStatement.executeQuery();
    }*/

    public static ResultSet retrieveTripListByCategoryAndCountry(Connection connection, String category, String country) throws SQLException {
        String sql = "SELECT idTrip, tripName, outboundDate, returnDate, departureCity, guide FROM Trips WHERE category = ? AND country = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,category);
        preparedStatement.setString(2,country);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveUpcomingTripList(Connection connection, Date currentDate, String user) throws SQLException{
        String sql = "SELECT idTrip, tripName, outboundDate, returnDate, departureCity, guide FROM Trips WHERE outboundDate > ? AND guide = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setDate(1,currentDate);
        preparedStatement.setString(2,user);
        return preparedStatement.executeQuery();
    }

    /*public static ResultSet retrieveFutureTripList(Connection connection) throws SQLException{
        String sql = "SELECT idTrip, tripName, outboundDate, returnDate, departureCity, guide FROM Trips WHERE outboundDate > CURDATE()";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }*/

    public static ResultSet retrieveTripListByTripId(Connection connection, int tripId) throws SQLException {
        String sql = "SELECT city, arrival, departure FROM Itinerari WHERE trip = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,tripId);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveTrip(Connection connection, int idTrip) throws SQLException{ // rimetti tripId
        String sql = "SELECT t.idTrip, t.tripName, t.departureCity, t.outboundDate, t.returnDate, t.price, t.guide\n" +
                "FROM Trips t\n" +
                "JOIN Itinerari i ON t.idTrip = i.trip\n" +
                "WHERE i.trip = ?";
        //String sql = "SELECT tripName, departureCity, outboundDate, returnDate, guide, price FROM Trips WHERE idTrip = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idTrip); //setInt tripId
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRequestsListGuide(Connection connection, int idGuide) throws SQLException{
        String sql = "SELECT t.tripName, v.Nome_viaggiatore, v.Cognome_viaggiatore, r.idRequest\n" +
                "From dbAdventures.GUIDE g Join dbAdventures.TRIPS t ON  g.guideName = t.guide \n" +
                "join dbAdventures.REQUEST r ON r.trip = t.idTrip\n" +
                "join dbAdventures.VIAGGIATORI v on v.Id_viaggiatore = r.traveler\n" +
                "WHERE g.idGuide = ? and status = 0\n";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idGuide);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveTripData(Connection connection, int tripId) throws SQLException {
        String sql = "SELECT * FROM Trips WHERE idTrip = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,tripId);
        return preparedStatement.executeQuery();
    }

    /*public static String retrieveTripCategory(Connection connection, int tripId) throws SQLException{
        String sql = "SELECT category FROM Trips WHERE idTrip = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,tripId);
        return preparedStatement.executeQuery();
    }*/

    public static ResultSet numberOfTrips(Connection connection, String country) throws SQLException{
        String sql = "SELECT COUNT(*) FROM Trips WHERE country = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,country);
        return preparedStatement.executeQuery();
    }

    /*public static boolean setBell(Connection connection, Integer idGuide) throws SQLException{

        String sql = "SELECT EXISTS(SELECT 1  " +
                    "FROM dbAdventures.Request r " +
                    "JOIN dbAdventures.Trips t ON r.trip = t.idTrip\n" +
                    "JOIN dbAdventures.Guide g ON t.guide = g.guideName\n" +
                    "WHERE g.idGuide = ?\n" +
                    "AND r.status = 0) AS result";
        System.out.println("RISULTATAO QUERY: " + sql);
        boolean bell = Boolean.valueOf(sql);
        return bell;
    }

     */

    public static ResultSet setBell(Connection connection, Integer idGuide) throws SQLException{
        String sql = "SELECT *" +
                "FROM dbAdventures.Request r " +
                "JOIN dbAdventures.Trips t ON r.trip = t.idTrip\n" +
                "JOIN dbAdventures.Guide g ON t.guide = g.guideName\n" +
                "WHERE g.idGuide = ?\n" +
                "AND r.status = 0";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idGuide);
        return preparedStatement.executeQuery();
    }




}
