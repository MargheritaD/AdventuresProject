package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appController.DatailsQuoteController;
import com.example.adventures.appController.NewTripController;
import com.example.adventures.appController.TableTripController;
import com.example.adventures.bean.*;
import com.example.adventures.dao.ItineraryStopDAO;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.engineering.Session;
import com.example.adventures.engineering.decoretor.CancellationQuote;
import com.example.adventures.engineering.decoretor.HealthQuote;
import com.example.adventures.engineering.decoretor.LuggageQuote;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.ItineraryStop;
import com.example.adventures.model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

/*
    public void initialize(){

        GuideBean guideBean = Session.getCurrentSession().getGuideBean();
        guideId = guideBean.getId();
    }
*/
    public void setTripId(int tripId) {
        this.tripId = tripId;
        System.out.println("CODICE VIAGGIO THIS:" + tripId);
    }


    public void inizio(int codice) throws NotFoundException{

        DatailsQuoteController detailsQuoteController = new DatailsQuoteController();
        System.out.println("CODICE VIAGGIO: " + codice);
        List<ItineraryStopBean> itineraryStopBeans = detailsQuoteController.tableItinerary(codice);// prima era tripId
        TripBean tripBean = detailsQuoteController.tableTrip(codice);

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
    public void bookAction() throws IOException {

        // dovrebbe sapere il codice della guida perche lo inizializzo nelmetodo initialize

        System.out.println("++++++++++++++ BOOK ACTION:  trip id: " + tripId + " person id: " + userId);

        //int idPerson = Session.getCurrentSession().getGuideBean().getId();

        try{
            DatailsQuoteController datailsQuoteController = new DatailsQuoteController();
            System.out.println("Passo al controller logico");
            datailsQuoteController.bookTrip(tripId, userId);
            System.out.println("Passo i paramteri al controller logico");

        }catch (Exception e) { //NotFoundException
            System.out.println(e);
            //Printer.printError(e.getMessage());
        }

        // ci va l'if per vedere se è andata bene

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripBooked.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        dialog.centerOnScreen();
        dialog.show();


    }
    public void quoteAction(ActionEvent event) throws IOException {

        QuoteBean quoteBean = new QuoteBean(tripPrice); // Inizializzo con il prezzo del viaggio

        try {
            if (healthcareButton.isSelected()) {
                quoteBean.addInsurance(new HealthQuote());
            }
            if (tripCancellationButton.isSelected()) {
                quoteBean.addInsurance(new CancellationQuote());
            }
            if (luggageButton.isSelected()) {
                quoteBean.addInsurance(new LuggageQuote());
            }

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
        } catch (Exception e) {
            System.out.println("Eccezione quote: " + e.getMessage());
            // ShowExceptionSupport.showException(e.getMessage());
        }

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
