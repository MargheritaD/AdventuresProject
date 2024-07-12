package com.example.adventures.engineering.decoretor.decorations;

import com.example.adventures.engineering.decoretor.Quote;

public class CancellationDecorator extends Decorator{

    public CancellationDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+90;
    }
}
