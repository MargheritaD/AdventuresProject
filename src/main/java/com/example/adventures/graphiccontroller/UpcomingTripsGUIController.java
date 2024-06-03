package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appController.TableTripController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class UpcomingTripsGUIController {

    @FXML
    private Button participantsButton;
    @FXML
    private Label titleLabel;
    @FXML
    private TableView<TripBean> tableViewTrips;
    @FXML
    private TableColumn<TripBean, String> nameColumn;
    @FXML
    private TableColumn<TripBean, String> outboundColumn;
    @FXML
    private TableColumn<TripBean, String> returnColumn;
    @FXML
    private TableColumn<TripBean, Integer> idColumn;
    @FXML
    private TableColumn<TripBean, String> cittaColumn;


    private String username;
    private boolean guideController = true;

    public void initialize(){

        // Controlla il tipo di utente attualmente loggato
        Session session = Session.getCurrentSession();
        if(session != null) {
            if(session.getGuideBean() != null) {
                // È un utente Guida
                GuideBean guideBean = session.getGuideBean();
                username = guideBean.getName(); // Utilizza l'email anziché il nome
                System.out.println("Sono nella tabella relax come GUIDA:" + username);
            } else if(session.getTravelerBean() != null) {
                // È un utente Viaggiatore
                TravelerBean travelerBean = session.getTravelerBean();
                username = travelerBean.getName(); // Utilizza l'email anche per i viaggiatori
                guideController = false;
                System.out.println("Sono nell tabella relax xome VIAGGIATORE:" + username);
                System.out.println("Controlla che sia una turista serve FALSE: " + guideController);
                System.out.println(travelerBean.getId());
            } else {
                // Tipo di utente non riconosciuto
            }
        } else {
            // Sessione non valida
        }
    }

    Date currentDate = new Date(System.currentTimeMillis());

    public void inizio()throws IOException, NotFoundException{

        // Ottieni la lista dei viaggi dal TripDAO
        System.out.println("\nData adesso "+ currentDate);

        TableTripController tableTripController = new TableTripController();

        List<TripBean> tripBeans = tableTripController.upcomingTableTrip(currentDate, username);

        // Imposta i valori delle colonne
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idTrip")); // prima era idColumn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        //cittaColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));

        // Popola la tabella con i viaggi
        tableViewTrips.getItems().addAll(tripBeans);

        // Disabilita il pulsante all'avvio dell'applicazione
        participantsButton.setDisable(true);

        // Aggiungo un listener per la selezione delle righe nella tabella
        tableViewTrips.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Abilita il pulsante se una riga è selezionata
                participantsButton.setDisable(false);
                // Salva i dati della riga selezionata

            } else {
                // Disabilita il pulsante se nessuna riga è selezionata
                participantsButton.setDisable(true);
            }
        });

    }


    public void homeAction() throws IOException {

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void backAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/HomePageGuideMap.fxml"));
        Parent root = loader.load();

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


}
