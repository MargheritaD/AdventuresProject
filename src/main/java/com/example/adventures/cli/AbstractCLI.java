package com.example.adventures.cli;

import com.example.adventures.appcontroller.LoginController;
import com.example.adventures.engineering.Session;
import com.example.adventures.utilities.CLIPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class AbstractCLI {

    Logger logger = Logger.getAnonymousLogger();

    protected static int getMenuChoice(int min, int max) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            CLIPrinter.printMessage("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max) {
                break;
            }
            CLIPrinter.printMessage("Invalid option\n");
        }
        return choice;
    }

    protected static void logout(){

        new LoginController().logout();
        new CLILogin().start();

    }

    protected void goHome() {
        // ruolo cosi cambi home

        Session session = Session.getCurrentSession();
        if (session != null) {
            if (session.getGuideBean() != null) { // è una guida
                new CLIHomeGuide().start();
            } else if (session.getTravelerBean() != null) {
                new CLIHomeTraveler().start();
            } else {
                // Tipo di utente non riconosciuto
            }
        } else {
            // Sessione non valida
        }
    }

}
