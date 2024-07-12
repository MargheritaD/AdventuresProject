package com.example.adventures.engineering.decoretor.decorations;

import com.example.adventures.engineering.decoretor.Quote;

public class HealthcareDecorator extends Decorator{

    public HealthcareDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+100;
    }
}
