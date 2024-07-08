package com.example.adventures.cli;

import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class CLISelectedTrip {

    // vale sia per guide sia per viaggiatori?????

    /*
        controllo per vedere se il viaggio è il suo o no
        suo -> modifica
        non suo -> :
            guida -> preventivo / cambia profilo
            viaggiatore -> preventivo / manda richiesta

        Quindi mantieni la sessione per l'utente loggato
     */

    int role = 0;
    public void setRole(int role){
        this.role = role;
        System.out.println("CHI SONO dentro selected trip: " + role);
    }


    public void start(TripBean tripBean, List<ItineraryStopBean> itinerary) {
        printTripDetails(tripBean);
        printItinerary(itinerary);

        if(role == 0){
            menuForTraveler();
        }else{
           menuForGuideAsTraveler();
        }
    }

    private void printTripDetails(TripBean tripBean) {
        CLIPrinter.printMessage("\n--------Trip details:-----------\n");
        CLIPrinter.printMessage("| Trip name: " + tripBean.getTripName() + "\n");
        CLIPrinter.printMessage("| Guide: " + tripBean.getGuide() + "\n");
        CLIPrinter.printMessage("| Departure city: " + tripBean.getDepartureCity() + "\n");
        CLIPrinter.printMessage("| Outbound date: " + tripBean.getOutboundDate() + "\n");
        CLIPrinter.printMessage("| Return date: " + tripBean.getReturnDate() + "\n");
        CLIPrinter.printMessage("| Price: " + tripBean.getPrice() + "$\n");
        CLIPrinter.printMessage("--------------------------------\n");
    }

    private void printItinerary(List<ItineraryStopBean> itinerary) {
        CLIPrinter.printMessage("\n--------Trip itinerary:--------\n");
        for (ItineraryStopBean stop : itinerary) {
            CLIPrinter.printMessage("| City: " + stop.getCity() + "\n");
            CLIPrinter.printMessage("| Arrival: " + stop.getArrival() + "\n");
            CLIPrinter.printMessage("| Departure: " + stop.getDeparture() + "\n");
            CLIPrinter.printMessage("|------------------------------\n");
        }
    }

    public void menuForTraveler(){
        System.out.println("");
    }

    public void menuForGuideAsTraveler(){
        System.out.println("Munu for guide as traveler");
    }
}
