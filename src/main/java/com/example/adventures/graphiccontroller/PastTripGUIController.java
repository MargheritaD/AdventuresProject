package com.example.adventures.graphiccontroller;

import com.example.adventures.model.Trip;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class PastTripGUIController {
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private List<Trip> trips = new ArrayList<>();

    private List<Trip> getData(){
        List<Trip> trips = new ArrayList<>();
        Trip trip;

        for(int i = 0; i < 10; i++){
            trip = new Trip();
            trips.add(trip);
        }

        return trips;
    }
}
