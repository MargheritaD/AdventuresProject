package com.example.adventures.model;

public class Guide extends GenericUser{

    private int idGuide;
    private String nomeGuida;
    private String cognomeGuida;
    private String email;

    public Guide() {}
    public Guide(int idGuide, String nomeGuida, String cognomeGuida, String email){
        super(idGuide, null, email);
        this.nomeGuida = nomeGuida;
        this.cognomeGuida = cognomeGuida;
    }

    public String getName() {
        return nomeGuida;
    }

    public String getSurname() {
        return cognomeGuida;
    }
    public int getIdGuide(){return idGuide;}

    @Override
    public String getEmail(){
        return email;}
}
