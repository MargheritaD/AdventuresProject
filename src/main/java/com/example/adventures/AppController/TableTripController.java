package com.example.adventures.AppController;

import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Trip;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TableTripController {

    /*
    public List<TripBean> tableTripCountry(String valore, String country){
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
*/

    public List<TripBean> upcomingTableTrip(Date currentDate, String user){
        List<Trip> trips = TripDAO.retrieveUpcomingTrip(currentDate, user);

        List<TripBean> tripBeans = new ArrayList<>();
        for (Trip trip : trips) {
            TripBean tripBean = new TripBean(trip.getIdTrip(), trip.getTripName(), trip.getOutboundDate(), trip.getReturnDate(), trip.getDepartureCity(), trip.getGuide());
            tripBeans.add(tripBean);
        }

        return tripBeans;

    }
}
