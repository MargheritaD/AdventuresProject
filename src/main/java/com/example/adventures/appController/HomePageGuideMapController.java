package com.example.adventures.appController;

import com.example.adventures.dao.TripDAO;

public class HomePageGuideMapController {

    public int numberOfTrps(String country){

        int number = TripDAO.getNumberTripsByCountry(country);

        return number;
    }
}
