package com.example.adventures.appcontroller;

import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.*;

import java.util.ArrayList;
import java.util.List;

public class NewTripController {

    public void createTrip(TripBean tripBean, GuideBean guideBean){

        Guide guide = new Guide(guideBean.getId(), guideBean.getName(), guideBean.getSurname(), guideBean.getEmail());
        List<ItineraryStop> itineraryStops = new ArrayList<>();
        // Creazione dell'oggetto LocationInfo
        LocationInfo locationInfo = new LocationInfo(tripBean.getDepartureCity(), tripBean.getCountry());
        PeriodInfo periodInfo = new PeriodInfo(tripBean.getOutboundDate(), tripBean.getReturnDate());


        Trip trip = new Trip(tripBean.getTripName(), locationInfo, tripBean.getCategory(), periodInfo, tripBean.getPrice(), tripBean.getGuide());

        for(ItineraryStopBean itineraryStopBean: tripBean.getStops()){
            ItineraryStop itineraryStop = new ItineraryStop(itineraryStopBean.getCity(), itineraryStopBean.getArrival(), itineraryStopBean.getDeparture());
            itineraryStops.add(itineraryStop);
        }

        trip.setStops(itineraryStops);
        TripDAO.addTrip(trip, guide);

    }
}
