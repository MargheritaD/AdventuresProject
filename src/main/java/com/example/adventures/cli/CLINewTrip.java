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

    public void createTrip() {
        try {
            CLIPrinter.printMessage("Inserisci il nome del viaggio:");
            String tripName = scanner.nextLine();

            CLIPrinter.printMessage("Inserisci la città di partenza:");
            String departureCity = scanner.nextLine();

            CLIPrinter.printMessage("Inserisci la categoria (Dog trekking, Food tasting, Fun, Relax, Safari, Sport):");
            String category = scanner.nextLine();

            CLIPrinter.printMessage("Inserisci il prezzo:");
            String price = scanner.nextLine();

            CLIPrinter.printMessage("Inserisci la data di partenza (AAAA-MM-GG):");
            LocalDate outboundDate = LocalDate.parse(scanner.nextLine());

            CLIPrinter.printMessage("Inserisci la data di ritorno (AAAA-MM-GG):");
            LocalDate returnDate = LocalDate.parse(scanner.nextLine());

            CLIPrinter.printMessage("Inserisci il paese (Argentina, Australia, Brasil, Chile, India, Italy, Mexico, Perù, Spain):");
            String country = scanner.nextLine();

            List<ItineraryStopBean> stops = new ArrayList<>();
            boolean addingStops = true;
            while (addingStops) {
                CLIPrinter.printMessage("Vuoi aggiungere una tappa? (si/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("si")) {
                    CLIPrinter.printMessage("Inserisci la città della tappa:");
                    String city = scanner.nextLine();

                    CLIPrinter.printMessage("Inserisci la data di arrivo della tappa (AAAA-MM-GG):");
                    LocalDate arrival = LocalDate.parse(scanner.nextLine());

                    CLIPrinter.printMessage("Inserisci la data di partenza della tappa (AAAA-MM-GG):");
                    LocalDate departure = LocalDate.parse(scanner.nextLine());

                    ItineraryStopBean stopBean = new ItineraryStopBean(city, arrival, departure);
                    stops.add(stopBean);
                } else {
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
