package com.example.adventures.cli;

public class CLIQuote {


    /*
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
                        int choice1;
                        //int choice2;
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

     */
}
