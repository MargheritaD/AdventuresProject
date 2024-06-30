package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class CreateAccountGUIController {
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    public void toLogin() throws IOException{

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void toCreate() throws IOException{
        // elimina
    }

}
