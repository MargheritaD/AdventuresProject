package com.example.adventures.cli;

import com.example.adventures.bean.*;
import com.example.adventures.engineering.Session;
import com.example.adventures.engineering.decoretor.CancellationQuote;
import com.example.adventures.engineering.decoretor.HealthQuote;
import com.example.adventures.engineering.decoretor.LuggageQuote;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

import static com.example.adventures.cli.AbstractCLI.getMenuChoice;

public class CLISelectedTrip {

  //  int role = 0;
    private String username;
    private boolean guideController = true;

    private float tripPrice;

 /*   public void setRole(int role){
        this.role = role;
        System.out.println("CHI SONO dentro selected trip: " + role);
    }

  */


    public void start(TripBean tripBean, List<ItineraryStopBean> itinerary) {

        printTripDetails(tripBean);
        printItinerary(itinerary);


        // Controlla il tipo di utente attualmente loggato
        Session session = Session.getCurrentSession();
        if(session != null) {

            if(session.getGuideBean() != null) { // È un utente Guida

                GuideBean guideBean = session.getGuideBean();
                username = guideBean.getName();

                if(!username.equals(tripBean.getGuide())){ // non è l'organizzatore

                    boolean choose = true;
                    while(choose){
                        int choice1;
                        int choice2;
                        try{
                            choice1 = menuForGuideAsTraveler();
                            switch (choice1){
                                case 1 -> { // Request quote
                                    tripPrice = Float.parseFloat(tripBean.getGuide());
                                    QuoteBean quoteBean = new QuoteBean(tripPrice);
                                    System.out.println("        PREZZO: " + tripPrice);

                                    choice2 = menuForQuote();

                                    switch (choice2){
                                        case 1 -> {
                                            quoteBean.addInsurance(new HealthQuote());
                                            System.out.println(quoteBean);
                                        }
                                        case 2 -> {
                                            quoteBean.addInsurance(new LuggageQuote());
                                        }
                                        case 3 -> {
                                            quoteBean.addInsurance(new CancellationQuote());
                                        }
                                    }
                                }
                                case 2 -> {
                                    System.out.println("back");
                                }
                                case 3 -> {
                                    System.out.println("home");
                                }
                                case  4 -> {
                                    System.out.println("logout");
                                }
                                case 5 -> {
                                    System.out.println("quit");
                                }
                            }
                        }catch(Exception e) {
                            throw new RuntimeException(e);
                        }

                    }


                }else{ // è l'organizzatore

                    boolean scegli = true;
                    while(scegli){
                        int scelta;
                        try{
                            scelta = munuForGuide();
                            switch (scelta){
                                case 1 -> { // Edit trip
                                    CLIPrinter.printMessage("\nNOT IMPLEMENTED: \n");
                                }
                                case 2 -> { // Back
                                    System.out.println("back");
                                }
                                case 3 -> { // Home
                                    System.out.println("home");
                                }
                                case  4 -> { // Logout
                                    System.out.println("logout");
                                }
                                case 5 -> { // quit
                                    System.out.println("quit");
                                }
                            }
                        }catch(Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            } else if(session.getTravelerBean() != null) { // È un utente Viaggiatore

                TravelerBean travelerBean = session.getTravelerBean();
                username = travelerBean.getName(); // Utilizza l'email anche per i viaggiatori
                guideController = false;
                menuForTraveler();


            } else {
                // Tipo di utente non riconosciuto
            }
        } else {
            // Sessione non valida
        }

    }

    private void printTripDetails(TripBean tripBean) {
        CLIPrinter.printMessage("\n--------TRIP DETAILS:-----------\n");
        CLIPrinter.printMessage("| Trip name: " + tripBean.getTripName() + "\n");
        CLIPrinter.printMessage("| Guide: " + tripBean.getGuide() + "\n");
        CLIPrinter.printMessage("| Departure city: " + tripBean.getDepartureCity() + "\n");
        CLIPrinter.printMessage("| Outbound date: " + tripBean.getOutboundDate() + "\n");
        CLIPrinter.printMessage("| Return date: " + tripBean.getReturnDate() + "\n");
        CLIPrinter.printMessage("| Price: " + tripBean.getPrice() + "$\n");
        CLIPrinter.printMessage("--------------------------------\n");
    }

    private void printItinerary(List<ItineraryStopBean> itinerary) {
        CLIPrinter.printMessage("\n--------TRIP ITINERARY:--------\n");
        for (ItineraryStopBean stop : itinerary) {
            CLIPrinter.printMessage("| City: " + stop.getCity() + "\n");
            CLIPrinter.printMessage("| Arrival: " + stop.getArrival() + "\n");
            CLIPrinter.printMessage("| Departure: " + stop.getDeparture() + "\n");
            CLIPrinter.printMessage("|------------------------------\n");
        }
    }

    private int  menuForTraveler(){

        // 1. preventivo
        // 2. richiesta
        // 3. back
        // 4. home
        // 5. quite
        System.out.println("");

        return  getMenuChoice(1,5);
    }

    private int menuForGuideAsTraveler(){


        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Request quote \n");
        CLIPrinter.printMessage("2) Back \n");
        CLIPrinter.printMessage("3) Home \n");
        CLIPrinter.printMessage("4) Logout \n");
        CLIPrinter.printMessage("5) Quit \n");

        return  getMenuChoice(1,5);
    }

    private int munuForGuide(){

        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Edit trip \n");
        CLIPrinter.printMessage("2) Back \n");
        CLIPrinter.printMessage("3) Home \n");
        CLIPrinter.printMessage("4) Logout \n");
        CLIPrinter.printMessage("5) Quit \n");

        return  getMenuChoice(1,5);
    }

    private int menuForQuote(){

        CLIPrinter.printMessage("\nCalculate your quote: \n");
        CLIPrinter.printMessage("1) Healthcare insurance  \n");
        CLIPrinter.printMessage("2) Luggage insurance \n");
        CLIPrinter.printMessage("3) Cancellation insurance \n\n");

        return  getMenuChoice(1,3);
    }
}
