package com.example.adventures.dao;

import com.example.adventures.exception.NotFoundException;
import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.model.UserProfile;
import com.example.adventures.engineering.Printer;

import java.io.*;
/*

                        NON LO USIAMO PER IL LOGIN

public class LoginDAOCVS implements LoginDAO{

    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int ROLE = 2;

    @Override
    public UserProfile checkUser(String username, String password) throws UserNotFoundException {
        int role;
        UserProfile userProfile = null;
        File file = new File(CSV_FILE_NAME);

        // closing the resouce bufferReader is handeled automatically by the try-with-resouces
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String row;
            String[] data;

            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if(data[USERNAME].equals(username) && data[PASSWORD].equals(password)){
                    switch (data[ROLE]){
                        case "guide" -> role = 1;
                        case "traveler" -> role = 2;
                        default -> throw new NotFoundException("No role found");
                    }
                    userProfile = new UserProfile(role, username);
                }
            }

            if(userProfile == null){
                throw new UserNotFoundException();
            }
        }catch (IOException | NotFoundException e){
            Printer.printError(e.getMessage());
        }

        return userProfile;
    }
}*/
