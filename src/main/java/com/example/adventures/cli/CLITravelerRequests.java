package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.RequestBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;

public class CLITravelerRequests extends AbstractCLI{

    public void start(){

        Session session = Session.getCurrentSession();
        if (session == null || session.getTravelerBean() == null) {
            CLIPrinter.printMessage("Error: Traveler session not found.");
            return;
        }

        TravelerBean travelerBean = session.getTravelerBean();
        List<RequestBean> requestBeanList = getRequestsForTraveler(travelerBean);
        showRequests(requestBeanList);
    }

    private List<RequestBean> getRequestsForTraveler(TravelerBean travelerBean) {
        try {
            BookTripController bookTripController = new BookTripController();
            return bookTripController.tableRequestsTraveler(travelerBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showRequests(List<RequestBean> requestBeans) {

        CLIPrinter.printMessage("\n****************************  Your Request ****************************\n");

        for (RequestBean requestBean : requestBeans) {
            CLIPrinter.printMessage(" Trip: " + requestBean.getTripName() + "\n");
            CLIPrinter.printMessage(" Status: " + requestBean.getStatusString() + " \n");
            CLIPrinter.printMessage("---------------------------------------------\n");
        }
    }
}
