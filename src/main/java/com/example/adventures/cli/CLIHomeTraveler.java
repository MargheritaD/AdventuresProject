package com.example.adventures.cli;

import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;

public class CLIHomeTraveler extends AbstractCLI {

    public void start() throws NotFoundException {
        boolean continueChoosing = true;
        CountryCategoryBean travelerCountryCategoryBean = new CountryCategoryBean();
        CLITravelerRequests travelerRequests = new CLITravelerRequests();

        while (continueChoosing) {
            try {
                int userChoice = displayTravelerMenu();

                switch (userChoice) {
                    case 1 -> { // Choose country:
                        continueChoosing = false;
                        processCountrySelection(travelerCountryCategoryBean);
                    }
                    case 2 -> // Your requests
                            travelerRequests.start();
                    case 3 -> { // Logout
                        continueChoosing = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice");
                }
            } catch (Exception e) {
                throw new NotFoundException("Exception");
            }
        }
    }

    private void processCountrySelection(CountryCategoryBean travelerCountryCategoryBean) throws NotFoundException {
        int selectedCountry = displayCountryMenu();
        boolean validChoice = true;

        switch (selectedCountry) {
            case 1 -> travelerCountryCategoryBean.setCountry("Italy");
            case 2 -> travelerCountryCategoryBean.setCountry("Argentina");
            case 3 -> travelerCountryCategoryBean.setCountry("USA");
            case 4 -> travelerCountryCategoryBean.setCountry("Mexico");
            case 5 -> goHome();
            case 6 -> System.exit(0);
            default -> {
                CLIPrinter.printMessage("Invalid country choice");
                validChoice = false;
            }
        }

        if (validChoice) {
            int selectedCategory = displayCategoryMenu();
            assignCategoryToCountry(selectedCategory, travelerCountryCategoryBean);
        }
    }

    private int displayTravelerMenu() {
        CLIPrinter.printMessage("\n\n    ----------------- \n");
        CLIPrinter.printMessage("   |Menu:             |\n");
        CLIPrinter.printMessage("   |----------------- |\n");
        CLIPrinter.printMessage("   |1) Choose country |\n");
        CLIPrinter.printMessage("   |2) Your requests  |\n");
        CLIPrinter.printMessage("   |3) Logout         |\n");
        CLIPrinter.printMessage("   |4) Quit           |\n");
        CLIPrinter.printMessage("    -----------------\n\n");
        return getMenuChoice(1, 4);
    }

    private int displayCountryMenu() {
        CLIPrinter.printMessage("\n\n    ----------------                   -----------------\n");
        CLIPrinter.printMessage("   |Country list:    |                |Main menu:       |\n");
        CLIPrinter.printMessage("   |-----------------|                |-----------------| \n");
        CLIPrinter.printMessage("   |1. Italy         |                |5) Home          |\n");
        CLIPrinter.printMessage("   |2. Argentina     |                |6) Quit          |  \n");
        CLIPrinter.printMessage("   |3. USA           |                 ----------------- \n");
        CLIPrinter.printMessage("   |4. Mexico        | \n");
        CLIPrinter.printMessage("    ----------------\n\n");
        return getMenuChoice(1, 6);
    }

    private int displayCategoryMenu() {
        CLIPrinter.printMessage("\n\n    -----------------\n");
        CLIPrinter.printMessage("   |Category list:    |\n");
        CLIPrinter.printMessage("   |----------------- |\n");
        CLIPrinter.printMessage("   |1. Relax          |\n");
        CLIPrinter.printMessage("   |2. Sport          |\n");
        CLIPrinter.printMessage("   |3. Dog trekking   | \n");
        CLIPrinter.printMessage("   |4. Safari         | \n");
        CLIPrinter.printMessage("   |5. Food tasting   | \n");
        CLIPrinter.printMessage("   |6. Fun            | \n");
        CLIPrinter.printMessage("    -----------------\n\n");
        return getMenuChoice(1, 6);
    }

    public void assignCategoryToCountry(int selectedCategory, CountryCategoryBean travelerCountryCategoryBean) throws NotFoundException {
        String categoryName = resolveCategoryName(selectedCategory);

        if (categoryName != null) {
            travelerCountryCategoryBean.setCategory(categoryName);
            ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
            List<TripBean> listOfTripBean = viewTripDetailsController.selectCountryAndCategory(travelerCountryCategoryBean);
            CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
            cliListTripCategoryCountry.start(listOfTripBean);
        } else {
            CLIPrinter.printMessage("Invalid category choice");
        }
    }

    private String resolveCategoryName(int category) {
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
}
