package com.example.adventures.bean;

import java.time.LocalDate;

public class PeriodInfoBean {
    private LocalDate outboundDate;
    private LocalDate returnDate;

    public PeriodInfoBean(LocalDate outboundDate, LocalDate returnDate) {
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
    }

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
