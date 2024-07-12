package com.example.adventures.engineering.decoretor.decorations;

import com.example.adventures.engineering.decoretor.Quote;

public class LuggageDecorator extends Decorator{
    public LuggageDecorator(Quote quote) {
        super(quote);
    }

    @Override
    public int getPrice(){
        return super.getPrice()+120;
    }
}
