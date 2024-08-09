package com.example.adventures.appcontroller;

import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.exception.MessageException;
import com.example.adventures.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewTripController {

    public void createTrip(TripBean tripBean, GuideBean guideBean) throws MessageException {

        Guide guide = new Guide(guideBean.getId(), guideBean.getName(), guideBean.getSurname(), guideBean.getEmail());
        List<ItineraryStop> itineraryStops = new ArrayList<>();
        // Controllo sulle date
        checkRequestDate(tripBean.getOutboundDate(), tripBean.getReturnDate());
        // Creazione dell'oggetto LocationInfo
        LocationInfo locationInfo = new LocationInfo(tripBean.getDepartureCity(), tripBean.getCountry());
        // Creazione dell'oggetto PeriodInfo
        PeriodInfo periodInfo = new PeriodInfo(tripBean.getOutboundDate(), tripBean.getReturnDate());


        Trip trip = new Trip(tripBean.getTripName(), locationInfo, tripBean.getCategory(), periodInfo, tripBean.getPrice(), tripBean.getGuide());

        for(ItineraryStopBean itineraryStopBean: tripBean.getStops()){
            ItineraryStop itineraryStop = new ItineraryStop(itineraryStopBean.getCity(), itineraryStopBean.getArrival(), itineraryStopBean.getDeparture());
            itineraryStops.add(itineraryStop);
        }

        trip.setStops(itineraryStops);
        TripDAO.addTrip(trip, guide);

    }

    private void checkRequestDate(LocalDate arrival, LocalDate departure) throws MessageException {

        LocalDate currentDate = LocalDate.now();

        if(arrival.isBefore(currentDate.plusDays(3))){
            throw new MessageException("Arrival must be at least \n 3 days from today!");
        }
        if(departure.isBefore(arrival.plusDays(2))){
            throw new MessageException("Departure must be at least \n 2 days from arrival!");
        }
    }
}
