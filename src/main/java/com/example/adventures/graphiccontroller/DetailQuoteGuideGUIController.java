package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.bean.*;
import com.example.adventures.engineering.Session;
import com.example.adventures.engineering.decoretor.*;
import com.example.adventures.engineering.decoretor.decorations.CancellationDecorator;
import com.example.adventures.engineering.decoretor.decorations.HealthcareDecorator;
import com.example.adventures.engineering.decoretor.decorations.LuggageDecorator;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Trip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DetailQuoteGuideGUIController {


    @FXML
    private Button bookButton;

    @FXML
    private Button quoteButton;
    @FXML
    private RadioButton luggageButton;
    @FXML
    private RadioButton tripCancellationButton;
    @FXML
    private RadioButton healthcareButton;
    @FXML
    private Label nameLabel;

    @FXML
    private Label returnLabel;

    @FXML
    private Label outboundLabel;

    @FXML
    private Label cityLabel;
    @FXML
    private TableColumn<ItineraryStopBean, LocalDate> arrivalColumn;
    @FXML
    private TableView<ItineraryStopBean> itineraryTable;
    @FXML
    private TableColumn<ItineraryStopBean, LocalDate> departureColumn;
    @FXML
    private TableColumn<ItineraryStopBean, String> stopColumn;

    @FXML
    private TableView<TripBean> tripTable;
    @FXML
    private TableColumn<TripBean, String> departureCityColumn;
    @FXML
    private TableColumn<TripBean, String> nameColumn;
    @FXML
    private TableColumn<TripBean, String> returnColumn;
    @FXML
    private TableColumn<TripBean, String> outboundColumn;
    @FXML
    private TableColumn<TripBean, String> priceColumn;
    @FXML
    private TableColumn<TripBean, String> guideColumn;



    private Trip trip;
    private int tripId;
    private String country;
    private String categoria;
    private float tripPrice;
    int travalerId;
    int userId;

    public void setCategory(String category) {
        this.categoria = category;
    }

    public void setCountry(String country) {

        this.country = country;
    }


    public void initialize(){

        // Controlla il tipo di utente attualmente loggato
        Session session = Session.getCurrentSession();
        if(session != null) {
            if(session.getGuideBean() != null) {
                // Guida
                GuideBean guideBean = session.getGuideBean();
                userId = guideBean.getId();
            } else if(session.getTravelerBean() != null) {
                // Viaggiatore
                TravelerBean travelerBean = session.getTravelerBean();
                userId = travelerBean.getId();
            }
        }
    }

    public void setTripDetails(TripBean tripBean) throws NotFoundException {

        tripPrice = Float.parseFloat(tripBean.getPrice());

        ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
        List<ItineraryStopBean> itineraryStopBeans = viewTripDetailsController.tableItinerary(tripBean);// prima era tripId


        // Imposta i valori delle colonne tabella viaggio
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        departureCityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        guideColumn.setCellValueFactory(new PropertyValueFactory<>("guide"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Popolare tabella con i dettagli
        tripTable.getItems().addAll(tripBean);

        // Impostare i valori delle colonne tabella itinerario
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));

        // Popolare la tabella con le tappe
        itineraryTable.getItems().addAll(itineraryStopBeans);

    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void quoteAction() throws IOException {

        // Inizializza il preventivo di base con il prezzo del viaggio
        Quote baseQuote = new TripPriceQuote((int)tripPrice);


        // Aggiungi le assicurazioni selezionate dall'utente
        if (healthcareButton.isSelected()) {
            baseQuote = new HealthcareDecorator(baseQuote);
        }
        if (tripCancellationButton.isSelected()) {
            baseQuote = new CancellationDecorator(baseQuote);
        }
        if (luggageButton.isSelected()) {
            baseQuote = new LuggageDecorator(baseQuote);
        }

        // Crea il QuoteBean con il preventivo finale
        QuoteBean quoteBean = new QuoteBean(baseQuote.getPrice());

        // Passaggio del bean alla finestra successiva
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/quote.fxml"));
        Parent root1 = fxmlLoader.load();

        QuoteGUIController quoteGUIController = fxmlLoader.getController();
        quoteGUIController.showQuoteResult(quoteBean); // Passa il bean al controller della nuova finestra

        Stage dialogQ = new Stage();
        dialogQ.initModality(Modality.APPLICATION_MODAL);
        dialogQ.initStyle(StageStyle.UNDECORATED);
        dialogQ.setScene(new Scene(root1));
        dialogQ.centerOnScreen();
        dialogQ.show();
    }


    public void exitAction() throws IOException {

        Parent root;
        Stage dialogE = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene sceneE = new Scene(root);
        dialogE.setScene(sceneE);
        dialogE.show();
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

        Stage stage = Main.getStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
