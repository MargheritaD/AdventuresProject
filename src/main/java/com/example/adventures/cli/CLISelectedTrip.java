package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
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

public class CLISelectedTrip extends AbstractCLI {

    /*

  //  int role = 0;
    private String username;
    private boolean guideController = true;
    private float tripPrice;


    public void start(TripBean tripBean, List<ItineraryStopBean> itinerary) {

        printTripDetails(tripBean);
        printItinerary(itinerary);


        // Controlla il tipo di utente attualmente loggato
        Session session = Session.getCurrentSession();
        if(session != null) {

            if(session.getGuideBean() != null) { // È un utente Guida

                GuideBean guideBean = session.getGuideBean();
                username = guideBean.getName();

                System.out.println("Tripbean.getGuide = "+ tripBean.getPrice());

                // Sono invertiti getPrice e getGuide come risultato
                if(!username.equals(tripBean.getPrice())){ // non è l'organizzatore

                    boolean choose = true;
                    while(choose){
                        //int choice2;
                        try{
                           int choice1 = menuForGuideAsTraveler();
                            switch (choice1){
                                case 1 -> { // Request quote
                                    requestQuote(tripBean);
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
                                    choose = false;
                                }
                            }
                        }catch(Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                }else{ // è l'organizzatore

                    boolean scegli = true;
                    while(scegli){
                        try{
                            int scelta = munuForGuide();
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
                                    scegli = false;
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

                boolean choose = true;
                while(choose){
                    try{
                        int choice = menuForTraveler();
                        switch (choice){
                            case 1 -> { // Request quote
                                requestQuote(tripBean);
                            }
                            case 2 -> {
                                System.out.println("Send request");
                            }
                            case 3 -> {
                                choose = false;
                                goBack();
                            }
                            case 4 -> {
                                choose = false;
                                goHome();
                            }
                            case 5 -> {
                                choose = false;
                                logout();
                            }
                            case 6 -> {
                                choose = false;
                                System.exit(0);
                            }
                        }
                    } catch(Exception e) {
                        throw new RuntimeException(e);
                    }
                }

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
        CLIPrinter.printMessage("| Guide: " + tripBean.getPrice() + "\n");
        CLIPrinter.printMessage("| Departure city: " + tripBean.getDepartureCity() + "\n");
        CLIPrinter.printMessage("| Outbound date: " + tripBean.getOutboundDate() + "\n");
        CLIPrinter.printMessage("| Return date: " + tripBean.getReturnDate() + "\n");
        CLIPrinter.printMessage("| Price: " + tripBean.getGuide() + "$\n");
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

        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Request quote \n");
        CLIPrinter.printMessage("2) Send request \n");
        CLIPrinter.printMessage("3) Back \n");
        CLIPrinter.printMessage("4) Home \n");
        CLIPrinter.printMessage("5) Logout \n");
        CLIPrinter.printMessage("6) Quit \n");

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

    private void requestQuote(TripBean tripBean) {
        tripPrice = Float.parseFloat(tripBean.getGuide());
        QuoteBean quoteBean = new QuoteBean(tripPrice);

        boolean selectingInsurances = true;
        while (selectingInsurances) {
            int choice2 = menuForQuote();
            switch (choice2) {
                case 1 -> {
                    quoteBean.addInsurance(new HealthQuote());
                    System.out.println("Healthcare insurance added.");
                }
                case 2 -> {
                    quoteBean.addInsurance(new LuggageQuote());
                    System.out.println("Luggage insurance added.");
                }
                case 3 -> {
                    quoteBean.addInsurance(new CancellationQuote());
                    System.out.println("Cancellation insurance added.");
                }
                case 4 -> selectingInsurances = false;
            }
        }

        BookTripController quoteController = new BookTripController();
        float totalQuote = quoteController.calculateQuote(quoteBean);
        System.out.println("Total price: " + totalQuote + " $");
    }


    private int menuForQuote(){

        CLIPrinter.printMessage("\nCalculate your quote: \n");
        CLIPrinter.printMessage("1) Healthcare insurance  \n");
        CLIPrinter.printMessage("2) Luggage insurance \n");
        CLIPrinter.printMessage("3) Cancellation insurance \n");
        CLIPrinter.printMessage("4) Finish selection \n");

        return  getMenuChoice(1,4);
    }

     */
    private String username;
    private boolean guideController = true;
    private float tripPrice;

    public void start(TripBean tripBean, List<ItineraryStopBean> itinerary) {
        printTripDetails(tripBean);
        printItinerary(itinerary);

        Session session = Session.getCurrentSession();
        if (session != null) {
            if (session.getGuideBean() != null) {
                handleGuideUser(session, tripBean);
            } else if (session.getTravelerBean() != null) {
                handleTravelerUser(session, tripBean);
            } else {
                // Tipo di utente non riconosciuto
            }
        } else {
            // Sessione non valida
        }
    }

    private void handleGuideUser(Session session, TripBean tripBean) {
        GuideBean guideBean = session.getGuideBean();
        username = guideBean.getName();

        if (!username.equals(tripBean.getPrice())) { // Non è l'organizzatore
            boolean choose = true;
            while (choose) {
                try {
                    int choice1 = menuForGuideAsTraveler();
                    switch (choice1) {
                        case 1 -> requestQuote(tripBean);
                        case 2 -> System.out.println("back");
                        case 3 -> System.out.println("home");
                        case 4 -> System.out.println("logout");
                        case 5 -> {
                            System.out.println("quit");
                            choose = false;
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else { // È l'organizzatore
            handleOrganizerOptions();
        }
    }

    private void handleTravelerUser(Session session, TripBean tripBean) {
        TravelerBean travelerBean = session.getTravelerBean();
        username = travelerBean.getName();
        guideController = false;

        boolean choose = true;
        while (choose) {
            try {
                int choice = menuForTraveler();
                switch (choice) {
                    case 1 -> requestQuote(tripBean);
                    case 2 -> System.out.println("Send request");
                    case 3 -> System.out.println("back");
                    case 4 -> System.out.println("home");
                    case 5 -> System.out.println("logout");
                    case 6 -> {
                        System.out.println("quit");
                        choose = false;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleOrganizerOptions() {
        boolean scegli = true;
        while (scegli) {
            try {
                int scelta = munuForGuide();
                switch (scelta) {
                    case 1 -> CLIPrinter.printMessage("\nNOT IMPLEMENTED: \n");
                    case 2 -> System.out.println("back");
                    case 3 -> System.out.println("home");
                    case 4 -> System.out.println("logout");
                    case 5 -> {
                        System.out.println("quit");
                        scegli = false;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printTripDetails(TripBean tripBean) {
        CLIPrinter.printMessage("\n--------TRIP DETAILS:-----------\n");
        CLIPrinter.printMessage("| Trip name: " + tripBean.getTripName() + "\n");
        CLIPrinter.printMessage("| Guide: " + tripBean.getPrice() + "\n");
        CLIPrinter.printMessage("| Departure city: " + tripBean.getDepartureCity() + "\n");
        CLIPrinter.printMessage("| Outbound date: " + tripBean.getOutboundDate() + "\n");
        CLIPrinter.printMessage("| Return date: " + tripBean.getReturnDate() + "\n");
        CLIPrinter.printMessage("| Price: " + tripBean.getGuide() + "$\n");
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

    private int menuForTraveler() {
        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Request quote \n");
        CLIPrinter.printMessage("2) Send request \n");
        CLIPrinter.printMessage("3) Back \n");
        CLIPrinter.printMessage("4) Home \n");
        CLIPrinter.printMessage("5) Logout \n");
        CLIPrinter.printMessage("6) Quit \n");

        return getMenuChoice(1, 6);
    }

    private int menuForGuideAsTraveler() {
        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Request quote \n");
        CLIPrinter.printMessage("2) Back \n");
        CLIPrinter.printMessage("3) Home \n");
        CLIPrinter.printMessage("4) Logout \n");
        CLIPrinter.printMessage("5) Quit \n");

        return getMenuChoice(1, 5);
    }

    private int munuForGuide() {
        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Edit trip \n");
        CLIPrinter.printMessage("2) Back \n");
        CLIPrinter.printMessage("3) Home \n");
        CLIPrinter.printMessage("4) Logout \n");
        CLIPrinter.printMessage("5) Quit \n");

        return getMenuChoice(1, 5);
    }

    private void requestQuote(TripBean tripBean) {
        tripPrice = Float.parseFloat(tripBean.getGuide());
        QuoteBean quoteBean = new QuoteBean(tripPrice);

        boolean selectingInsurances = true;
        while (selectingInsurances) {
            int choice2 = menuForQuote();
            switch (choice2) {
                case 1 -> {
                    quoteBean.addInsurance(new HealthQuote());
                    System.out.println("Healthcare insurance added.");
                }
                case 2 -> {
                    quoteBean.addInsurance(new LuggageQuote());
                    System.out.println("Luggage insurance added.");
                }
                case 3 -> {
                    quoteBean.addInsurance(new CancellationQuote());
                    System.out.println("Cancellation insurance added.");
                }
                case 4 -> selectingInsurances = false;
            }
        }

        BookTripController quoteController = new BookTripController();
        float totalQuote = quoteController.calculateQuote(quoteBean);
        displayQuoteResult(totalQuote);
    }

    private void displayQuoteResult(float totalQuote) {
        CLIPrinter.printMessage("\n--------QUOTE RESULT:--------\n");
        CLIPrinter.printMessage("Total price: " + totalQuote + " $\n");
        CLIPrinter.printMessage("-----------------------------\n");
    }

    private int menuForQuote() {
        CLIPrinter.printMessage("\nCalculate your quote: \n");
        CLIPrinter.printMessage("1) Healthcare insurance  \n");
        CLIPrinter.printMessage("2) Luggage insurance \n");
        CLIPrinter.printMessage("3) Cancellation insurance \n");
        CLIPrinter.printMessage("4) Finish selection \n");

        return getMenuChoice(1, 4);
    }

}
