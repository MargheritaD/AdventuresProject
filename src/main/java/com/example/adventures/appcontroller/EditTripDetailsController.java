package com.example.adventures.appcontroller;

import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.ItineraryStopDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class EditTripDetailsController {
    public List<ItineraryStopBean> tableItinerary(int tripId){

        List<ItineraryStop> stops = ItineraryStopDAO.retrieveStopList(tripId);

        List<ItineraryStopBean> itineraryStopBeans = new ArrayList<>();
        for (ItineraryStop itineraryStop : stops) {
            ItineraryStopBean itineraryStopBean = new ItineraryStopBean(itineraryStop.getCity(), itineraryStop.getArrival(), itineraryStop.getDeparture());
            itineraryStopBeans.add(itineraryStopBean);
        }

        return itineraryStopBeans;
    }

    public TripBean tableTrip(int tripId) throws NotFoundException {

        Trip trip = TripDAO.retrieveTripById(tripId);

        TripBean tripBean = new TripBean(trip.getTripName(), trip.getDepartureCity(), trip.getOutboundDate(), trip.getReturnDate(), trip.getPrice(), trip.getCategory());

        return tripBean;

    }

    public void cancelTrip(int idTrip) {

        TripDAO.cancelTrip(idTrip);
    }


}
