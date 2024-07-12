package com.example.adventures.engineering.decoretor;

public class IndiaQuote extends Quote{

    private Quote baseQuote; // Preventivo di base:  il prezzo del viaggio

    public IndiaQuote(Quote baseQuote) {
        this.baseQuote = baseQuote;
    }

    @Override
    public int getPrice() {
        // Aggiunge il prezzo specifico per il paese
        return baseQuote.getPrice() + 100;
    }
}
