package com.example.adventures;

import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Guide;
import com.example.adventures.model.LocationInfo;
import com.example.adventures.model.PeriodInfo;
import com.example.adventures.model.Trip;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class NewTripTest {

    @Test
    void checkNewTrip(){

        LocationInfo locationInfo = new LocationInfo("Rome", "Italy");
        PeriodInfo periodInfo = new PeriodInfo((Date.valueOf("2024-04-24")).toLocalDate(), (Date.valueOf("2024-04-26")).toLocalDate());
        Trip trip = new Trip("Viaggio test", locationInfo, "Relax",  periodInfo, "3000","Ben");
        Guide guide = new Guide(1,"Ben", "Ottaviani", "ben.ottaviani@gmail.com");
        try{

            TripDAO.addTrip(trip, guide);

        }catch (Exception e){
            fail();
        }
    }


}
