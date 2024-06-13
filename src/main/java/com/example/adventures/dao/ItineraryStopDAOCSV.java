package com.example.adventures.dao;

import com.example.adventures.model.ItineraryStop;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class ItineraryStopDAOCSV {

    private static File fd;

    private static final int INDEX_TRIP = 1;

    private static final int INDEX_CITY = 2;

    private static final int INDEX_ARRIVAL = 3;

    private static final int INDEX_DEPARTURE = 4;


    public ItineraryStopDAOCSV() throws IOException{



        this.fd = new File("stops.csv");

        if(!fd.exists()){
            System.out.println("NON CREO FILE");
            throw new IOException("File does not exists");
        }
    }

    public static void addStop(ItineraryStop itineraryStop, int id) throws IOException {


        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

        try{

            String[] stop = new  String[5];


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


}
