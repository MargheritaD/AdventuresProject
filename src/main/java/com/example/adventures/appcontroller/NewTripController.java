package com.example.adventures.appcontroller;

import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Guide;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class NewTripController {

    public void createTrip(TripBean tripBean, GuideBean guideBean){

        Guide guide = new Guide(guideBean.getId(), guideBean.getName(), guideBean.getSurname(), guideBean.getEmail());
        List<ItineraryStop> itineraryStops = new ArrayList<>();

        Trip trip = new Trip(tripBean.getTripName(), tripBean.getDepartureCity(), tripBean.getCategory(), tripBean.getOutboundDate(), tripBean.getReturnDate(), tripBean.getPrice(), tripBean.getGuide(), tripBean.getCountry());

        for(ItineraryStopBean itineraryStopBean: tripBean.getStops()){
            ItineraryStop itineraryStop = new ItineraryStop(itineraryStopBean.getCity(), itineraryStopBean.getArrival(), itineraryStopBean.getDeparture());
            itineraryStops.add(itineraryStop);
        }

        trip.setStops(itineraryStops);
        TripDAO.addTrip(trip, guide);

    }
}
