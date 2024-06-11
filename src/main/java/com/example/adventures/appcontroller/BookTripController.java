package com.example.adventures.appcontroller;

import com.example.adventures.bean.*;
import com.example.adventures.dao.ItineraryStopDAO;
import com.example.adventures.dao.RequestDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.engineering.decoretor.Quote;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Request;
import com.example.adventures.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class BookTripController {

    public float calculateQuote(QuoteBean quoteBean) {
        float totalPrice = quoteBean.getTripPrice();

        for (Quote insurance : quoteBean.getSelectedInsurances()) {
            totalPrice += insurance.getPrice();
        }

        return totalPrice;
    }

    public int numberOfTrps(String country){

        return TripDAO.getNumberTripsByCountry(country);
    }


    public List<TripBean> selectCountryAndCategory(CountryCategoryBean countryCategoryBean){

        List<Trip> trips = TripDAO.retrieveTripListByCategoryAndCountry(countryCategoryBean.getCategory(), countryCategoryBean.getCountry());

        List<TripBean> tripBeans = new ArrayList<>();
        for (Trip trip : trips) {
            TripBean tripBean = new TripBean(trip.getIdTrip(), trip.getTripName(), trip.getOutboundDate(), trip.getReturnDate(), trip.getDepartureCity(), trip.getGuide());
            tripBeans.add(tripBean);
        }

        return tripBeans;
    }

    public List<ItineraryStopBean> tableItinerary(int tripId){

        List<ItineraryStop> stops = ItineraryStopDAO.retrieveStopList(tripId);

        List<ItineraryStopBean> itineraryStopBeans = new ArrayList<>();
        for (ItineraryStop itineraryStop : stops) {
            ItineraryStopBean itineraryStopBean = new ItineraryStopBean(itineraryStop.getCity(), itineraryStop.getArrival(), itineraryStop.getDeparture());
            itineraryStopBeans.add(itineraryStopBean);
        }

        return itineraryStopBeans;
    }

    public TripBean tableTrip(int tripId) throws NotFoundException {

        Trip trip = TripDAO.retrieveTripById(tripId);

        TripBean tripBean = new TripBean(trip.getTripName(), trip.getDepartureCity(), trip.getOutboundDate(), trip.getReturnDate(), trip.getPrice(), trip.getGuide());

        return tripBean;

    }

    public void sendRequest(RequestBean requestBean){

        Request request = new Request(requestBean.getIdTrip(), requestBean.getIdTraveler());
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.registerReservation(request);

    }

    public List<RequestBean> tableRequestsGuide(GuideBean guideBean){

        System.out.println("Sono nel booktripcontroler\n");
        System.out.println("Sto chiamando il requestsDAO");

        List<Request> requests = RequestDAO.requestsGuide(guideBean.getId());

        List<RequestBean> requestBeans = new ArrayList<>();
        for (Request request : requests) {
            RequestBean requestBean = new RequestBean(request.getNomeViaggio(), request.getNomeViaggiatore(), request.getCognomeViaggiatore(), request.getRequest_id());
            requestBeans.add(requestBean);
            System.out.println(request.getNomeViaggio());
            System.out.println(request.getNomeViaggiatore());
            System.out.println(request.getCognomeViaggiatore());
        }

        return requestBeans;
    }

    public void acceptRequest(RequestBean requestBean){
        RequestDAO.acceptRequest(requestBean);
    }

    public void declineRequest(RequestBean requestBean){

    }

}
