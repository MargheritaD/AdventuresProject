package com.example.adventures.cli;

import com.example.adventures.utilities.CLIPrinter;

public class CLIHomeGuide extends AbstractCLIController{
    public void start(){

        boolean choose = true;

        while(choose){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> {
                        choose = false;
                        chooseCategory();
                    }
                    case 2 -> {
                        choose = false;
                    }
                    case 3 -> {
                        choose = false;
                    }
                    case 4 -> {
                        choose = false;
                    }
                }
            }
        }

    }

    private int showMenu(){

        CLIPrinter.printMessage("Menu: \n");
        CLIPrinter.printMessage("1. Choose country: \n");
        CLIPrinter.printMessage("2. your requests: \n");
        CLIPrinter.printMessage("3. Logout: \n");
        CLIPrinter.printMessage("4. Quit: \n");

        return  getMenuChoice(1,4);
    }
}
