package com.example.adventures.engineering.decoretor;

public class TripPriceQuote extends Quote{
    private int basePrice;

    public TripPriceQuote(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public int getPrice() {
        return basePrice;
    }
}
