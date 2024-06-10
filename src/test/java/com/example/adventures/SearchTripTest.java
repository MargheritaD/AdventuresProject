package com.example.adventures;

import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchTripTest {

    TripDAO tripDAO = new TripDAO();

    @Test
    void searchTrip(){


        String country = "Italy";
        String category = "Food tasting";

        try{

            List<Trip> trips = tripDAO.retrieveTripListByCategoryAndCountry(category, country);
            assertTrue(trips.size()>0);

        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }


}
