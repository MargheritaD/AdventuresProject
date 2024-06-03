package com.example.adventures.graphiccontroller;

import com.example.adventures.AppController.QuoteController;
import com.example.adventures.bean.QuoteBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class QuoteGUIController {

    @FXML
    Label priceLabel;

    public void showQuoteResult(QuoteBean quoteBean) {

        QuoteController quoteController = new QuoteController();
        float totalQuote = quoteController.calculateQuote(quoteBean);

        priceLabel.setText(totalQuote + " $");
    }

    public void closeAction(ActionEvent event){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
