package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class CLIListTripCategoryCountry extends AbstractCLI{

    int role = 0;
    public void setRole(int role){
        this.role = role;
        System.out.println("CHI SONO: " + role);
    }

    public void start(List<TripBean> tripBeanList) throws SQLException, NotFoundException {

        boolean loop = true;

        while(loop) {
            int choice;
            int i = tripBeanList.size();
            int trip = -1;

            choice = showMenu(tripBeanList);
            if (choice <= i) {
                trip = choice - 1;
                choice = 1;
            } else {
                choice -= i - 1;
            }
            switch (choice) {
                case 1 -> {
                    loop = false;
                    specifyTrip(tripBeanList.get(trip).getIdTrip());
                }
                case 2 -> {
                    loop = false;
                    goHome();
                }
                case 3 -> {
                    loop = false;
                    goBack();
                }
                case 4 -> {
                    loop = false;
                    logout();
                }
                case 5 -> {
                    loop = false;
                    System.exit(0);
                }
                default -> CLIPrinter.printMessage("Invalid choice");
            }
        }
    }

   /* private void viewDetails(int idTrip) throws SQLException, NotFoundException {
        TripBean tripBean = new BookTripController().tableTrip(idTrip);
        CLIPrinter.printMessage("Trip name: " + tripBean.getTripName());
        CLIPrinter.printMessage("Country: " + tripBean.getCountry());
        CLIPrinter.printMessage("Departure city: " + tripBean.getDepartureCity());
        CLIPrinter.printMessage("Outbound date:" + tripBean.getOutboundDate());
        CLIPrinter.printMessage("Return date:" + tripBean.getReturnDate());
        CLIPrinter.printMessage("Price: " + tripBean.getPrice());

        List<ItineraryStopBean> stops = new BookTripController().tableItinerary(idTrip);
        for (ItineraryStopBean itineraryStopBean : stops) {
            CLIPrinter.printMessage("City: " + itineraryStopBean.getCity());
            CLIPrinter.printMessage("Arrival : " + itineraryStopBean.getArrival());
            CLIPrinter.printMessage("Departure: " + itineraryStopBean.getDeparture());
        }
        //new CLISelectedTrip().start();
    }

    */
   private void specifyTrip(int trip) throws SQLException, NotFoundException {
/*
       BookTripController bookTripController = new BookTripController();
       CLIPrinter.printMessage("\n--------Trip details:-----------\n");
       CLIPrinter.printMessage("|\n");
       TripBean tripBean = bookTripController.tableTrip(trip);
       CLIPrinter.printMessage("| Trip name: " + tripBean.getTripName()+ "\n");
       CLIPrinter.printMessage("| Guide: " + tripBean.getPrice()+ "\n");
       CLIPrinter.printMessage("| Departure city: " + tripBean.getDepartureCity()+ "\n");
       CLIPrinter.printMessage("| Outbound date: " + tripBean.getOutboundDate()+ "\n");
       CLIPrinter.printMessage("| Return date: " + tripBean.getReturnDate()+ "\n");
       CLIPrinter.printMessage("| Price: " + tripBean.getGuide()+ "$\n");
       CLIPrinter.printMessage("|\n");
       CLIPrinter.printMessage("--------------------------------");

       List<ItineraryStopBean> stops = bookTripController.tableItinerary(trip);
       CLIPrinter.printMessage("\n\n--------Trip itinerary:--------\n");
       CLIPrinter.printMessage("|\n");
       for (ItineraryStopBean stop : stops) {
           CLIPrinter.printMessage("| City: " + stop.getCity() + "\n");
           CLIPrinter.printMessage("| Arrival: " + stop.getArrival() + "\n");
           CLIPrinter.printMessage("| Departure: " + stop.getDeparture() + "\n");
           CLIPrinter.printMessage("|\n");
           CLIPrinter.printMessage("--------------------------------\n");
       }

       // Verifica se la guida del viaggio è uguale all'utente loggato

       // Mostra il menu appropriato in base alla condizione
       if (isGuideSameAsLoggedUser) {
           showMenuForSameGuide();
       } else {
           showMenuForDifferentGuide();
       }


 */

       BookTripController bookTripController = new BookTripController();
       TripBean tripBean = bookTripController.tableTrip(trip);
       List<ItineraryStopBean> stops = bookTripController.tableItinerary(trip);

       CLISelectedTrip selectedTrip = new CLISelectedTrip();
       selectedTrip.setRole(role);
       selectedTrip.start(tripBean, stops);

       //new CLISelectedTrip().start(tripBean, stops);
   }

    private void showMenuForSameGuide() {
        CLIPrinter.printMessage("\nMenu:\n");
        CLIPrinter.printMessage("1. Go back\n");
        CLIPrinter.printMessage("2. Go home\n");
        CLIPrinter.printMessage("3. Logout\n");

        int choice = getMenuChoice(1, 3);
        switch (choice) {
            case 1:
                goBack();
                break;
            case 2:
                goHome();
                break;
            case 3:
                logout();
                break;
            default:
                CLIPrinter.printMessage("Invalid choice\n");
        }
    }
    private void showMenuForDifferentGuide() {
        CLIPrinter.printMessage("\nMenu:\n");
        CLIPrinter.printMessage("1. Send participation request\n");
        CLIPrinter.printMessage("2. Go back\n");
        CLIPrinter.printMessage("3. Go home\n");
        CLIPrinter.printMessage("4. Logout\n");

        int choice = getMenuChoice(1, 4);
        switch (choice) {
            case 1:
                // Implementa il codice per inviare una richiesta di partecipazione
                CLIPrinter.printMessage("Sending participation request...\n");
                break;
            case 2:
                goBack();
                break;
            case 3:
                goHome();
                break;
            case 4:
                logout();
                break;
            default:
                CLIPrinter.printMessage("Invalid choice\n");
        }
    }


    private int showMenu(List<TripBean> trips)  {

        int i = 1;
        CLIPrinter.printMessage("\n*************************************************************************\n");
        CLIPrinter.printMessage("\nSelect a trip: \n\n");
        for (TripBean tripBean : trips){
            CLIPrinter.printListOfTrips(i , tripBean.getTripName());
            i++;
        }
        CLIPrinter.printMessage("-----------\n");
        CLIPrinter.printNumbers(i); CLIPrinter.printMessage("Go home\n");
        CLIPrinter.printNumbers(i+1);
        CLIPrinter.printMessage("Go back\n");
        CLIPrinter.printNumbers(i+2);
        CLIPrinter.printMessage("ViewMessages\n");
        CLIPrinter.printNumbers(i+3);
        CLIPrinter.printMessage("Logout\n");
        CLIPrinter.printNumbers(i+4);
        CLIPrinter.printMessage("Quit\n");

        return getMenuChoice(1,i+4);
    }

    private void goBack(){
        new CLIHomeGuide().start(); // metti session per fare l'if
    }

}
