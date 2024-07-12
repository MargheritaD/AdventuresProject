package com.example.adventures.engineering.decoretor;

public class ArgentinaQuote extends Quote{

    private Quote baseQuote; // Preventivo di base: prezzo del viaggio

    public ArgentinaQuote(Quote baseQuote) {
        this.baseQuote = baseQuote;
    }

    @Override
    public int getPrice() {
        // Aggiunge il prezzo specifico per il paese
        return baseQuote.getPrice() + 200;
    }

}
