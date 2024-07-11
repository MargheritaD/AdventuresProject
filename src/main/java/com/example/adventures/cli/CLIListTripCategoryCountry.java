package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;

public class CLIListTripCategoryCountry extends AbstractCLI{
    public void start(List<TripBean> tripBeanList) throws NotFoundException {

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
   private void specificTrip(TripBean tripBean) throws NotFoundException {

        BookTripController bookTripController = new BookTripController();
        TripBean detailedTripBean = bookTripController.getTripDetails(tripBean);
        List<ItineraryStopBean> stops = bookTripController.tableItinerary(detailedTripBean);

        CLISelectedTrip selectedTrip = new CLISelectedTrip();
        selectedTrip.start(detailedTripBean, stops);
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
