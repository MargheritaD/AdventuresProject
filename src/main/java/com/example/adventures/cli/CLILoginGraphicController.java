package com.example.adventures.cli;

import com.example.adventures.AppController.LoginController;
import com.example.adventures.exception.EmailFormatException;
import com.example.adventures.utilities.CLIPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;

public class CLILoginGraphicController extends AbstractCLIController{

    public void start(){
        boolean a = true;
        while (a){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> {
                        a = false;
                        System.out.println("Login");

                        login();
                    }
                    case 2 -> {
                        a = false;

                        System.out.println("SinUp");
                        //new CLISignUpGraphicController().start();
                    }

                    case 3 -> {
                        a = false;
                        System.exit(0);
                    }
                    default -> throw new EmailFormatException("invalid choice");
                }
            } catch (EmailFormatException e){
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    public int showMenu() {
        CLIPrinter.printMessage("What do you want to do?\n");
        CLIPrinter.printMessage("1) Login\n");
        CLIPrinter.printMessage("2) Sign Up\n");
        CLIPrinter.printMessage("3) Quit\n");

        return getMenuChoice(1, 3);
    }



    private void login() {
        LoginController loginController = new LoginController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            CLIPrinter.printMessage("username: ");
            String username = reader.readLine();
            CLIPrinter.printMessage("password: ");
            String password = reader.readLine();
            //CredentialsBean bean = new CredentialsBean(username, password);
            //loginController.login(bean);

            //new CLIHomeGraphicController().start();
        } catch ( IOException e) {
            //logger.log(Level.INFO, e.getMessage());
            start();
        }
    }



}
