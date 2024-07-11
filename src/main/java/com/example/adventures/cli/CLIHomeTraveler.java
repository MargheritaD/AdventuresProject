package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class CLIHomeTraveler extends AbstractCLI {

    public void start() {
        boolean chooseT = true;
        CountryCategoryBean countryCategoryBean = new CountryCategoryBean();
        CLITravelerRequests travelerRequests = new CLITravelerRequests();

        while (chooseT) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> { // Choose country:
                        chooseT = false;
                        handleCountrySelection(countryCategoryBean);
                    }
                    case 2 ->  // Your requests
                            travelerRequests.start();

                    case 3 -> { // logout
                        chooseT = false;
                        System.exit(0);
                    }
                    default -> CLIPrinter.printMessage("Invalid choice");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleCountrySelection(CountryCategoryBean countryCategoryBean) throws SQLException, NotFoundException {
        int countryT = chooseCountry();
        switch (countryT) {
            case 1 -> countryCategoryBean.setCountry("Italy");
            case 2 -> countryCategoryBean.setCountry("Argentina");
            case 3 -> countryCategoryBean.setCountry("USA");
            case 4 -> countryCategoryBean.setCountry("Mexico");
            default -> {
                CLIPrinter.printMessage("Invalid country choice");
                return;
            }
        }
        int category = chooseCategory();
        setCategory(category, countryCategoryBean);
    }

    private int showMenu() {
        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Choose country: \n");
        CLIPrinter.printMessage("2) Your requests: \n");
        CLIPrinter.printMessage("3) Logout: \n");
        return getMenuChoice(1, 4);
    }

    private int chooseCountry() {
        CLIPrinter.printMessage("\n****************************** Country list ****************************** \n\n");
        CLIPrinter.printMessage("1. Italy: \n");
        CLIPrinter.printMessage("2. Argentina: \n");
        CLIPrinter.printMessage("3. USA: \n");
        CLIPrinter.printMessage("4. Mexico: \n\n");
        return getMenuChoice(1, 4);
    }

    private int chooseCategory() {
        CLIPrinter.printMessage("\n****************************** Category list ****************************** \n\n");
        CLIPrinter.printMessage("1. Relax: \n");
        CLIPrinter.printMessage("2. Sport: \n");
        CLIPrinter.printMessage("3. Dog trekking: \n");
        CLIPrinter.printMessage("4. Safari: \n");
        CLIPrinter.printMessage("5. Food tasting: \n");
        CLIPrinter.printMessage("6. Fun: \n\n");
        return getMenuChoice(1, 6);
    }

    public void setCategory(int category, CountryCategoryBean countryCategoryBean) throws SQLException, NotFoundException {

        String categoryName = getCategoryName(category);
        if (categoryName != null) {

            countryCategoryBean.setCategory(categoryName);
            BookTripController bookTripController = new BookTripController();
            List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);

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
}
