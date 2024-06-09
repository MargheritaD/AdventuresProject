package com.example.adventures.cli;

import com.example.adventures.AppController.LoginController;
import com.example.adventures.utilities.CLIPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class AbstractCLIController {

    Logger logger = Logger.getAnonymousLogger();

    protected int getMenuChoice(int min, int max) {
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


    /*
    protected void logout(){
        new LoginController().logout();
        new CLILoginGraphicController().start();
    }

    protected void goHome() {
        new CLIHomeGraphicController().start();
    }

    protected void viewMessages(){
        if (SessionManager.getInstance().getCurrentUser().getUserType() == TOURIST) {
            List<RequestsInfoBean> tourInfo = new JoinTourController().showMessages();
            new CLIMessagesGraphicController().start(tourInfo);
        } else {
            CLIPrinter.printNumbers(1);
            CLIPrinter.printMessage("Show messages\n");
            CLIPrinter.printNumbers(2);
            CLIPrinter.printMessage("Show requests\n");
            int choice = getMenuChoice(1, 2);
            if (choice == 1) {
                List<RequestsInfoBean> tourInfo = new JoinTourController().showMessages();
                new CLIMessagesGraphicController().start(tourInfo);
            } else if (choice == 2) {
                List<ReservationInfoBean> tourInfo = new JoinTourController().showRequests();
                new CLIRequestsGraphicController().start(tourInfo);
            }
        }

    }
    */

}
