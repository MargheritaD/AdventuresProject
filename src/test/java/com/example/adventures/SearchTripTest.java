package com.example.adventures;

import com.example.adventures.appcontroller.NewTripController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.LocationInfoBean;
import com.example.adventures.bean.PeriodInfoBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.MessageException;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTripTest {

    /*
       Il seguente test verifica che venga sollevata un'eccezione quando
       viene creato un viaggio con una data di arrivo precedente alla data corrente */

    @Test
    void testSendRequest(){

        int validDate = 0;
        GuideBean guide = new GuideBean(1, "Ben", "Ottaviani", "ben.ttaviani@gmail.com");

        LocationInfoBean locationInfo = new LocationInfoBean("Rome", "Australia");
        PeriodInfoBean periodInfo = new PeriodInfoBean((Date.valueOf("2023-05-26")).toLocalDate(), (Date.valueOf("2023-06-26")).toLocalDate());
        TripBean trip = new TripBean("Viaggio test date", locationInfo, periodInfo, "Fun", "300");

        NewTripController newTripController = new NewTripController();
        try {
            newTripController.createTrip(trip, guide);
            validDate = 1;
        } catch (MessageException e){
            validDate = 2;
        } catch (Exception ignored){
        }

        assertEquals(2,validDate);

        //il test ha successo perchè la data di arrivo è precedente al giorno corrente e viene sollevata l'eccezione
    }
}
