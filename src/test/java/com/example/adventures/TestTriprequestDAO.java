package com.example.adventures;

import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Guide;
import com.example.adventures.model.LocationInfo;
import com.example.adventures.model.PeriodInfo;
import com.example.adventures.model.Trip;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTriprequestDAO {

     /*
         Il seguente test verifica che dopo l'aggiunta di una nuovo viaggio, chiamando un
         metodo del DAO che restituisce il numero di viaggi per lo specifico paese, questo venga incrementato di 1
     */

    @Test
    void testNewRequest(){
        Connection connection;
        ResultSet resultSet;
        int count = 0;
        int newCount = 0;

        try {
            connection = ConnectionDB.getConnection();
            resultSet = SimpleQueries.numberOfTrips(connection, "Italy");
            if (!resultSet.first()) {
                throw new NotFoundException("No trip found");
            }
            resultSet.first();

            ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
            count = viewTripDetailsController.numberOfTrps("Italy");
            System.out.println("Count " + count);

            LocationInfo locationInfo = new LocationInfo("Rome", "Italy");
            PeriodInfo periodInfo = new PeriodInfo((Date.valueOf("2025-04-24")).toLocalDate(), (Date.valueOf("2025-04-26")).toLocalDate());
            Trip trip = new Trip("Viaggio test aggiuntao", locationInfo, "Relax",  periodInfo, "3000","Ben");
            Guide guide = new Guide(1,"Ben", "Ottaviani", "ben.ottaviani@gmail.com");
            TripDAO.addTrip(trip, guide);


            resultSet = SimpleQueries.numberOfTrips(connection, "Italy");
            if (!resultSet.first()) {
                throw new NotFoundException("No trip found");
            }
            resultSet.first();
            newCount = viewTripDetailsController.numberOfTrps("Italy");
        } catch (Exception ignore) {
        }

        assertEquals(count+1, newCount); //il test ha successo in quanto il viaggio viene aggiunta correttamente
    }

}
