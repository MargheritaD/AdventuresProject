package com.example.adventures.engineering.factory;

import com.example.adventures.dao.LoginDAO;
import com.example.adventures.dao.LoginDAOCVS;
import com.example.adventures.dao.LoginDAOJDBC;

import java.time.LocalTime;

public class LoginDAOFactory {
    private LoginDAOFactory(){}

    private static LoginDAOFactory instance = null;

    public static LoginDAOFactory getInstance(){
        if(instance == null){
            instance = new LoginDAOFactory();
        }
        return instance;
    }

    public LoginDAO createLoginDAO(){
        if(LocalTime.now().getMinute()%2 == 0){
            return new LoginDAOJDBC();
        } else {
            return new LoginDAOCVS();
        }
    }
}
