package com.example.adventures.cli;

import com.example.adventures.appcontroller.NewTripController;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLINewTrip extends AbstractCLI{

    private Scanner scanner = new Scanner(System.in);

    public void createTrip() {
        try {
            System.out.println("Inserisci il nome del viaggio:");
            String tripName = scanner.nextLine();

            System.out.println("Inserisci la città di partenza:");
            String departureCity = scanner.nextLine();

            System.out.println("Inserisci la categoria (Dog trekking, Food tasting, Fun, Relax, Safari, Sport):");
            String category = scanner.nextLine();

            System.out.println("Inserisci il prezzo:");
            String price = scanner.nextLine();

            System.out.println("Inserisci la data di partenza (AAAA-MM-GG):");
            LocalDate outboundDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Inserisci la data di ritorno (AAAA-MM-GG):");
            LocalDate returnDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Inserisci il paese (Argentina, Australia, Brasil, Chile, India, Italy, Mexico, Perù, Spain):");
            String country = scanner.nextLine();

            List<ItineraryStopBean> stops = new ArrayList<>();
            boolean addingStops = true;
            while (addingStops) {
                System.out.println("Vuoi aggiungere una tappa? (si/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("si")) {
                    System.out.println("Inserisci la città della tappa:");
                    String city = scanner.nextLine();

                    System.out.println("Inserisci la data di arrivo della tappa (AAAA-MM-GG):");
                    LocalDate arrival = LocalDate.parse(scanner.nextLine());

                    System.out.println("Inserisci la data di partenza della tappa (AAAA-MM-GG):");
                    LocalDate departure = LocalDate.parse(scanner.nextLine());

                    ItineraryStopBean stopBean = new ItineraryStopBean(city, arrival, departure);
                    stops.add(stopBean);
                } else {
                    addingStops = false;
                }
            }


            String guide = ""; // Da sostituire con il valore reale

            TripBean tripBean = new TripBean(tripName, departureCity, category, outboundDate, returnDate, price, country);
            tripBean.setStops(stops);

            NewTripController newTripController = new NewTripController();
            newTripController.createTrip(tripBean);

            System.out.println("Viaggio creato con successo!");

           // CLIHomeGuide.start();

        } catch (Exception e) {
            System.out.println("Errore nella creazione del viaggio: " + e.getMessage());
            e.printStackTrace();
        }
    }
}