package com.example.adventures.graphiccontroller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.IOException;


public class TripAddedGUIController {
    @FXML
    Button closeButton;

    public void closeAction(ActionEvent event) throws IOException {

        ((Node)event.getSource()).getScene().getWindow().hide();

    }

}
