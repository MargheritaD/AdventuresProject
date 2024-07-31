package com.example.adventures.appcontroller;

import com.example.adventures.dao.*;
import com.example.adventures.bean.*;
import com.example.adventures.model.*;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.engineering.Session;

import java.util.logging.Logger;


public class LoginController {

    public void checkUser(LoginBean loginBean) throws UserNotFoundException {
        LoginDAO loginDAO = new LoginDAOJDBC();
        try{
            UserProfile userProfile = loginDAO.checkUser(loginBean.getUsername(), loginBean.getPassword());
            loginBean.setRole(userProfile.getRole());
        }catch (UserNotFoundException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.severe("ECCEZIONE: Utente non esiste");
            throw e; // Rilancia l'eccezione se necessario
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
    public void logout(){
        Session.logout();
    }

}
