package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class CLIHomeGuide extends AbstractCLI {

    public void start() {
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
                    case 2 -> { // request
                        guideRequests.start();
                    }
                    case 3 -> { // new trip

                        choose = false;
                        new CLINewTrip().createTrip();
                        showMenu();
                        //choose = false;
                        //logout();
                    }
                    case 4 -> { // logout
                        choose = false;
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

    public void setCategory(int category, CountryCategoryBean countryCategoryBean) throws SQLException, NotFoundException {

        String categoryName = getCategoryName(category);
        if (categoryName != null) {
            System.out.println("DEBUG1: cat: "+categoryName);
            countryCategoryBean.setCategory(categoryName);
            BookTripController bookTripController = new BookTripController();
            List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);

            for (TripBean tripBean : listOfTripBean) {
                System.out.println("DEBUG1: Trip ID: " + tripBean.getIdTrip());
                System.out.println("DEBUG1: Trip Name: " + tripBean.getTripName());
                System.out.println("DEBUG1: Trip Country: " + tripBean.getCountry());
                System.out.println("DEBUG1: Trip City: " + tripBean.getDepartureCity());
                System.out.println("DEBUG1: Trip Price: " + tripBean.getPrice());
                System.out.println("DEBUG1: Trip category: " + tripBean.getCategory());
            }

            CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
            cliListTripCategoryCountry.start(listOfTripBean);
            System.out.println(countryCategoryBean.getCategory());
            System.out.println(countryCategoryBean.getCountry());
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



    //***********************************************SOPRA OK *****************************************//

/*    public void start(){

        boolean choose = true;
        CountryCategoryBean countryCategoryBean = new CountryCategoryBean();
        CLIGuideRequests guideRequests = new CLIGuideRequests();

        while(choose){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> { //Choose country:
                        choose = false;
                        int country;
                        country = chooseCountry();

                        switch (country){ // choose category
                            case 1 -> {

                                countryCategoryBean.setCountry("Italy");
                                int category = chooseCategory();
                                setCategory(category, countryCategoryBean);
                            }
                            case 2 -> {

                                countryCategoryBean.setCountry("Argentina");
                                int category = chooseCategory();
                                setCategory(category, countryCategoryBean);
                            }
                            case 3 -> {

                                countryCategoryBean.setCountry("USA");
                                int category = chooseCategory();
                                setCategory(category, countryCategoryBean);
                            }
                            case 4 -> {

                                countryCategoryBean.setCountry("Mexico");
                                int category = chooseCategory();
                                setCategory(category, countryCategoryBean);
                            }
                        }
                    }
                    case 2 -> { // request

                        guideRequests.start();
                    }
                    case 3 -> { // new trip
                        choose = false;
                        logout();

                    }
                    case 4 -> { // logout
                        choose = false;
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private int showMenu(){

        CLIPrinter.printMessage("\nMenu: \n");
        CLIPrinter.printMessage("\n1) Choose country: \n");
        CLIPrinter.printMessage("2) Trip requests: \n");
        CLIPrinter.printMessage("3) New trip: \n");
        CLIPrinter.printMessage("4) Logout: \n");
        //CLIPrinter.printMessage("5) Quit: \n\n");

        return  getMenuChoice(1,5);
    }

    private int chooseCountry(){

        CLIPrinter.printMessage("\n****************************** Country list ****************************** \n\n");
        CLIPrinter.printMessage("1. Italy: \n");
        CLIPrinter.printMessage("2. Argentina: \n");
        CLIPrinter.printMessage("3. USA: \n");
        CLIPrinter.printMessage("4. Mexico: \n\n");
        // aggiungi altre country

        return  getMenuChoice(1,4);
    }

    private int chooseCategory(){

        CLIPrinter.printMessage("\n****************************** Category list ****************************** \n\n");
        CLIPrinter.printMessage("1. Relax: \n");
        CLIPrinter.printMessage("2. Sport: \n");
        CLIPrinter.printMessage("3. Dog trekking: \n");
        CLIPrinter.printMessage("4. Safari: \n");
        CLIPrinter.printMessage("5. Food tasting: \n");
        CLIPrinter.printMessage("6. Fun: \n\n");

        return  getMenuChoice(1,6);
    }
    public void setCategory(int category, CountryCategoryBean countryCategoryBean) throws SQLException, NotFoundException {

        int guida = 1;

        switch (category){
            case 1 ->{
                countryCategoryBean.setCategory("Relax");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
                cliListTripCategoryCountry.start(listOfTripBean);
                //cliListTripCategoryCountry.setRole(guida);
            }

            case 2 ->{
                countryCategoryBean.setCategory("Sport");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
                cliListTripCategoryCountry.start(listOfTripBean);
                System.out.println(countryCategoryBean.getCategory());
                System.out.println(countryCategoryBean.getCountry());
            }
            case 3 ->{
                countryCategoryBean.setCategory("Dog trekking");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
                cliListTripCategoryCountry.start(listOfTripBean);
                System.out.println(countryCategoryBean.getCategory());
                System.out.println(countryCategoryBean.getCountry());
            }
            case 4 ->{
                countryCategoryBean.setCategory("Safari");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();

                //cliListTripCategoryCountry.setRole(guida);
                cliListTripCategoryCountry.start(listOfTripBean);

                System.out.println(countryCategoryBean.getCategory());
                System.out.println(countryCategoryBean.getCountry());
            }
            case 5 ->{
                countryCategoryBean.setCategory("Food tasting");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
                cliListTripCategoryCountry.start(listOfTripBean);
                System.out.println(countryCategoryBean.getCategory());
                System.out.println(countryCategoryBean.getCountry());
            }
            case 6 ->{
                countryCategoryBean.setCategory("Fun");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
                cliListTripCategoryCountry.start(listOfTripBean);
                System.out.println(countryCategoryBean.getCategory());
                System.out.println(countryCategoryBean.getCountry());
            }
        }
    }

 */
}
