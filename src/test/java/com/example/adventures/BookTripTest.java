package com.example.adventures;

import com.example.adventures.AppController.NewTripController;
import com.example.adventures.dao.TripDAO;
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


       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("23/04/2024");
        String date = "23/04/2024";

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("2024-04-25");
        formatter2 = formatter2.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateF = LocalDate.parse("2024-apr-25", formatter2);*/


        Trip trip = new Trip("Viaggio test", "Rome", "Relax",  (Date.valueOf("2024-04-24")).toLocalDate(), (Date.valueOf("2024-04-26")).toLocalDate(), "3000","Ben","Italy");

        try{

            TripDAO.addTrip(trip);

        }catch (Exception e){
            fail();
        }
    }
}
