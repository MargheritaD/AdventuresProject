package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.appcontroller.QuoteController;
import com.example.adventures.bean.*;
import com.example.adventures.engineering.Session;
import com.example.adventures.engineering.decoretor.*;
import com.example.adventures.engineering.decoretor.decorations.CancellationDecorator;
import com.example.adventures.engineering.decoretor.decorations.HealthcareDecorator;
import com.example.adventures.engineering.decoretor.decorations.LuggageDecorator;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;

public class CLISelectedTrip extends AbstractCLI {

    private String username;
    private boolean guideController = true;

    public void start(TripBean tripBean, List<ItineraryStopBean> itinerary) throws NotFoundException {
        printTripDetails(tripBean);
        printItinerary(itinerary);

        Session session = Session.getCurrentSession();
        if (session != null) {
            if (session.getGuideBean() != null) {
                handleGuideUser(session, tripBean);
            } else if (session.getTravelerBean() != null) {
                handleTravelerUser(session, tripBean);
            } else {
                CLIPrinter.printMessage("Error: Unrecognized user type.");
            }
        } else {
            CLIPrinter.printMessage("Error: Invalid session.");
        }
    }

    private void handleGuideUser(Session session, TripBean tripBean) throws NotFoundException{
        GuideBean guideBean = session.getGuideBean();
        username = guideBean.getName();

        if (!username.equals(tripBean.getGuide())) { // Non è l'organizzatore
            boolean choose = true;
            while (choose) {
                try {
                    int choice1 = menuForGuideAsTraveler();
                    switch (choice1) {
                        case 1 -> requestQuote(tripBean);

                        case 2 -> {
                            choose = false;
                            goHome();
                            }
                        case 3 -> {
                            choose = false;
                            logout();
                        }
                        case 4 -> {
                            choose = false;
                            System.exit(0);
                        }
                        default -> CLIPrinter.printMessage("Invalid choice!!");
                    }
                } catch (Exception e) {
                    throw new NotFoundException("Exception for Guide");
                }
            }
        } else { // È l'organizzatore
            handleGuideOptions();
        }
    }

    private void handleTravelerUser(Session session, TripBean tripBean) throws NotFoundException {
        TravelerBean travelerBean = session.getTravelerBean();
        username = travelerBean.getName();
        guideController = false;

        boolean choosee = true;
        while (choosee) {
            try {
                int choice = menuForTraveler();
                switch (choice) {
                    case 1 -> requestQuote(tripBean);
                    case 2 -> sendParticipationRequest(tripBean);
                    case 3 -> {
                        choosee = false;
                        goHome();
                        }
                    case 4 -> {
                        choosee = false;
                        logout();
                        }
                    case 5 -> {
                        choosee = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice!");
                }
            } catch (Exception e) {
                throw new NotFoundException("Exception for Traveler");
            }
        }
    }

    private void handleGuideOptions() throws NotFoundException {
        boolean scegli = true;
        while (scegli) {
            try {
                int scelta = munuForGuide();
                switch (scelta) {
                    case 1 -> CLIPrinter.printMessage("\nNOT IMPLEMENTED: \n");
                    case 2 -> {
                        scegli = false;
                        goHome();
                    }
                    case 3 -> {
                        scegli = false;
                        logout();
                    }
                    case 4 -> {
                        scegli = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice.");
                }
            } catch (Exception e) {
                throw new NotFoundException("Exception");
            }
        }
    }


    private void requestQuote(TripBean tripBean) {

        float tripPriceFloat = Float.parseFloat(tripBean.getPrice());
        // Converti il float in int (usando il casting)
        int tripPrice = (int) tripPriceFloat;

        Quote baseQuote = new TripPriceQuote(tripPrice);


        CLIPrinter.printMessage("\n     ---------------------------------------------------");
        CLIPrinter.printMessage("\n    | To calculate the quote, add the insurances you are |");
        CLIPrinter.printMessage("\n    | interested in one by one. When you're done, select |");
        CLIPrinter.printMessage("\n    | 'finish selection' to get the final calculation    |");
        CLIPrinter.printMessage("\n     ---------------------------------------------------\n");

        boolean selectingInsurances = true;
        while (selectingInsurances) {

            int choice2 = menuForQuote();
            switch (choice2) {
                case 1 -> {
                    baseQuote = new HealthcareDecorator(baseQuote);
                    CLIPrinter.printMessage("\n      ---------------------------");
                    CLIPrinter.printMessage("\n     | Healthcare insurance added |");
                    CLIPrinter.printMessage("\n      ---------------------------\n");
                }
                case 2 -> {
                    baseQuote = new LuggageDecorator(baseQuote);
                    CLIPrinter.printMessage("\n      ------------------------");
                    CLIPrinter.printMessage("\n     | Luggage insurance added |");
                    CLIPrinter.printMessage("\n      ------------------------\n");

                }
                case 3 -> {
                    baseQuote = new CancellationDecorator(baseQuote);
                    CLIPrinter.printMessage("\n      -----------------------------");
                    CLIPrinter.printMessage("\n     | Cancellation insurance added |");
                    CLIPrinter.printMessage("\n      -----------------------------\n");

                }
                case 4 -> selectingInsurances = false;

                default -> CLIPrinter.printMessage("Invalid choice");
            }
        }

        QuoteBean quoteBean = new QuoteBean(baseQuote.getPrice());
        QuoteController quoteController = new QuoteController();
        float totalQuote = quoteController.calculateQuote(quoteBean);
        displayQuoteResult(totalQuote);
    }


    private void sendParticipationRequest(TripBean tripBean) {
        // Controllo della sessione dell'utente
        Session session = Session.getCurrentSession();
        if (session != null && session.getTravelerBean() != null) {
            TravelerBean travelerBean = session.getTravelerBean();

            RequestBean requestBean = new RequestBean(tripBean.getIdTrip(), travelerBean.getId());

            BookTripController bookTripController = new BookTripController();
            bookTripController.sendRequest(requestBean);

            CLIPrinter.printMessage("\n     ----------------------------------------\n");
            CLIPrinter.printMessage("      Participation request sent successfully! \n");
            CLIPrinter.printMessage("      ----------------------------------------\n");
        } else {
            CLIPrinter.printMessage("Error: Traveler session not found!\n");
        }
    }

    private void printTripDetails(TripBean tripBean) {
        CLIPrinter.printMessage("\n\n    ---------------TRIP DETAILS:-----------------\n");
        CLIPrinter.printMessage("   | Trip name: " + tripBean.getTripName() + "\n");
        CLIPrinter.printMessage("   | Guide: " + tripBean.getGuide() + "\n");
        CLIPrinter.printMessage("   | Departure city: " + tripBean.getDepartureCity() + "\n");
        CLIPrinter.printMessage("   | Outbound date: " + tripBean.getOutboundDate() + "\n");
        CLIPrinter.printMessage("   | Return date: " + tripBean.getReturnDate() + "\n");
        CLIPrinter.printMessage("   | Price: " + tripBean.getPrice() + "$\n");
        CLIPrinter.printMessage("    ---------------------------------------------\n");
    }

    private void printItinerary(List<ItineraryStopBean> itinerary) {
        CLIPrinter.printMessage("\n   ---------------TRIP ITINERARY:---------------\n");
        for (ItineraryStopBean stop : itinerary) {
            CLIPrinter.printMessage("  | City: " + stop.getCity() + "\n");
            CLIPrinter.printMessage("  | Arrival: " + stop.getArrival() + "\n");
            CLIPrinter.printMessage("  | Departure: " + stop.getDeparture() + "\n");
            CLIPrinter.printMessage("  |--------------------------------------------\n");
        }
    }

    private int menuForTraveler() {

        CLIPrinter.printMessage("\n    -------------------\n");
        CLIPrinter.printMessage("   |Menu:              |\n");
        CLIPrinter.printMessage("   |-------------------|\n");
        CLIPrinter.printMessage("   |1) Request quote   |\n");
        CLIPrinter.printMessage("   |2) Send request    |\n");
        CLIPrinter.printMessage("   |3) Home            |\n");
        CLIPrinter.printMessage("   |4) Logout          |\n");
        CLIPrinter.printMessage("   |5) Quit            |\n");
        CLIPrinter.printMessage("    -------------------\n\n");

        return getMenuChoice(1, 5);
    }

    private int menuForGuideAsTraveler() {

        CLIPrinter.printMessage("\n    ------------------\n");
        CLIPrinter.printMessage("   |Menu:             |\n");
        CLIPrinter.printMessage("   |------------------|\n");
        CLIPrinter.printMessage("   |1) Request quote  |\n");
        CLIPrinter.printMessage("   |2) Home           |\n");
        CLIPrinter.printMessage("   |3) Logout         |\n");
        CLIPrinter.printMessage("   |4) Quit           |\n");
        CLIPrinter.printMessage("    ------------------\n\n");

        return getMenuChoice(1, 4);
    }

    private int munuForGuide() {

        CLIPrinter.printMessage("\n    -----------------\n");
        CLIPrinter.printMessage("   |Menu:            |\n");
        CLIPrinter.printMessage("   |-----------------|\n");
        CLIPrinter.printMessage("   |1) Edit trip     |\n");
        CLIPrinter.printMessage("   |2) Home          |\n");
        CLIPrinter.printMessage("   |3) Logout        |\n");
        CLIPrinter.printMessage("   |4) Quit          |\n");
        CLIPrinter.printMessage("    -----------------\n\n");

        return getMenuChoice(1, 4);
    }

    private void displayQuoteResult(float totalQuote) {
        CLIPrinter.printMessage("\n     --------QUOTE RESULT:-------\n");
        CLIPrinter.printMessage("     | Total price: " + totalQuote + " $     |\n");
        CLIPrinter.printMessage("      ----------------------------\n\n");
    }

    private int menuForQuote() {

        CLIPrinter.printMessage("\n     ---------------------------------------------------\n");
        CLIPrinter.printMessage("    |1) Healthcare insurance                             |\n");
        CLIPrinter.printMessage("    |2) Luggage insurance                                |\n");
        CLIPrinter.printMessage("    |3) Cancellation insurance                           |\n");
        CLIPrinter.printMessage("    |4) Finish selection                                 |\n");
        CLIPrinter.printMessage("     ---------------------------------------------------\n\n");

        return getMenuChoice(1, 4);
    }


}
