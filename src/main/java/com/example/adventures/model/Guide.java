package com.example.adventures.model;

public class Guide extends GenericUser{

    private int Id_guide;
    private static String Nome_guida;
    private String Cognome_guida;
    private String Email;

    public Guide() {}
    public Guide(int Id_guide, String nome_guida, String cognome_guida, String email){
        super(Id_guide, null, email);
        this.Nome_guida = nome_guida;
        this.Cognome_guida = cognome_guida;
    }

    public static String getName() {
        System.out.println(Nome_guida);
        return Nome_guida;
    }

    public String getSurname() {
        return Cognome_guida;
    }

    public String getEmail(){
        System.out.println("\nEMAIL BEN: " + Email);
        return Email;}
}
