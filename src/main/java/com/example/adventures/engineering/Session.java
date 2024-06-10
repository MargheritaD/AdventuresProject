package com.example.adventures.engineering;

import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.bean.TripBean;

public class Session {

    private static Session sessionInstance = null;
    private GuideBean guideBean;
    private TravelerBean travelerBean;
    private TripBean tripBean;

    private Session(Object ob) {

        if(ob instanceof TravelerBean){
            System.out.println("Sono nell'if di istance come viaggiatore");
            this.travelerBean = (TravelerBean) ob;
        }
        else if(ob instanceof GuideBean){
            System.out.println("Sono nell'else di istance come guida");
            guideBean = (GuideBean) ob;
        }
    }

    public static void setSessionInstance(Object ob) {
        if(sessionInstance == null)
            sessionInstance = new Session(ob);
    }

    public static void closeSession() {
        sessionInstance = null;
    }

    public static Session getCurrentSession() {
        return sessionInstance;
    }

    public GuideBean getGuideBean() {
        return guideBean;
    }

    public TravelerBean getTravelerBean() {
        return travelerBean;
    }

    public TripBean getTripBean(){ return tripBean;}

    public void setTripBean(TripBean tripBean) {
        this.tripBean = tripBean;
    }

    public  void logout(){
        sessionInstance = null;
    }

}
