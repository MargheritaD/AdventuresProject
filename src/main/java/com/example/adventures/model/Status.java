package com.example.adventures.model;

public enum Status {
    OPEN(0),
    ACCEPTED(1),
    DECLINED(2);
    private final int id;
    private Status(int id){this.id = id;}

    public int getId() {
        return id;
    }
}
