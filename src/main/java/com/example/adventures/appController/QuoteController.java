package com.example.adventures.appController;

import com.example.adventures.bean.QuoteBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.engineering.decoretor.CancellationQuote;
import com.example.adventures.engineering.decoretor.HealthQuote;
import com.example.adventures.engineering.decoretor.LuggageQuote;
import com.example.adventures.engineering.decoretor.Quote;
import com.example.adventures.exception.NotFoundException;

public class QuoteController {

    public float calculateQuote(QuoteBean quoteBean) {
        float totalPrice = quoteBean.getTripPrice();

        for (Quote insurance : quoteBean.getSelectedInsurances()) {
            totalPrice += insurance.getPrice();
        }

        return totalPrice;
    }

}
