package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CategoryGUIController {

    @FXML
    private Button exitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button relaxButton;
    @FXML
    private Button sportButton;
    @FXML
    private Button safariButton;
    @FXML
    private Button foodButton;
    @FXML
    private Button dogButton;
    @FXML
    private Button funButton;
    @FXML
    private Label sportLabel;
    @FXML
    private Label safariLabel;
    @FXML
    private Label foodLabel;
    @FXML
    private Label relaxLabel;
    @FXML
    private Label dogLabel;
    @FXML
    private Label funLabel;

    private String country;


    public void setCountryLabel(String country) {
        this.country = country;
    }

    private String username;
    private boolean guideController = true;
  public void safariAction() throws IOException, NotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        RelaxGUIController relaxGUIController = loader.getController();
        String category = safariLabel.getText();
        relaxGUIController.setTitle(category);

        relaxGUIController.setCountry(country);

        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void sportAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        RelaxGUIController relaxGUIController = loader.getController();
        String category = sportLabel.getText();
        relaxGUIController.setTitle(category);

        relaxGUIController.setCountry(country);

        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();

    }
    public void dogAction() throws IOException, NotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        RelaxGUIController relaxGUIController = loader.getController();
        String category = dogLabel.getText();
        relaxGUIController.setTitle(category);

        relaxGUIController.setCountry(country);

        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void funAction() throws IOException, NotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        RelaxGUIController relaxGUIController = loader.getController();
        String category = funLabel.getText();
        relaxGUIController.setTitle(category);

        relaxGUIController.setCountry(country);

        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void exitAction() throws IOException {
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void backAction() throws IOException {

        Parent root;
        Stage dialog = Main.getStage();

        Session session = Session.getCurrentSession();
        if(session != null) {
            if (session.getGuideBean() != null) {
                // È un utente Guida
                GuideBean guideBean = session.getGuideBean();
                username = guideBean.getName(); // Utilizza l'email anziché il nome
                root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homePageGuideMap.fxml")));

                Scene scene = new Scene(root);
                dialog.setScene(scene);
                dialog.show();

            } else if (session.getTravelerBean() != null) {
                // È un utente Viaggiatore
                TravelerBean travelerBean = session.getTravelerBean();
                username = travelerBean.getName(); // Utilizza l'email anche per i viaggiatori
                guideController = false;
                root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homePageTravelerMap.fxml")));

                Scene scene = new Scene(root);
                dialog.setScene(scene);
                dialog.show();
            } else {
                // Tipo di utente non riconosciuto
            }
        }
    }
    public void homeAction() throws IOException {
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void foodAction() throws IOException, NotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        RelaxGUIController relaxGUIController = loader.getController();
        String category = foodLabel.getText();
        relaxGUIController.setTitle(category);

        relaxGUIController.setCountry(country);

        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void relaxAction() throws IOException, NotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        RelaxGUIController relaxGUIController = loader.getController();
        String category = relaxLabel.getText();
        relaxGUIController.setTitle(category);

        relaxGUIController.setCountry(country);

        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

}
