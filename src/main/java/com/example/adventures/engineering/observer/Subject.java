package com.example.adventures.engineering.observer;

import com.example.adventures.bean.RequestBean;

import java.util.List;
import java.util.Vector;

public abstract class Subject {

    /*definisco la classe astratta anche se tutti i metodi che
     fornisce sono concreti per impedire l'istanziazione di essa
     */

    private final List<Observer> observersList;

    protected Subject() {
        this((Observer) null);
    }

    protected Subject(Observer observer) {
        this(new Vector<>());
        if (observer != null) {
            this.register(observer);
        }
    }

    protected Subject(List<Observer> observersList) {
        this.observersList = observersList;
    }

    public void register(Observer observer) {
        observersList.add(observer);
    }

    public void unregister(Observer observer) {
        observersList.remove(observer);
    }

    public void notifyObserversGuide(RequestBean requestBean) {
        for (Observer observer : observersList) {
            observer.update();
            //observer.updateFamilyPage(familyRequestBean, pane);
        }
    }



}
