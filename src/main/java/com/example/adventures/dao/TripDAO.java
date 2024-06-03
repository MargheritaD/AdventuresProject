package com.example.adventures.dao;

import com.example.adventures.bean.TripBean;
import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Guide;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
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
    //private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    private TripDAO() {}

    public static void addTrip(Trip trip) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            Date outboundDate = Date.valueOf(trip.getOutboundDate());
            Date returnDate = Date.valueOf(trip.getReturnDate());
            String guide = Guide.getName();

            CRUDQueries.insertTrip(connection, trip.getTripName(), trip.getDepartureCity(), trip.getCategory(), outboundDate, returnDate, trip.getPrice(), guide, trip.getCountry());

            ResultSet resultSet = SimpleQueries.retrieveTripID(connection, trip.getTripName());

            if(!resultSet.first()){
                System.out.println("No trip found");
                //throw new NotFoundException("No trip found");
            }
            resultSet.first();

            int id = resultSet.getInt(ID);
            System.out.println("Id del nuovo viaggio: " + id);

            for(ItineraryStop itineraryStop : trip.getStops()){
                ItineraryStopDAO.addStop(itineraryStop, id);
            }
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    private static Trip setTripInformationForRelaxTable(ResultSet resultSet) throws NotFoundException, SQLException {
        int id = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        LocalDate outboundDate = resultSet.getDate(OUTBOUND_DATE).toLocalDate();
        LocalDate returnDate = resultSet.getDate(RETURN_DATE).toLocalDate();
        String guide = resultSet.getString(GUIDE);

//CANCELLA I PRINT
        System.out.println("Id viaggio " + id);
        System.out.println("Nome viaggio: "+name);
        System.out.println(returnDate);
        System.out.println(outboundDate);

        Trip trip = new Trip(id, name, outboundDate, returnDate, guide);

        return trip;
    }

    private static Trip setTripInformationForTripTable(ResultSet resultSet) throws NotFoundException, SQLException {
        //int id = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        String departureCity = resultSet.getString(DEPARTURE_CITY);
        String category = resultSet.getString(CATEGORY);
        LocalDate outboundDate = resultSet.getDate(OUTBOUND_DATE).toLocalDate();
        LocalDate returnDate = resultSet.getDate(RETURN_DATE).toLocalDate();
        String guide = resultSet.getString(GUIDE); // POSSO LEVARLA
        String price = resultSet.getString(PRICE);
        String country = resultSet.getString(COUNTRY);

        Trip trip = new Trip(name, departureCity, category, outboundDate, returnDate, price, guide, country);

        return trip;
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
                System.out.println("ID del viaggio recuperato: " + trip.getIdTrip());
                System.out.println("Citta del viaggio recuperato: " + trip.getDepartureCity());
                System.out.println("Guida del viaggio recuperato: " + trip.getGuide());
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


    /*public static List<Trip> retrieveTripListByCategory(String category) {
        Connection connection;
        Trip trip;
        List<Trip> tripList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveTripListByCategory(connection, category);

            if (!resultSet.first()) {
                throw new NotFoundException("No trip found for this category: " + category);
            }

            resultSet.first();

            do {

                trip = setTripInformationForRelaxTable(resultSet);
                System.out.println("ID del viaggio recuperato: " + trip.getIdTrip());
                System.out.println("Citta del viaggio recuperato: " + trip.getDepartureCity());
                System.out.println("Guida del viaggio recuperato: " + trip.getGuide());
                tripList.add(trip);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return tripList;
    }
*/
    public static Trip retrieveTripById(int tripId) throws NotFoundException{

        Connection connection;
        Trip trip = null;

        try {
            connection = ConnectionDB.getConnection();

            //ResultSet resultSet = SimpleQueries.retrieveTrip(connection, tripId);
            ResultSet resultSet = SimpleQueries.retrieveTripData(connection, tripId);

            // Stampa i valori recuperati dal ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + resultSet.getObject(i) + "\t");
                }
                System.out.println(); // Vai a capo dopo aver stampato i valori di una riga
            }

            if (!resultSet.first()) {
                throw new NotFoundException("No trip found with id: " + tripId);
            }

            trip = setTripInformationForTripTable(resultSet);

        } catch (SQLException e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo appropriato
        }

        return trip;
    }

    public static List<Trip> retrieveTrip(int tripId) throws NotFoundException { // rimetti come par int id

        Connection connection;
        Trip trip = null;
        List<Trip> tripList = new ArrayList<>();

        System.out.println("\nId trip in TRIPDAO: " + tripId);

        try{
            connection = ConnectionDB.getConnection();

            System.out.println("\n\nSto provando ad eseguire la query\n");
            ResultSet resultSet = SimpleQueries.retrieveTrip(connection, tripId); // id
            System.out.println("\n\nHo eseguito la query\n");

            if(!resultSet.first()) {
                throw new NotFoundException("Ma con scarsi risultati. No trip found with id: " + tripId); // id
            }

            resultSet.first();
            do{

                trip = setTripInformationForTripTable(resultSet);
                //System.out.println("ID del viaggio recuperato: " + trip.getIdTrip());
                tripList.add(trip);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return tripList;
    }

    public static void cancelTrip(int idTrip) throws NotFoundException{

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
            System.out.println("Numero viaggi in " + country + ": " + number);

            resultSet.close();

            /*if(!resultSet.first()) {
                throw new NotFoundException("No trip found for country: " + country); // id
            }

            resultSet.first();
            do{
                number = resultSet.getInt(1);
                System.out.println("NUmero viaggi australia: "+ number);

            } while(resultSet.next());

            resultSet.close();*/

           //number = resultSet.getInt(1);
            //System.out.println("NUmero viaggi australia: "+ number);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        return number;
    }

    /*public static String retrieveCategory(int idTrip){

        String category;
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            category = SimpleQueries.retrieveTripCategory(connection, idTrip);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
        return category;
    }*/

   /*public static Trip retrieveTripById(int id) throws NotFoundException {
        Connection connection;
        //TripBean tripBean;
        Trip trip = null;
        ResultSet resultSet;

        try {
            connection = ConnectionDB.getConnection();

            resultSet = SimpleQueries.retrieveTripById(connection, id);

            if (!resultSet.first()) {
                throw new NotFoundException("No trip found with id: " + id);
            }

            resultSet.first();
            do {
                trip = setTripInformation(resultSet);
               // tripBean = setTripInformation(resultSet);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return trip;
       //return tripBean;
    }*/
}
