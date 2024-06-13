package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItineraryStopDAOCSV {

    private static File fd;

    private static final int INDEX_TRIP = 0;

    private static final int INDEX_CITY = 1;

    private static final int INDEX_ARRIVAL = 2;

    private static final int INDEX_DEPARTURE = 3;


    public ItineraryStopDAOCSV() throws IOException{



        this.fd = new File("stops.csv");

        if(!fd.exists()){
            System.out.println("NON CREO FILE");
            throw new IOException("File does not exists");
        }
    }

    public void addStop(ItineraryStop itineraryStop, int id) throws IOException {


        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

        try{

            String[] stop = new  String[4];


            stop[INDEX_TRIP] = String.valueOf(id);
            stop[INDEX_CITY] = itineraryStop.getCity();
            stop[INDEX_ARRIVAL] = String.valueOf(itineraryStop.getArrival());
            stop[INDEX_DEPARTURE] = String.valueOf(itineraryStop.getDeparture());

            csvWriter.writeNext(stop);
            csvWriter.flush();

        }finally {

            csvWriter.close();
        }
    }

    public  List<ItineraryStop> retrieveStopList(int idTrip) throws IOException {
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

        try {
            String[] stop;
            List<ItineraryStop> stopList = new ArrayList<>();

            while ((stop = csvReader.readNext()) != null) {

            }
            String city = stop[INDEX_CITY];
            LocalDate arrival = LocalDate.parse(stop[INDEX_ARRIVAL]);
            LocalDate departure = LocalDate.parse(stop[INDEX_DEPARTURE]);


            if(idTrip==(Integer.parseInt(stop[INDEX_TRIP]))){

                ItineraryStop itineraryStop = new ItineraryStop(city, arrival,departure);

                stopList.add(itineraryStop);
            }
            System.out.println(stopList.get(0).getCity());
            return stopList;

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }finally {
            csvReader.close();
        }

    }

}

    //public  List<ItineraryStop> retrieveStopList(int idTrip) {

//}
