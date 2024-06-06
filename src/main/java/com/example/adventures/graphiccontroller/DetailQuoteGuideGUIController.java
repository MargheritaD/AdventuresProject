package com.example.adventures.graphiccontroller;

import com.example.adventures.AppController.BookTripController;
import com.example.adventures.Main;
import com.example.adventures.AppController.DatailsQuoteController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Trip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DetailQuoteGuideGUIController {

    @FXML
    private Button quoteButton;
    @FXML
    private Button bookButton;
    @FXML
    private RadioButton heltcareButton;
    @FXML
    private RadioButton tripCancellationButton;
    @FXML
    private RadioButton luggageButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label outboundLabel;
    @FXML
    private Label returnLabel;
    @FXML
    private TableView<ItineraryStopBean> itineraryTable;
    @FXML
    private TableColumn<ItineraryStopBean, String> stopColumn;
    @FXML
    private TableColumn<ItineraryStopBean, LocalDate> arrivalColumn;
    @FXML
    private TableColumn<ItineraryStopBean, LocalDate> departureColumn;
    @FXML
    private TableView<TripBean> tripTable;
    @FXML
    private TableColumn<TripBean, String> nameColumn;
    @FXML
    private TableColumn<TripBean, String> departureCityColumn;
    @FXML
    private TableColumn<TripBean, String> outboundColumn;
    @FXML
    private TableColumn<TripBean, String> returnColumn;
    @FXML
    private TableColumn<TripBean, String> guideColumn;
    @FXML
    private TableColumn<TripBean, String> priceColumn;


    private int tripId;
    private Trip trip;
    private String categoria;
    private String country;
    private float tripPrice;
    int userId;
    int travalerId;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCategory(String category) {
        this.categoria = category;
    }

    public void initialize(){

        // Controlla il tipo di utente attualmente loggato
        Session session = Session.getCurrentSession();
        if(session != null) {
            if(session.getGuideBean() != null) {
                // È un utente Guida
                GuideBean guideBean = session.getGuideBean();
                userId = guideBean.getId(); // Utilizza l'email anziché il nome
                System.out.println("Sono nella details quote relax come GUIDA:" + userId);
            } else if(session.getTravelerBean() != null) {
                // È un utente Viaggiatore
                TravelerBean travelerBean = session.getTravelerBean();
                userId = travelerBean.getId(); // Utilizza l'email anche per i viaggiatori
                System.out.println("Sono nell details quote  xome VIAGGIATORE:" + userId);
            } else {
                // Tipo di utente non riconosciuto, gestire di conseguenza
            }
        } else {
            // Sessione non valida, gestire di conseguenza
        }
    }


    public void inizio(int codice) throws NotFoundException {

       // DatailsQuoteController detailsQuoteController = new DatailsQuoteController();
        //System.out.println("CODICE VIAGGIO: " + codice);
        //List<ItineraryStopBean> itineraryStopBeans = detailsQuoteController.tableItinerary(codice);// prima era tripId
       //TripBean tripBean = detailsQuoteController.tableTrip(codice);

        BookTripController bookTripController = new BookTripController();
        List<ItineraryStopBean> itineraryStopBeans = bookTripController.tableItinerary(codice);// prima era tripId
        TripBean tripBean = bookTripController.tableTrip(codice);

        System.out.println("Categoria datails quote: "+ tripBean.getCategory());
        tripPrice = Float.parseFloat(tripBean.getGuide());
        System.out.println("prezzo: "+ tripBean.getGuide());

        // Imposta i valori delle colonne tabella viaggio
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        departureCityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        guideColumn.setCellValueFactory(new PropertyValueFactory<>("guide"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        System.out.println("Categoria datails quote colonna: "+ guideColumn);

        // Popola la tabella con i dettagli
        tripTable.getItems().addAll(tripBean);

        // Imposta i valori delle colonne tabella itinerario
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));

        // Popola la tabella con le tappe
        itineraryTable.getItems().addAll(itineraryStopBeans);

    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
        System.out.println("CODICE VIAGGIO THIS:" + tripId);
    }

    public void quoteAction(){

    }


    public void exitAction() throws IOException {

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void backAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Relax.fxml"));
        Parent root = loader.load();

        // Ottieni il controller della schermata precedente
        RelaxGUIController relaxGUIController = loader.getController();

        // Imposta il valore della stringa nel controller della schermata precedente
        relaxGUIController.setTitle(categoria);
        relaxGUIController.setCountry(country);
        relaxGUIController.initialize();
        relaxGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void homeAction() throws IOException {

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

}
