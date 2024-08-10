package com.example.adventures.model;

import java.time.LocalDate;

public class PeriodInfo {
    private LocalDate outboundDate;
    private LocalDate returnDate;


    public PeriodInfo(LocalDate outboundDate, LocalDate returnDate) {
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
    }

    // Getter e Setter
    public LocalDate getOutboundDate() {

        return outboundDate;
    }

    public void setOutboundDate(LocalDate outboundDate) {
        this.outboundDate = outboundDate;
    }

    public LocalDate getReturnDate() {

        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {

        this.returnDate = returnDate;
    }
}
