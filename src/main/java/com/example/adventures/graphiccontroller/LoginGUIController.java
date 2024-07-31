package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.bean.LoginBean;
import com.example.adventures.engineering.Printer;
import com.example.adventures.exception.EmailFormatException;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.appcontroller.LoginController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginGUIController {

    @FXML
    TextField usernameText;
    @FXML
    PasswordField passwordText;

    public void switchToWelcome() throws IOException, NotFoundException {


        try {
            LoginBean loginBean = new LoginBean(usernameText.getText(),passwordText.getText());
            LoginController loginController = new LoginController();
            loginController.checkUser(loginBean);
            Parent root;
            Stage dialog = Main.getStage();
            switch(loginBean.getRole()){
                case 1 ->  {
                    loginController.guideLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));
                }
                case 2 -> {
                    loginController.travelerLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/com/example/adventures/HomePageTravelerMap.fxml")));
                }
                default -> throw new UserNotFoundException();
            }
            Scene scene = new Scene(root);
            dialog.setScene(scene);
            dialog.show();
        } catch (EmailFormatException | UserNotFoundException  e) {
            Printer.printError(e.getMessage());
        } catch (IOException e){
            Printer.printError(e.getMessage());
        }
    }
    public void switchToNew() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("newAccount.fxml")));
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void googleAction() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
/*
        try {

            LoginBean loginBean = new LoginBean(usernameText.getText(),passwordText.getText());
            LoginController loginController = new LoginController();

            System.out.println("Sono nel controller GUI");

            loginController.checkUser(loginBean);

            Parent root;
            Stage dialog = Main.getStage();


            switch(loginBean.getRole()){
                case 1 ->  {
                    loginController.guideLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));
                }
                case 2 -> {
                    loginController.travelerLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/com/example/adventures/HomePageTravelerMap.fxml")));
                }
                default -> throw new UserNotFoundException();
            }

            Scene scene = new Scene(root);
            dialog.setScene(scene);
            dialog.show();

        } catch (EmailFormatException | UserNotFoundException  e) {
            Printer.printError(e.getMessage());
        } catch (IOException e){
            Printer.printError(e.getMessage());
        }

    }

    public void switchToNew() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("newAccount.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void googleAction() throws IOException{

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

 */
}
