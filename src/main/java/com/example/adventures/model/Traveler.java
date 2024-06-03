package com.example.adventures.model;

public class Traveler extends GenericUser{
    private int Id_viaggiatore;
    private static String Nome_traveler;
    private String Cognome_traveler;
    private String Email;

    public Traveler() {}
    public Traveler(int Id_traveler, String Nome_traveler, String Cognome_traveler, String Email){
        //super(Id_traveler, null, email);
        this.Id_viaggiatore = Id_traveler;
        this.Nome_traveler = Nome_traveler;
        this.Cognome_traveler = Cognome_traveler;
        this.Email = Email;
    }

    public static String getName() {
        System.out.println(Nome_traveler);
        return Nome_traveler;
    }

    public int getId(){
        return Id_viaggiatore;
    }

    public String getSurname() {
        return Cognome_traveler;
    }

    public String getEmail(){
        System.out.println("\nEMAIL BEN: " + Email);
        return Email;}

}
