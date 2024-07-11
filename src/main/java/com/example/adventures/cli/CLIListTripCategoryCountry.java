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

            for (TripBean tripBean : tripBeanList) {
                System.out.println("DEBUG: Trip ID: " + tripBean.getIdTrip());
                System.out.println("DEBUG: Trip Name: " + tripBean.getTripName());
                System.out.println("DEBUG: Trip Country: " + tripBean.getCountry());
                System.out.println("DEBUG: Trip City: " + tripBean.getDepartureCity());
                System.out.println("DEBUG: Trip Price: " + tripBean.getPrice());
            }

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
                    specificTrip(tripBeanList.get(trip));
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
   private void specificTrip(TripBean tripBean){

        System.out.println("\n\nTrip price: "+ tripBean.getPrice()+ "dentro specificList\n");
        System.out.println("\n\nTrip price: "+ tripBean.getGuide()+ "dentro specificList\n");
        System.out.println("\n\nTrip price: "+ tripBean.getDepartureCity()+ "dentro specificList\n");
        System.out.println("\n\nTrip price: "+ tripBean.getOutboundDate()+ "dentro specificList\n");
        BookTripController bookTripController = new BookTripController();
        List<ItineraryStopBean> stops = bookTripController.tableItinerary(tripBean.getIdTrip());
        CLISelectedTrip selectedTrip = new CLISelectedTrip();
        selectedTrip.start(tripBean, stops);
    }



    private int showMenu(List<TripBean> trips)  {

        int i = 1;
        CLIPrinter.printMessage("\n*************************************************************************\n");
        CLIPrinter.printMessage("\nSelect a trip: \n\n");
        for (TripBean tripBean : trips){
            CLIPrinter.printListOfTrips(i , tripBean.getTripName());
            i++;
            System.out.println("\nLIST CATEGORY : ");
            System.out.println("\nTrip ID: " + tripBean.getIdTrip());
            System.out.println("\nTrip Name: " + tripBean.getTripName());
            System.out.println("\nTrip Country: " + tripBean.getCountry());
            System.out.println("\nTrip city: " + tripBean.getDepartureCity());
            System.out.println("\nTrip price: " + tripBean.getPrice());
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
