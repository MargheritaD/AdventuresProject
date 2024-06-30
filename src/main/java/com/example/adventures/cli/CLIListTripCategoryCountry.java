package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class CLIListTripCategoryCountry extends AbstractCLI{

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

       BookTripController bookTripController = new BookTripController();
       CLIPrinter.printMessage("\nTrip details:\n\n");
       TripBean tripBean = bookTripController.tableTrip(trip);
       //CLIPrinter.printMessage("Trip name: " + tripBean.getTripName());
       CLIPrinter.printMessage("\nTrip itinerary:\n\n");
       List<ItineraryStopBean> stop = bookTripController.tableItinerary(trip);
       new CLISelectedTrip().start(tripBean, stop);
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
