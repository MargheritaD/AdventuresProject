package com.example.adventures.appcontroller;

import com.example.adventures.bean.QuoteBean;
import com.example.adventures.engineering.decoretor.Quote;

public class RequestQuoteController {

    public float calculateQuote(QuoteBean quoteBean) {
        float totalPrice = quoteBean.getTripPrice();

        for (Quote insurance : quoteBean.getSelectedInsurances()) {
            totalPrice += insurance.getPrice();
        }

        return totalPrice;
    }
}
