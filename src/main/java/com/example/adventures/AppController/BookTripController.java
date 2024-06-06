package com.example.adventures.AppController;

import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.QuoteBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.ItineraryStopDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.engineering.decoretor.Quote;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class BookTripController {

    public float calculateQuote(QuoteBean quoteBean) {
        float totalPrice = quoteBean.getTripPrice();

        for (Quote insurance : quoteBean.getSelectedInsurances()) {
            totalPrice += insurance.getPrice();
        }

        return totalPrice;
    }

    public int numberOfTrps(String country){

        int number = TripDAO.getNumberTripsByCountry(country);

        return number;
    }


    public List<TripBean> selectCountryAndCategory(String valore, String country){
        List<Trip> trips = TripDAO.retrieveTripListByCategoryAndCountry(valore, country);

        List<TripBean> tripBeans = new ArrayList<>();
        for (Trip trip : trips) {
            TripBean tripBean = new TripBean(trip.getIdTrip(), trip.getTripName(), trip.getOutboundDate(), trip.getReturnDate(), trip.getDepartureCity(), trip.getGuide());
            tripBeans.add(tripBean);
            System.out.println("\nPROVA STAMPA CITTA TRIP: "+ trip.getDepartureCity());
            System.out.println("PROVA STAMPA ID TRIP: "+ trip.getIdTrip());
            System.out.println("PROVA STAMPA NOME TRIP: "+ trip.getTripName());
            System.out.println("PROVA STAMPA NOME GUIDA TRIP: "+ trip.getGuide());
        }

        return tripBeans;
    }

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

        TripBean tripBean = new TripBean(trip.getTripName(), trip.getDepartureCity(), trip.getOutboundDate(), trip.getReturnDate(), trip.getPrice(), trip.getGuide());

        return tripBean;

    }


}
