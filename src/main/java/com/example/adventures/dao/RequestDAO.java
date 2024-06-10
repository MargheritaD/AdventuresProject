package com.example.adventures.dao;

import com.example.adventures.connection.ConnectionDB;
import com.example.adventures.dao.queries.CRUDQueries;
import com.example.adventures.dao.queries.SimpleQueries;
import com.example.adventures.model.Guide;
import com.example.adventures.model.Request;

import java.sql.Connection;
import java.sql.ResultSet;

public class RequestDAO {

    private static final String IDTRAVELER = "traveler";
    private static final String IDTRIP = "trip";
    private static final String STATUS = "status";
    private static final String IDREQUEST = "idRequest";


    public void registerReservation(Request request){

        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            CRUDQueries.sendRequest(connection, request.getIdTraveler(), request.getIdTrip());

        }catch (Exception e){
            e.getMessage();
        }
    }

    //public void


}
