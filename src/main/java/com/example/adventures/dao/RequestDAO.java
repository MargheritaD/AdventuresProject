package com.example.adventures.dao;

import com.example.adventures.bean.RequestBean;
import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Guide;
import com.example.adventures.model.Request;
import com.example.adventures.model.Trip;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static javax.swing.text.html.parser.DTDConstants.ID;

public class RequestDAO {

    private static final String IDTRAVELER = "traveler";
    private static final String IDTRIP = "trip";
    private static final String STATUS = "status";
    private static final String IDREQUEST = "idRequest";
    private static final String TRAVELERNAME = "Nome_viaggiatore";
    private static final String TRAVELERSURNAME = "Cognome_viaggiatore";
    private static final String TRPNAME = "tripName";



    public void registerReservation(Request request){

        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            CRUDQueries.sendRequest(connection, request.getIdTraveler(), request.getIdTrip());

        }catch (Exception e){
            e.getMessage();
        }
    }

    public static List<Request> requestsGuide(int idGuide) {

        System.out.println("sono nel requestsDAO");

        Connection connection;
        Request request;
        List<Request> requestList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveRequestsListGuide(connection, idGuide);

            if (!resultSet.first()) {
                throw new NotFoundException("No request found for this guide\n ");
            }

            resultSet.first();

            do {

                request = setTripInformationForRequestTable(resultSet);
                System.out.println("RICHIESTA: " + request);
                requestList.add(request);

            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return requestList;
    }


    private static Request setTripInformationForRequestTable(ResultSet resultSet) throws NotFoundException, SQLException {

        String tripName = resultSet.getString(1);
        String Nome_viaggiatore = resultSet.getString(2);
        String Cognome_viaggiatore = resultSet.getString(3);
        int idRequest = resultSet.getInt(4);



        return (new Request(tripName, Nome_viaggiatore, Cognome_viaggiatore, idRequest));
    }

    public static void acceptRequest(RequestBean requestBean){
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();

            CRUDQueries.acceptRequest(connection, requestBean.getIdRequest());
        }catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }


}
