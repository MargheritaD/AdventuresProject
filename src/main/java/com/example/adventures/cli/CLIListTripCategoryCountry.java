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

   private void specifyTrip(int trip) throws SQLException, NotFoundException {

       BookTripController bookTripController = new BookTripController();
       TripBean tripBean = bookTripController.tableTrip(trip);
       List<ItineraryStopBean> stops = bookTripController.tableItinerary(trip);

       CLISelectedTrip selectedTrip = new CLISelectedTrip();
       selectedTrip.start(tripBean, stops);
   }

   /*
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

    */
    /*
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

     */


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
