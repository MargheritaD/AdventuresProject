package com.example.adventures.viewcli;

import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.engineering.Printer;
import com.example.adventures.graphiccontroller.cli.NewTripCLIController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewTripViewCLI {
/*
    private NewTripCLIController newTripCLIController;
   // private FormViewCLI formViewCLI;

    public NewTripCLIController(NewTripCLIController newTripCLIController) {
        this.newTripCLIController = newTripCLIController;
        //this.formViewCLI = new FormViewCLI();
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        Printer.printMessage("\nInsert Trip Name:");
        String tripName = scanner.nextLine();

        Printer.printMessage("Insert Departure City:");
        String departureCity = scanner.nextLine();

        String category = formViewCLI.printSelectionCategory();

        Printer.printMessage("Insert Outbound Date (YYYY-MM-DD):");
        String outboundDate = scanner.nextLine();

        Printer.printMessage("Insert Return Date (YYYY-MM-DD):");
        String returnDate = scanner.nextLine();

        Printer.printMessage("Insert Price:");
        String price = scanner.nextLine();

        Printer.printMessage("Insert Guide:");
        String guide = scanner.nextLine();

        String country = formViewCLI.printSelectionCountry();

        List<ItineraryStopBean> stops = new ArrayList<>();
        boolean addStop = true;
        while (addStop) {
            Printer.printMessage("Add Stop:");
            String city = scanner.nextLine();
            Printer.printMessage("Arrival Date (YYYY-MM-DD):");
            String arrivalDate = scanner.nextLine();
            Printer.printMessage("Departure Date (YYYY-MM-DD):");
            String departureDate = scanner.nextLine();

            stops.add(new ItineraryStopBean(city, arrivalDate, departureDate));

            Printer.printMessage("Do you want to add another stop? (yes/no):");
            String addAnotherStop = scanner.nextLine();
            addStop = addAnotherStop.equalsIgnoreCase("yes");
        }

        try {
            NewTripCLIController.createTrip(tripName, departureCity, category, outboundDate, returnDate, price, guide, country);
        } catch (Exception e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }

        Printer.printMessage("Trip added successfully!");
    }
*/
}
