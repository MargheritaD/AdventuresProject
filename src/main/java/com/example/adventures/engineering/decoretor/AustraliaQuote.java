package com.example.adventures.engineering.decoretor;

public class AustraliaQuote extends Quote{

    private Quote baseQuote; // Preventivo di base:  il prezzo del viaggio

    public AustraliaQuote(Quote baseQuote) {
        this.baseQuote = baseQuote;
    }

    @Override
    public int getPrice() {
        // Aggiunge il prezzo specifico per l'Australia
        return baseQuote.getPrice() + 260;
    }
}
