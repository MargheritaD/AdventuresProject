package com.example.adventures.engineering.decoretor.decorations;

import com.example.adventures.engineering.decoretor.Quote;

public abstract class Decorator extends Quote {

    protected Quote quote;
    protected Decorator(Quote quote){
        this.quote = quote;
    }
    @Override
    public int getPrice() {
        return quote.getPrice();
    }
}


