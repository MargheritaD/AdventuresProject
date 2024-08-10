package com.example.adventures.cli;

import com.example.adventures.appcontroller.NewTripController;
import com.example.adventures.bean.*;
import com.example.adventures.engineering.Session;
import com.example.adventures.utilities.CLIPrinter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLINewTrip extends AbstractCLI{

    private Scanner scanner = new Scanner(System.in);

    private static final String menuBordo = "   |----------------------------------------------------------------------------------------------------------------| \n";

    public void createTrip() {
        try {
            CLIPrinter.printMessage("   \n\n    ---------------------------------------------------------------------------------------------------------------- \n");
            CLIPrinter.printMessage("   |                                                   New trip!!                                                   |\n");
            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Trip name: ");
            String tripName = scanner.nextLine();

            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Departure city:");
            String departureCity = scanner.nextLine();

            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Choose category (Dog trekking, Food tasting, Fun, Relax, Safari, Sport):");
            String category = scanner.nextLine();

            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Price:");
            String price = scanner.nextLine();

            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Outbound date (YYYY-MM-DD):");
            LocalDate outboundDate = LocalDate.parse(scanner.nextLine());

            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Return date (YYYY-MM-DD):");
            LocalDate returnDate = LocalDate.parse(scanner.nextLine());

            CLIPrinter.printMessage(menuBordo);
            CLIPrinter.printMessage("   |Select country (Argentina, Australia, Brasil, Chile, India, Italy, Mexico, Perù, Spain):");
            String country = scanner.nextLine();

            List<ItineraryStopBean> stops = new ArrayList<>();
            boolean addingStops = true;
            while (addingStops) {
                CLIPrinter.printMessage(menuBordo);
                CLIPrinter.printMessage("   |Do you want to add a stop? (yes/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    CLIPrinter.printMessage(menuBordo);
                    CLIPrinter.printMessage("   |City name:");
                    String city = scanner.nextLine();

                    CLIPrinter.printMessage(menuBordo);
                    CLIPrinter.printMessage("   |Arrival (YYYY-MM-DD):");
                    LocalDate arrival = LocalDate.parse(scanner.nextLine());

                    CLIPrinter.printMessage(menuBordo);
                    CLIPrinter.printMessage("   |Departure (YYYY-MM-DD):");
                    LocalDate departure = LocalDate.parse(scanner.nextLine());

                    ItineraryStopBean stopBean = new ItineraryStopBean(city, arrival, departure);
                    stops.add(stopBean);
                } else {
                    CLIPrinter.printMessage("    ---------------------------------------------------------------------------------------------------------------- \n");
                    addingStops = false;
                }
            }

            // Creazione dei bean per le nuove classi
            LocationInfoBean locationInfoBean = new LocationInfoBean(departureCity, country);
            PeriodInfoBean periodInfoBean = new PeriodInfoBean(outboundDate, returnDate);

            TripBean tripBean = new TripBean(tripName, locationInfoBean, periodInfoBean, category, price);
            tripBean.setStops(stops);
            GuideBean guideBean = Session.getCurrentSession().getGuideBean();

            NewTripController newTripController = new NewTripController();
            newTripController.createTrip(tripBean, guideBean);

            CLIPrinter.printMessage("Viaggio creato con successo!");

        } catch (Exception e) {
            CLIPrinter.printMessage("Errore nella creazione del viaggio: " + e.getMessage());
        }
    }
}
