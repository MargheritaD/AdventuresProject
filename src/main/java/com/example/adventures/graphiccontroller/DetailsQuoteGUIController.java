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

        // Imposta i valori delle colonne tabella viaggio
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        departureCityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        guideColumn.setCellValueFactory(new PropertyValueFactory<>("guide"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Popola la tabella con i dettagli
        tripTable.getItems().addAll(tripBean);

        // Imposta i valori delle colonne tabella itinerario
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));

        // Popola la tabella con le tappe
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
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        dialog.centerOnScreen();
        dialog.show();


    }

    public void quoteAction() throws IOException {

        // Inizializza il preventivo di base con il prezzo del viaggio
        Quote baseQuote = new TripPriceQuote((int)tripPrice);

        // Aggiungi la tassa per destinazione specifica
        if("Australia".equals(country)){
            baseQuote = new AustraliaQuote(baseQuote);
        } else if ("Argentina".equals(country)) {
            baseQuote = new ArgentinaQuote(baseQuote);
        }else if ("Brasil".equals(country)) {
            baseQuote = new BrasilQuote(baseQuote);
        }else if ("Chile".equals(country)) {
            baseQuote = new ChileQuote(baseQuote);
        }else if ("India".equals(country)) {
            baseQuote = new IndiaQuote(baseQuote);
        }else if ("Italy".equals(country)) {
            baseQuote = new ItalyQuote(baseQuote);
        }else if ("Mexico".equals(country)) {
            baseQuote = new MexicoQuote(baseQuote);
        }else if ("Perù".equals(country)) {
            baseQuote = new PuruQuote(baseQuote);
        }else if ("Spain".equals(country)) {
            baseQuote = new SpainQuote(baseQuote);
        }else if ("USA".equals(country)) {
            baseQuote = new USAQuote(baseQuote);
        }


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

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        dialog.centerOnScreen();
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
