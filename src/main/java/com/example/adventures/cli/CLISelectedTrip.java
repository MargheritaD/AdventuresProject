package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.appcontroller.QuoteController;
import com.example.adventures.bean.*;
import com.example.adventures.engineering.Session;
import com.example.adventures.engineering.decoretor.*;
import com.example.adventures.engineering.decoretor.decorations.CancellationDecorator;
import com.example.adventures.engineering.decoretor.decorations.HealthcareDecorator;
import com.example.adventures.engineering.decoretor.decorations.LuggageDecorator;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;

import static java.lang.Integer.parseInt;

public class CLISelectedTrip extends AbstractCLI {

    private String username;
    private boolean guideController = true;
    private int tripPrice;
    private String country;

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
                        case 1 -> {
                            System.out.println("Prima di richiedere il preventivo");
                            requestQuote(tripBean);
                            System.out.println("Dopo averlo chioesto");
                        }

                        case 2 -> CLIPrinter.printMessage("back");

                        case 3 -> {
                            choose = false;
                            goHome();
                            }
                        case 4 -> {
                            choose = false;
                            logout();
                        }
                        case 5 -> {
                            choose = false;
                            System.exit(0);
                        }
                        default -> CLIPrinter.printMessage("Invalid choice!!");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else { // È l'organizzatore
            handleGuideOptions();
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
                    case 2 -> sendParticipationRequest(tripBean);
                    case 3 -> CLIPrinter.printMessage("back");
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
                    default -> CLIPrinter.printMessage("Invalid choice!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleGuideOptions() {
        boolean scegli = true;
        while (scegli) {
            try {
                int scelta = munuForGuide();
                switch (scelta) {
                    case 1 -> CLIPrinter.printMessage("\nNOT IMPLEMENTED: \n");
                    case 2 -> CLIPrinter.printMessage("back");
                    case 3 -> {
                        scegli = false;
                        goHome();
                    }
                    case 4 -> {
                        scegli = false;
                        logout();
                    }
                    case 5 -> {
                        scegli = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void requestQuote(TripBean tripBean) {

        System.out.println("Entro nella richiesta preventivo");

        System.out.println("Entro nella richiesta preventivo prezzo " + tripBean.getPrice());

        float tripPriceFloat = Float.parseFloat(tripBean.getPrice());
        // Converti il float in int (usando il casting)
        int tripPrice = (int) tripPriceFloat;
        String country = tripBean.getCountry();

        System.out.println("Prezzo del viaggio: " + tripPrice);
        System.out.println("Country: "+country);

        Quote baseQuote = new TripPriceQuote(tripPrice);

        System.out.println(baseQuote);

        // Aggiungi la tassa per destinazione specifica
        if("Australia".equals(country)){
            baseQuote = new AustraliaQuote(baseQuote);
        } else if ("Argentina".equals(country)) {
            baseQuote = new ArgentinaQuote(baseQuote);
        }else if ("Brasil".equals(country)) {
            baseQuote = new BrasilQuote(baseQuote);
        }else if ("Chile".equals(country)) {
            baseQuote = new ChileQuote(baseQuote);
        }else if ("India".equals(country)) {
            baseQuote = new IndiaQuote(baseQuote);
        }else if ("Italy".equals(country)) {
            baseQuote = new ItalyQuote(baseQuote);
        }else if ("Mexico".equals(country)) {
            baseQuote = new MexicoQuote(baseQuote);
        }else if ("Perù".equals(country)) {
            baseQuote = new PuruQuote(baseQuote);
        }else if ("Spain".equals(country)) {
            baseQuote = new SpainQuote(baseQuote);
        }else if ("USA".equals(country)) {
            baseQuote = new USAQuote(baseQuote);
        }


        //tripPrice = Float.parseFloat(tripBean.getPrice());
        //QuoteBean quoteBean = new QuoteBean(tripPrice);

        CLIPrinter.printMessage("\n ---------------------------------------------------");
        CLIPrinter.printMessage("\n| To calculate the quote, add the insurances you are |");
        CLIPrinter.printMessage("\n| interested in one by one. When you're done, select |");
        CLIPrinter.printMessage("\n| 'finish selection' to get the final calculation    |");
        CLIPrinter.printMessage("\n ---------------------------------------------------\n");

        boolean selectingInsurances = true;
        while (selectingInsurances) {

            int choice2 = menuForQuote();
            switch (choice2) {
                case 1 -> {
                    baseQuote = new HealthcareDecorator(baseQuote);
                    //quoteBean.addInsurance(new HealthQuote());
                    CLIPrinter.printMessage("\n ---------------------------");
                    CLIPrinter.printMessage("\n| Healthcare insurance added |");
                    CLIPrinter.printMessage("\n ---------------------------\n");
                }
                case 2 -> {
                    baseQuote = new LuggageDecorator(baseQuote);

                    //quoteBean.addInsurance(new LuggageQuote());
                    CLIPrinter.printMessage("\n ------------------------");
                    CLIPrinter.printMessage("\n| Luggage insurance added |");
                    CLIPrinter.printMessage("\n ------------------------\n");

                }
                case 3 -> {
                    baseQuote = new CancellationDecorator(baseQuote);
                    //quoteBean.addInsurance(new CancellationQuote());
                    CLIPrinter.printMessage("\n -----------------------------");
                    CLIPrinter.printMessage("\n| Cancellation insurance added |");
                    CLIPrinter.printMessage("\n -----------------------------\n");

                }
                case 4 -> selectingInsurances = false;

                default -> CLIPrinter.printMessage("Invalid choice");
            }
        }

        QuoteBean quoteBean = new QuoteBean(baseQuote.getPrice());
        QuoteController quoteController = new QuoteController();
        float totalQuote = quoteController.calculateQuote(quoteBean);

        //BookTripController quoteController = new BookTripController();
        //float totalQuote = quoteController.calculateQuote(quoteBean);
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

            CLIPrinter.printMessage("\n----------------------------------------\n");
            CLIPrinter.printMessage("Participation request sent successfully!\n");
            CLIPrinter.printMessage("----------------------------------------\n");
        } else {
            CLIPrinter.printMessage("Error: Traveler session not found!\n");
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

    private int menuForTraveler() {
        CLIPrinter.printMessage("\nMenù: \n");
        CLIPrinter.printMessage("\n1) Request quote \n");
        CLIPrinter.printMessage("2) Send request \n");
        CLIPrinter.printMessage("3) Back \n");
        CLIPrinter.printMessage("4) Home \n");
        CLIPrinter.printMessage("5) Logout \n");
        CLIPrinter.printMessage("6) Quit \n");

        return getMenuChoice(1, 6);
    }

    private int menuForGuideAsTraveler() {
        CLIPrinter.printMessage("\nMenu°: \n");
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

    private void displayQuoteResult(float totalQuote) {
        CLIPrinter.printMessage("\n--------QUOTE RESULT:-------\n");
        CLIPrinter.printMessage("| Total price: " + totalQuote + " $     |\n");
        CLIPrinter.printMessage("----------------------------\n");
    }

    private int menuForQuote() {

        CLIPrinter.printMessage("   1) Healthcare insurance  \n");
        CLIPrinter.printMessage("   2) Luggage insurance \n");
        CLIPrinter.printMessage("   3) Cancellation insurance \n");
        CLIPrinter.printMessage("   4) Finish selection \n\n");

        return getMenuChoice(1, 4);
    }

}
