package com.example.adventures.cli;

import com.example.adventures.appcontroller.LoginController;
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

    protected void logout(){

        new LoginController().logout();
        new CLILoginGraphicController().start();

    }

    protected void goHome() {
        new CLIHomeGuide().start();
    }

}
