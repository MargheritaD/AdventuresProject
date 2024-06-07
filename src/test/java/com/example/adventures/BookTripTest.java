package com.example.adventures;

import com.example.adventures.AppController.NewTripController;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Guide;
import com.example.adventures.model.Trip;
import javafx.util.converter.LocalDateStringConverter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class BookTripTest {


    //NewTripController newTripController = new NewTripController();

    @Test
    void checkNewTrip(){

        Trip trip = new Trip("Viaggio test", "Rome", "Relax",  (Date.valueOf("2024-04-24")).toLocalDate(), (Date.valueOf("2024-04-26")).toLocalDate(), "3000","Ben","Italy");
        new Guide(1,"Ben", "Ottaviani", "ben.ottaviani@gmail.com");
        try{

            TripDAO.addTrip(trip);

        }catch (Exception e){
            fail();
        }
    }


}
