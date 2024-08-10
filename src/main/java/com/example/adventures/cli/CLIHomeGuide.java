package com.example.adventures.cli;

import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.*;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;




public class CLIHomeGuide extends AbstractCLI {
    public void start() throws NotFoundException {
        boolean choose = true;
        while (choose) {
            try {
                int choice = showMainMenu();

                switch (choice) {
                    case 1 -> { // Choose country
                        choose = false;
                        handleCountrySelection(new CountryCategoryBean());
                    }
                    case 2 -> // Trip requests
                            new CLIGuideRequests().start();
                    case 3 -> { // New trip
                        choose = false;
                        new CLINewTrip().createTrip();
                        start();
                    }
                    case 4 -> { // Quit
                        choose = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice");
                }
            } catch (Exception e) {
                throw new NotFoundException("Exception");
            }
        }
    }

    private void handleCountrySelection(CountryCategoryBean countryCategoryBean) throws NotFoundException {
        int countryChoice = showCountryMenu();

        boolean validChoice = true;
        switch (countryChoice) {
            case 1 -> countryCategoryBean.setCountry("Italy");
            case 2 -> countryCategoryBean.setCountry("Argentina");
            case 3 -> countryCategoryBean.setCountry("USA");
            case 4 -> countryCategoryBean.setCountry("Mexico");
            case 5 -> goHome();
            case 6 -> System.exit(0);
            default -> {
                CLIPrinter.printMessage("Invalid country choice");
                validChoice = false;
            }
        }

        if (validChoice) {
            int categoryChoice = showCategoryMenu();
            setCategory(categoryChoice, countryCategoryBean);
        }
    }

    private int showMainMenu() {
        CLIPrinter.printMessage("\n\n    ------------------ \n");
        CLIPrinter.printMessage("   |Menu:             |\n");
        CLIPrinter.printMessage("   |------------------|\n");
        CLIPrinter.printMessage("   |1) Choose country |\n");
        CLIPrinter.printMessage("   |2) Trip requests  |\n");
        CLIPrinter.printMessage("   |3) New trip       |\n");
        CLIPrinter.printMessage("   |4) Quit           |\n");
        CLIPrinter.printMessage("    ------------------\n\n");
        return getMenuChoice(1, 4);
    }

    private int showCountryMenu() {
        CLIPrinter.printMessage("\n\n    -----------------                   -----------------\n");
        CLIPrinter.printMessage("   |Country list:     |                |Main menu:       |\n");
        CLIPrinter.printMessage("   |----------------- |                |-----------------|\n");
        CLIPrinter.printMessage("   |1. Italy          |                |5) Home          |\n");
        CLIPrinter.printMessage("   |2. Argentina      |                |6) Quit          |\n");
        CLIPrinter.printMessage("   |3. USA            |                |                 |\n");
        CLIPrinter.printMessage("   |4. Mexico         |                |                 |\n");
        CLIPrinter.printMessage("    -----------------                   -----------------\n\n");
        return getMenuChoice(1, 6);
    }

    private int showCategoryMenu() {
        CLIPrinter.printMessage("\n\n    -------------------\n");
        CLIPrinter.printMessage("   |Category list:     |\n");
        CLIPrinter.printMessage("   |-------------------|\n");
        CLIPrinter.printMessage("   |1. Relax           |\n");
        CLIPrinter.printMessage("   |2. Sport           |\n");
        CLIPrinter.printMessage("   |3. Dog trekking    |\n");
        CLIPrinter.printMessage("   |4. Safari          |\n");
        CLIPrinter.printMessage("   |5. Food tasting    |\n");
        CLIPrinter.printMessage("   |6. Fun             |\n");
        CLIPrinter.printMessage("    -------------------\n\n");
        return getMenuChoice(1, 6);
    }

    public void setCategory(int categoryChoice, CountryCategoryBean countryCategoryBean) throws NotFoundException {
        String categoryName = switch (categoryChoice) {
            case 1 -> "Relax";
            case 2 -> "Sport";
            case 3 -> "Dog trekking";
            case 4 -> "Safari";
            case 5 -> "Food tasting";
            case 6 -> "Fun";
            default -> null;
        };

        if (categoryName != null) {
            countryCategoryBean.setCategory(categoryName);
            ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
            List<TripBean> listOfTripBean = viewTripDetailsController.selectCountryAndCategory(countryCategoryBean);
            new CLIListTripCategoryCountry().start(listOfTripBean);
        } else {
            CLIPrinter.printMessage("Invalid category choice");
        }
    }

/*
    public void start() throws NotFoundException{
        boolean choose = true;
        CountryCategoryBean countryCategoryBean = new CountryCategoryBean();
        CLIGuideRequests guideRequests = new CLIGuideRequests();

        while (choose) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> { // Choose country:
                        choose = false;
                        handleCountrySelection(countryCategoryBean);
                    }
                    case 2 ->  // request
                        guideRequests.start();

                    case 3 -> { // new trip

                        choose = false;
                        new CLINewTrip().createTrip();
                        start();
                    }
                    case 4 -> { // logout
                        choose = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice");
                }
            } catch (Exception e) {
                throw new NotFoundException("Exception");
            }
        }
    }

    private void handleCountrySelection(CountryCategoryBean countryCategoryBean) throws NotFoundException {
        int country = chooseCountry();
        boolean choice = true;
        while (choice) {
            switch (country) {
                case 1 -> {
                    choice = false;
                    countryCategoryBean.setCountry("Italy");
                }
                case 2 -> {
                    choice= false;
                    countryCategoryBean.setCountry("Argentina");
                }
                case 3 -> {
                    choice = false;
                    countryCategoryBean.setCountry("USA");
                }
                case 4 -> {
                    choice = false;
                    countryCategoryBean.setCountry("Mexico");
                }
                case 5 -> {
                    choice = false;
                    goHome();
                }
                case 6 -> {
                    choice = false;
                    System.exit(0);
                }
                default -> {
                    CLIPrinter.printMessage("Invalid choice");
                    return;
                }
            }
            int category = chooseCategory();
            setCategory(category, countryCategoryBean);
        }
    }

    private int showMenu() {
        CLIPrinter.printMessage("\n\n    ------------------ \n");
        CLIPrinter.printMessage("   |Menu:             |\n");
        CLIPrinter.printMessage("   |------------------ |\n");
        CLIPrinter.printMessage("   |1) Choose country  |\n");
        CLIPrinter.printMessage("   |2) Trip requests   |\n");
        CLIPrinter.printMessage("   |3) New trip        |\n");
        CLIPrinter.printMessage("   |4) Quit            |\n");
        CLIPrinter.printMessage("    ------------------\n\n");

        return getMenuChoice(1, 4);
    }

    private int chooseCountry() {
        CLIPrinter.printMessage("\n\n    -----------------                   -----------------\n");
        CLIPrinter.printMessage("   |Country list:     |                |Main menu:       |\n");
        CLIPrinter.printMessage("   |----------------- |                |-----------------| \n");
        CLIPrinter.printMessage("   |1. Italy          |                |5) Home          |\n");
        CLIPrinter.printMessage("   |2. Argentina      |                |6) Quit          |  \n");
        CLIPrinter.printMessage("   |3. USA            |                 ----------------- \n");
        CLIPrinter.printMessage("   |4. Mexico         | \n");
        CLIPrinter.printMessage("    -----------------\n\n");

        return getMenuChoice(1, 6);
    }

    private int chooseCategory() {
            CLIPrinter.printMessage("\n\n    -------------------\n");
            CLIPrinter.printMessage("   |Category list:      |\n");
            CLIPrinter.printMessage("   |------------------- |\n");
            CLIPrinter.printMessage("   |1. Relax            |\n");
            CLIPrinter.printMessage("   |2. Sport            |\n");
            CLIPrinter.printMessage("   |3. Dog trekking     | \n");
            CLIPrinter.printMessage("   |4. Safari           | \n");
            CLIPrinter.printMessage("   |5. Food tasting     | \n");
            CLIPrinter.printMessage("   |6. Fun              | \n");
            CLIPrinter.printMessage("    -------------------\n\n");

        return getMenuChoice(1, 6);
    }

    public void setCategory(int category, CountryCategoryBean countryCategoryBean) throws NotFoundException {

        String categoryName = getCategoryName(category);
        if (categoryName != null) {

            countryCategoryBean.setCategory(categoryName);
            ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
            List<TripBean> listOfTripBean = viewTripDetailsController.selectCountryAndCategory(countryCategoryBean);

            CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
            cliListTripCategoryCountry.start(listOfTripBean);
        } else {
            CLIPrinter.printMessage("Invalid category choice");
        }
    }

    private String getCategoryName(int category) {
        return switch (category) {
            case 1 -> "Relax";
            case 2 -> "Sport";
            case 3 -> "Dog trekking";
            case 4 -> "Safari";
            case 5 -> "Food tasting";
            case 6 -> "Fun";
            default -> null;
        };
    }

 */

}
