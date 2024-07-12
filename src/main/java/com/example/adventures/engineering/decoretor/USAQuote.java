package com.example.adventures.engineering.decoretor;

public class USAQuote extends Quote{
    private Quote baseQuote; // Preventivo di base:  il prezzo del viaggio

    public USAQuote(Quote baseQuote) {
        this.baseQuote = baseQuote;
    }

    @Override
    public int getPrice() {
        // Aggiunge il prezzo specifico per il paese
        return baseQuote.getPrice() + 300;
    }
}
