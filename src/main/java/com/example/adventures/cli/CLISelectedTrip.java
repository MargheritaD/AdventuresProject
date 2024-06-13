package com.example.adventures.cli;

import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public class CLISelectedTrip {

    // vale sia per guide sia per viaggiatori?????

    /*
        controllo per vedere se il viaggio è il suo o no
        suo -> modifica
        non suo -> :
            guida -> preventivo / cambia profilo
            viaggiatore -> preventivo / manda richiesta

        Quindi mantieni la sessione per l'utente loggato
     */

    public void start(TripBean tripBean, List<ItineraryStopBean> stops) throws SQLException, NotFoundException {

    }
}
