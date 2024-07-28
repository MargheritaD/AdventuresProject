package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.*;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;
import java.util.Vector;

public class CLIHomeGuide extends AbstractCLI {

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
                        showMenu();
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
        switch (country) {
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
        CLIPrinter.printMessage("2) Trip requests: \n");
        CLIPrinter.printMessage("3) New trip: \n");
        CLIPrinter.printMessage("4) Logout: \n");
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

    public void setCategory(int category, CountryCategoryBean countryCategoryBean) throws NotFoundException {

        String categoryName = getCategoryName(category);
        if (categoryName != null) {

            countryCategoryBean.setCategory(categoryName);
            ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
            List<TripBean> listOfTripBean = viewTripDetailsController.selectCountryAndCategory(countryCategoryBean);
            //BookTripController bookTripController = new BookTripController();
            //List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);

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
