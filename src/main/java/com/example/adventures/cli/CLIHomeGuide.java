package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class CLIHomeGuide extends AbstractCLI {
    public void start(){

        boolean choose = true;
        CountryCategoryBean countryCategoryBean = new CountryCategoryBean();

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

                        choose = false;
                        CLIPrinter.printMessage("Not implemented");
                    }
                    case 3 -> {
                        choose = false;
                        logout();

                    }
                    case 4 -> {
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

        CLIPrinter.printMessage("Menu: \n");
        CLIPrinter.printMessage("1. Choose country: \n");
        CLIPrinter.printMessage("2. Trip requests: \n");
        CLIPrinter.printMessage("3. Logout: \n");
        CLIPrinter.printMessage("4. Quit: \n");

        return  getMenuChoice(1,4);
    }

    private int chooseCountry(){

        CLIPrinter.printMessage("Country list: \n");
        CLIPrinter.printMessage("1. Italy: \n");
        CLIPrinter.printMessage("2. Argentina: \n");
        CLIPrinter.printMessage("3. USA: \n");
        CLIPrinter.printMessage("4. Mexico: \n");
        // aggiungi altre country

        return  getMenuChoice(1,4);
    }

    private int chooseCategory(){

        CLIPrinter.printMessage("Category list: \n");
        CLIPrinter.printMessage("1. Relax: \n");
        CLIPrinter.printMessage("2. Sport: \n");
        CLIPrinter.printMessage("3. Dog trekking: \n");
        CLIPrinter.printMessage("4. Safari: \n");
        CLIPrinter.printMessage("5. Food tasting: \n");
        CLIPrinter.printMessage("6. Fun: \n");
        // aggiungi altre country

        return  getMenuChoice(1,6);
    }
    public void setCategory(int category, CountryCategoryBean countryCategoryBean) throws SQLException, NotFoundException {
        switch (category){
            case 1 ->{
                countryCategoryBean.setCategory("Relax");
                BookTripController bookTripController = new BookTripController();
                List<TripBean> listOfTripBean = bookTripController.selectCountryAndCategory(countryCategoryBean);
                CLIListTripCategoryCountry cliListTripCategoryCountry = new CLIListTripCategoryCountry();
                cliListTripCategoryCountry.start(listOfTripBean);
                System.out.println(countryCategoryBean.getCategory());
                System.out.println(countryCategoryBean.getCountry());
            }

            case 2 ->{
                countryCategoryBean.setCategory("Sport");
              //  new // nuovaPage.start(countryCategoryBean)
            }
            case 3 ->{
                countryCategoryBean.setCategory("Dog trekking");
              //  new // nuovaPage.start(countryCategoryBean)
            }
            case 4 ->{
                countryCategoryBean.setCategory("Safari");
               // new // nuovaPage.start(countryCategoryBean)
            }
            case 5 ->{
                countryCategoryBean.setCategory("Food tasting");
               // new // nuovaPage.start(countryCategoryBean)
            }
            case 6 ->{
                countryCategoryBean.setCategory("Fun");
              //  new // nuovaPage.start(countryCategoryBean)
            }
        }
    }
}
