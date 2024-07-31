package com.example.adventures.appcontroller;

import com.example.adventures.bean.*;
import com.example.adventures.dao.RequestDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.model.Request;


import java.util.ArrayList;
import java.util.List;

public class BookTripController {
    public void sendRequest(RequestBean requestBean){

        System.out.println("Sono in sendRequest");
        Request request = new Request(requestBean.getIdTrip(), requestBean.getIdTraveler());
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.registerReservation(request);

    }

    public List<RequestBean> listRequestGuide(GuideBean guideBean){

        List<Request> requests = RequestDAO.listRequestToGuide(guideBean.getId());

        List<RequestBean> requestBeans = new ArrayList<>();
        for (Request request : requests) {
            RequestBean requestBean = new RequestBean(request.getNomeViaggio(), request.getNomeViaggiatore(), request.getCognomeViaggiatore(), request.getRequestId());
            requestBeans.add(requestBean);
        }

        return requestBeans;
    }

    public List<RequestBean> listRequestTraveler(TravelerBean travelerBean){
        List<Request> requests = RequestDAO.listRequestForTraveler(travelerBean.getId());

        List<RequestBean> requestBeans = new ArrayList<>();
        for (Request request : requests) {
            RequestBean requestBean = new RequestBean(request.getNomeViaggio(), request.getRequestId(), request.getStatus());
            requestBeans.add(requestBean);
        }

        return requestBeans;
    }

    public void acceptRequest(RequestBean requestBean){
        RequestDAO.acceptRequest(requestBean);
    }

    public void declineRequest(RequestBean requestBean){
        RequestDAO.declineRequest(requestBean);
    }

    public void cancelTrip(int idTrip) {

        TripDAO.cancelTrip(idTrip);
    }
}
