package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItineraryStopDAO {
    private static final String CITY = "city";
    private static final String ARRIVAL = "arrival";
    private static final String DEPARTURE = "departure";

    private ItineraryStopDAO(){}

    public static void addStop(ItineraryStop itineraryStop, int id) {

        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            //int idTrip = Trip.getIdTrip();
            Date arrival = Date.valueOf(itineraryStop.getArrival());
            Date departure = Date.valueOf(itineraryStop.getDeparture());

            CRUDQueries.insertStop(connection, id, itineraryStop.getCity(), arrival, departure);

        } catch(SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    private static ItineraryStop setTripInformation(ResultSet resultSet) throws NotFoundException, SQLException {
        String city = resultSet.getString(CITY);
        LocalDate arrival = resultSet.getDate(ARRIVAL).toLocalDate();
        LocalDate departure = resultSet.getDate(DEPARTURE).toLocalDate();

        //ItineraryStop itineraryStop = new ItineraryStop(city, arrival, departure);
        // prima era return itineraryStop

        return (new ItineraryStop(city, arrival, departure));
    }

    public static List<ItineraryStop> retrieveStopList(int idTrip) {
        Connection connection;
        ItineraryStop itineraryStop;
        List<ItineraryStop> stopList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveTripListByTripId(connection, idTrip);

            if (!resultSet.first()) {
                throw new NotFoundException("No itinerary found for this trip: " + idTrip);
            }

            resultSet.first();

            do {

                itineraryStop = setTripInformation(resultSet);
                stopList.add(itineraryStop);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return stopList;
    }
}
