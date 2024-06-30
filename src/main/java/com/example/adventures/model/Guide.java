package com.example.adventures.model;

public class Guide extends GenericUser{

    private int idGuida;
    private static String nomeGuida;
    private String cognomeGuida;
    private String Email;

    public Guide() {}
    public Guide(int Id_guide, String nomeGuida, String cognomeGuida, String email){
        super(Id_guide, null, email);
        this.nomeGuida = nomeGuida;
        this.cognomeGuida = cognomeGuida;
    }

    public static String getName() {
        System.out.println(nomeGuida);
        return nomeGuida;
    }

    public String getSurname() {
        return cognomeGuida;
    }

    public String getEmail(){
        return Email;}
}
