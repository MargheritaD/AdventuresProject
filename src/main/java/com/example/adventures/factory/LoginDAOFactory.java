package com.example.adventures.factory;

import com.example.adventures.dao.LoginDAO;

public class LoginDAOFactory {
    private LoginDAOFactory(){}

    private static LoginDAOFactory instance = null;

    public static LoginDAOFactory getInstance(){
        if(instance == null){
            instance = new LoginDAOFactory();
        }
        return instance;
    }

    /*
    public LoginDAO createLoginDAO
     */
}
