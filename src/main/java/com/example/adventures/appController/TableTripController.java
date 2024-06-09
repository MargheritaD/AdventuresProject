package com.example.adventures.appController;

import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Trip;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TableTripController {

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
