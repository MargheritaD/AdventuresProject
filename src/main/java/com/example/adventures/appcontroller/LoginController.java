package com.example.adventures.appcontroller;

import com.example.adventures.dao.*;
import com.example.adventures.bean.*;
import com.example.adventures.model.*;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.engineering.Session;


public class LoginController {
    public void checkUser(LoginBean loginBean) throws UserNotFoundException {


        LoginDAO loginDAO = new LoginDAOJDBC();

        try{
            UserProfile userProfile = loginDAO.checkUser(loginBean.getUsername(), loginBean.getPassword());
            loginBean.setRole(userProfile.getRole());

        }catch (UserNotFoundException e){
            System.out.println("ECCEZIONE: Utente non esiste");
        }

    }

    public void guideLogin(LoginBean loginBean) throws NotFoundException{
        Guide guide = GuideDAO.retrieveGuideByUsername(loginBean.getUsername());
        GuideBean guideBean = new GuideBean(guide.getId(), guide.getName(), guide.getSurname(), guide.getEmail());
        Session.setSessionInstance(guideBean);
    }

    public void travelerLogin(LoginBean loginBean) throws NotFoundException {
        Traveler traveler = TravelerDAO.retrieveTravelerByUsername(loginBean.getUsername());
        TravelerBean travelerbean = new TravelerBean(traveler.getId(), traveler.getName(), traveler.getSurname(), traveler.getEmail());
        Session.setSessionInstance(travelerbean);
    }
}
