package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Guide;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    private static final String ID = "idTrip";
    private static final String NAME = "tripName";
    private static final String DEPARTURE_CITY = "departureCity";
    private static final String CATEGORY = "category";
    private static final String OUTBOUND_DATE = "outboundDate";
    private static final String RETURN_DATE = "returnDate";
    private static final String PRICE = "price";
    private static final String GUIDE = "guide";
    private static final String COUNTRY = "country";

    public TripDAO(){
        // costruttore
    }

    public static void addTrip(Trip trip, Guide guide) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            Date outboundDate = Date.valueOf(trip.getOutboundDate());
            Date returnDate = Date.valueOf(trip.getReturnDate());
            String guideName = guide.getName();

            CRUDQueries.insertTrip(connection, trip.getTripName(), trip.getDepartureCity(), trip.getCategory(), outboundDate, returnDate, trip.getPrice(), guideName, trip.getCountry());

            ResultSet resultSet = SimpleQueries.retrieveTripID(connection, trip.getTripName());

            if(!resultSet.first()){
                // NO TRIP FOUND
            }
            resultSet.first();

            int id = resultSet.getInt(ID);

           ItineraryStopDAOCSV itineraryStopDAOCSV = new ItineraryStopDAOCSV();

           for(ItineraryStop itineraryStop : trip.getStops()){
                ItineraryStopDAO.addStop(itineraryStop, id);
                itineraryStopDAOCSV.addStop(itineraryStop, id);
            }


        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }catch (IOException e) {
            Printer.printError(e.getMessage());
        }
    }

    private static Trip setTripInformationForRelaxTable(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        LocalDate outboundDate = resultSet.getDate(OUTBOUND_DATE).toLocalDate();
        LocalDate returnDate = resultSet.getDate(RETURN_DATE).toLocalDate();
        String guide = resultSet.getString(GUIDE);

        return (new Trip(id, name, outboundDate, returnDate, guide));
    }

    private static Trip setTripInformationForTripTable(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        String departureCity = resultSet.getString(DEPARTURE_CITY);
        String category = resultSet.getString(CATEGORY);
        LocalDate outboundDate = resultSet.getDate(OUTBOUND_DATE).toLocalDate();
        LocalDate returnDate = resultSet.getDate(RETURN_DATE).toLocalDate();
        String guide = resultSet.getString(GUIDE); // POSSO LEVARLA
        String price = resultSet.getString(PRICE);
        String country = resultSet.getString(COUNTRY);

        return (new Trip(name, departureCity, category, outboundDate, returnDate, price, guide, country));
    }

    public static List<Trip> retrieveTripListByCategoryAndCountry(String category, String country){
        Connection connection;
        Trip trip;
        List<Trip> tripList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveTripListByCategoryAndCountry(connection, category, country);

            if (!resultSet.first()) {
                throw new NotFoundException("No trip found for this category: " + category);
            }

            resultSet.first();

            do {

                trip = setTripInformationForRelaxTable(resultSet);
                tripList.add(trip);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return tripList;
    }

    public static List<Trip> retrieveUpcomingTrip (Date currentDate, String user){
        Connection connection;
        Trip trip;
        List<Trip> tripList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveUpcomingTripList(connection, currentDate, user);

            if (!resultSet.first()) {
                throw new NotFoundException("No trip found for this period: " + currentDate);
            }

            resultSet.first();

            do {

                trip = setTripInformationForRelaxTable(resultSet);
                tripList.add(trip);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return tripList;
    }

    public static Trip retrieveTripById(int tripId) throws NotFoundException{

        Connection connection;
        Trip trip = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveTripData(connection, tripId);

            if (!resultSet.first()) {
                throw new NotFoundException("No trip found with id: " + tripId);
            }

            trip = setTripInformationForTripTable(resultSet);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());// Gestisci l'eccezione in modo appropriato
        }

        return trip;
    }

    public static List<Trip> retrieveTrip(int tripId) throws NotFoundException { // rimetti come par int id

        Connection connection;
        Trip trip = null;
        List<Trip> tripList = new ArrayList<>();


        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveTrip(connection, tripId); // id

            if(!resultSet.first()) {
                throw new NotFoundException("Ma con scarsi risultati. No trip found with id: " + tripId); // id
            }

            resultSet.first();
            do{

                trip = setTripInformationForTripTable(resultSet);
                tripList.add(trip);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return tripList;
    }

    public static void cancelTrip(int idTrip) {

        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.deleteItinerary(connection, idTrip);
            CRUDQueries.deleteTrip(connection, idTrip);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }


    public static int getNumberTripsByCountry(String country){

        int number = 0;
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet =SimpleQueries.numberOfTrips(connection, country);

            if (!resultSet.next()) {
                throw new NotFoundException("No trip found for country: " + country);
            }

            number = resultSet.getInt(1);

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        } catch (NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return number;
    }

}
