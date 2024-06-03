package com.example.adventures.bean;

public class GuideBean {

    private final int idGuide;
    private final String guideName;
    private final String guideSurname;
    private String email;


    public GuideBean(int idGuide, String guideName, String guideSurname, String email) {
        this.idGuide = idGuide;
        this.guideName = guideName;
        this.guideSurname = guideSurname;
        this.email = email;
    }

    public int getId() {
        return idGuide;
    }

    public String getName() {

        return guideName;
    }

    public String getSurname() {
        return guideSurname;
    }

    public String getEmail() {
        return email;
    }

}
