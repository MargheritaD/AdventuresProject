package com.example.adventures.graphiccontroller;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.Main;
import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.bean.*;
import com.example.adventures.engineering.Printer;
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

public class DetailsQuoteGUIController {

    @FXML
    private Button quoteButton;
    @FXML
    private Button bookButton;
    @FXML
    private RadioButton healthcareButton;
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
    private TableColumn<ItineraryStopBean, LocalDate> arrivalColumn;
    @FXML
    private TableView<ItineraryStopBean> itineraryTable;

    @FXML
    private TableColumn<ItineraryStopBean, LocalDate> departureColumn;
    @FXML
    private TableColumn<ItineraryStopBean, String> stopColumn;

    @FXML
    private TableColumn<TripBean, String> departureCityColumn;

    @FXML
    private TableView<TripBean> tripTable;
    @FXML
    private TableColumn<TripBean, String> returnColumn;
    @FXML
    private TableColumn<TripBean, String> guideColumn;
    @FXML
    private TableColumn<TripBean, String> nameColumn;
    @FXML
    private TableColumn<TripBean, String> priceColumn;

    @FXML
    private TableColumn<TripBean, String> outboundColumn;

    private String categoria;
    private String country;
    private int tripId;
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

            } else if(session.getTravelerBean() != null) {
                // È un utente Viaggiatore
                TravelerBean travelerBean = session.getTravelerBean();
                userId = travelerBean.getId(); // Utilizza l'email anche per i viaggiatori

            } else {
                // Tipo di utente non riconosciuto, gestire di conseguenza
            }
        } else {
            // Sessione non valida, gestire di conseguenza
        }
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }


    public void inizio(TripBean tripBean) throws NotFoundException{


        ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
        List<ItineraryStopBean> itineraryStopBeans = viewTripDetailsController.tableItinerary(tripBean);// prima era tripId

        tripPrice = Float.parseFloat(tripBean.getPrice());

        // Valori delle colonne tabella viaggio
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        departureCityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        guideColumn.setCellValueFactory(new PropertyValueFactory<>("guide"));


        // Tabella con i dettagli
        tripTable.getItems().addAll(tripBean);

        // Valori delle colonne tabella itinerario
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));

        // Tabella con le tappe
        itineraryTable.getItems().addAll(itineraryStopBeans);

    }
    public void bookAction() throws IOException {

        // dovrebbe sapere il codice della guida perche lo inizializzo nelmetodo initialize

        try{

            BookTripController bookTripController = new BookTripController();
            RequestBean requestBean = new RequestBean(tripId,Session.getCurrentSession().getTravelerBean().getId());
            bookTripController.sendRequest(requestBean);

        }catch (Exception e) { //NotFoundException

            Printer.printError(e.getMessage());
        }



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripBooked.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage dialogD = new Stage();
        dialogD.initModality(Modality.APPLICATION_MODAL);
        dialogD.initStyle(StageStyle.UNDECORATED);
        dialogD.setScene(new Scene(root1));
        dialogD.centerOnScreen();
        dialogD.show();


    }

    public void quoteAction() throws IOException {

        // Inizializza il preventivo di base con il prezzo del viaggio
        Quote baseQuoteT = new TripPriceQuote((int)tripPrice);

        // Aggiungi le assicurazioni selezionate dall'utente
        if (healthcareButton.isSelected()) {
            baseQuoteT = new HealthcareDecorator(baseQuoteT);
        }
        if (tripCancellationButton.isSelected()) {
            baseQuoteT = new CancellationDecorator(baseQuoteT);
        }
        if (luggageButton.isSelected()) {
            baseQuoteT = new LuggageDecorator(baseQuoteT);
        }

        // Crea il QuoteBean con il preventivo finale
        QuoteBean quoteBean = new QuoteBean(baseQuoteT.getPrice());

        // Passaggio del bean alla finestra successiva
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/quote.fxml"));
        Parent root1 = fxmlLoader.load();

        QuoteGUIController quoteGUIController = fxmlLoader.getController();
        quoteGUIController.showQuoteResult(quoteBean); // Passa il bean al controller della nuova finestra

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        dialog.centerOnScreen();
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

    public void exitAction() throws IOException {

        Parent root;
        Stage dialogS = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialogS.setScene(scene);
        dialogS.show();
    }

    public void homeAction() throws IOException {

        Parent root;
        Stage stage = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
