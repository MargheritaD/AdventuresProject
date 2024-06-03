package com.example.adventures.AppController;

import com.example.adventures.bean.QuoteBean;
import com.example.adventures.engineering.decoretor.Quote;

public class QuoteController {

    public float calculateQuote(QuoteBean quoteBean) {
        float totalPrice = quoteBean.getTripPrice();

        for (Quote insurance : quoteBean.getSelectedInsurances()) {
            totalPrice += insurance.getPrice();
        }

        return totalPrice;
    }

}
