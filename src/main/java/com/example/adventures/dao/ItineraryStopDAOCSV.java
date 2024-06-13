package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

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

    public  List<ItineraryStop> retrieveStopList(int idTrip) throws IOException{

        CSVReader reader = new CSVReader(new BufferedReader(new FileReader(fd)));
        List<ItineraryStop> itineraryStopList = null;
        try {
            List<String[]> csvBody = reader.readAll();

            for (int i = 0; i < csvBody.size(); i++) {
                String[] strArray = csvBody.get(i);
                ItineraryStop itineraryStop = new ItineraryStop(strArray[INDEX_CITY], LocalDate.parse(strArray[INDEX_ARRIVAL]), LocalDate.parse(strArray[INDEX_DEPARTURE]));
                itineraryStopList.add(itineraryStop);
            }

        }catch (CsvException e){
            Printer.printError(e.getMessage());
        }finally {
            reader.close();
        }
        System.out.println(itineraryStopList.get(0).getArrival());
        return itineraryStopList;

        /*ItineraryStop itineraryStop = null;
        List<ItineraryStop> list = null;
        CSVReader reader = new CSVReader(new BufferedReader(new FileReader(fd)));

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("stops.csv"))){

            String row;
            String[] data;

            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");

                if(data[INDEX_TRIP].equals(idTrip)){

                    itineraryStop = new ItineraryStop(data[INDEX_CITY], LocalDate.parse(data[INDEX_ARRIVAL]), LocalDate.parse(data[INDEX_DEPARTURE]));
                    list.add(itineraryStop);
                }

                throw new NotFoundException("No itinerary found for this trip:" + idTrip);
            }
        }catch(NotFoundException | IOException e){
            Printer.printError(e.getMessage());
        }
        catch()
        finally {

        }

        return list;*/

    }

}

    //public  List<ItineraryStop> retrieveStopList(int idTrip) {

//}

