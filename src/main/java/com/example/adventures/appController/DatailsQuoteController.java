package com.example.adventures.appController;

import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.QuoteBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.ItineraryStopDAO;
import com.example.adventures.dao.ParticipantDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.engineering.Session;
import com.example.adventures.engineering.decoretor.CancellationQuote;
import com.example.adventures.engineering.decoretor.HealthQuote;
import com.example.adventures.engineering.decoretor.LuggageQuote;
import com.example.adventures.engineering.decoretor.Quote;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Guide;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class DatailsQuoteController {

    public List<ItineraryStopBean> tableItinerary(int tripId){

        List<ItineraryStop> stops = ItineraryStopDAO.retrieveStopList(tripId);

        List<ItineraryStopBean> itineraryStopBeans = new ArrayList<>();
        for (ItineraryStop itineraryStop : stops) {
            ItineraryStopBean itineraryStopBean = new ItineraryStopBean(itineraryStop.getCity(), itineraryStop.getArrival(), itineraryStop.getDeparture());
            itineraryStopBeans.add(itineraryStopBean);
        }

        return itineraryStopBeans;
    }

    public List<TripBean> tableDetails(int tripId) throws NotFoundException { // rimetti come parametro int tripId

        System.out.println("\nCodice viaggio in datailsquotecontroller metodo table details: " + tripId);
        //System.out.println("\nCodice NOME viaggio in datailsquotecontroller metodo table details: " + nome);

        List<Trip> trips = TripDAO.retrieveTrip(tripId); // rimetti idTrip

        List<TripBean> tripBeans = new ArrayList<>();
        for (Trip trip : trips) {
            TripBean tripBean = new TripBean(trip.getTripName(), trip.getDepartureCity(), trip.getOutboundDate(), trip.getReturnDate(), trip.getGuide(), trip.getPrice());
            tripBeans.add(tripBean);
        }

        return tripBeans;
    }

    public TripBean tableTrip(int tripId) throws NotFoundException {

        Trip trip = TripDAO.retrieveTripById(tripId);

        TripBean tripBean = new TripBean(trip.getTripName(), trip.getDepartureCity(), trip.getOutboundDate(), trip.getReturnDate(), trip.getPrice(), trip.getGuide());

        return tripBean;

    }

    public void bookTrip(int tripId, int personId){

        System.out.println("ENtro nel controller logico");

        //Guide guide = new Guide(guideBean.getId);

        //Trip trip = new Trip(tripBean.getTripName(), tripBean.getDepartureCity(), tripBean.getCategory(), tripBean.getOutboundDate(), tripBean.getReturnDate(), tripBean.getPrice(), tripBean.getGuide(), tripBean.getCountry());

        //Participants participants = new Participants(tripId, personId);
        ParticipantDAO.insertParticipant(tripId, personId);
        System.out.println("Ho chiamato il DAO");

    }

    public float calculateQuote(QuoteBean quoteBean, int tripId) throws NotFoundException {

        Quote quote = null;

        Trip trip = TripDAO.retrieveTripById(tripId);
        float totalPrice = Float.parseFloat(trip.getPrice());

        List<Quote> insurances = quoteBean.getSelectedInsurances();
        for (Quote insurance : insurances) {
            totalPrice += insurance.getPrice();
        }

        return totalPrice;

    }



    public void tripDetails(int tripId) throws NotFoundException {

        //Trip trip = TripDAO.retrieveTripById(tripId);
        //Session.getCurrentSession().setTripBean(trip);
    }
}
