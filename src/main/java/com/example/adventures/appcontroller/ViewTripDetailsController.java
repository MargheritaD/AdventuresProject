package com.example.adventures.appcontroller;

import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.ItineraryStopDAO;
import com.example.adventures.dao.RequestDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class ViewTripDetailsController {

    public int numberOfTrps(String country){

        return TripDAO.getNumberTripsByCountry(country);
    }

    public boolean bell(GuideBean guideBean){

        return RequestDAO.setBell(guideBean);
    }

    public List<TripBean> selectCountryAndCategory(CountryCategoryBean countryCategoryBean){

        List<Trip> trips = TripDAO.retrieveTripListByCategoryAndCountry(countryCategoryBean.getCategory(), countryCategoryBean.getCountry());

        List<TripBean> tripBeans = new ArrayList<>();
        for (Trip trip : trips) {
            TripBean tripBean = new TripBean(trip.getIdTrip(), trip.getTripName(), trip.getOutboundDate(), trip.getReturnDate(), trip.getDepartureCity(), trip.getGuide());
            tripBeans.add(tripBean);
        }

        return tripBeans;
    }

    public List<ItineraryStopBean> tableItinerary(TripBean tripBean){

        List<ItineraryStop> stops = ItineraryStopDAO.retrieveStopList(tripBean.getIdTrip());

        List<ItineraryStopBean> itineraryStopBeans = new ArrayList<>();
        for (ItineraryStop itineraryStop : stops) {
            ItineraryStopBean itineraryStopBean = new ItineraryStopBean(itineraryStop.getCity(), itineraryStop.getArrival(), itineraryStop.getDeparture());
            itineraryStopBeans.add(itineraryStopBean);
        }

        return itineraryStopBeans;
    }

    public TripBean getTripDetails(TripBean partialTripBean) throws NotFoundException {
        int tripId = partialTripBean.getIdTrip();

        // Recupera i dettagli completi del viaggio dal DAO
        Trip trip = TripDAO.retrieveTripById(tripId);
        if (trip == null) {
            throw new NotFoundException("Trip not found for ID: " + tripId);
        }

        return new TripBean(partialTripBean.getIdTrip(), trip.getTripName(), trip.getDepartureCity(), trip.getCategory(), trip.getOutboundDate(), trip.getReturnDate(), trip.getPrice(), trip.getGuide(), trip.getCountry());
    }

}
