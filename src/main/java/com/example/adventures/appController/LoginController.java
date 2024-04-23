package com.example.adventures.appController;

import com.example.adventures.dao.*;
import com.example.adventures.bean.*;
import com.example.adventures.model.*;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.engineering.factory.LoginDAOFactory;
//import com.example.adventures.engineering.Session;


public class LoginController {
    public void checkUser(LoginBean loginBean) throws UserNotFoundException {

        System.out.println("INIZIAMO STO METODO");

        LoginDAO loginDAO = new LoginDAOJDBC();

        try{
            UserProfile userProfile = loginDAO.checkUser(loginBean.getUsername(), loginBean.getPassword());
            loginBean.setRole(userProfile.getRole());

            System.out.println("Ruolo " + userProfile.getRole());
        }catch (UserNotFoundException e){
            System.out.println("ECCEZIONE");
        }

        //LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();

        //UserProfile userProfile = loginDAO.checkUser(loginBean.getUsername(),loginBean.getPassword());
        //loginBean.setRole(userProfile.getRole());


    }

    public void guideLogin(LoginBean loginBean){
      //  Guide guide = GuideDAO.retrieveGuideByUsername(loginBean.getUsername());
       // GuideBean guideBean = new GuideBean();
    }

    public void travelerLogin(LoginBean loginBean){
        //Traveler traveler = TravelerDAO.retrieveTravelerByUsername(loginBean.getUsername());
        //Travelerbean travelerbean = new TravelerBean();
    }
}
