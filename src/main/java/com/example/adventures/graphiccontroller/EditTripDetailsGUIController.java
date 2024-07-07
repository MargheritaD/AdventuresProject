package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.ItineraryStopBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Trip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EditTripDetailsGUIController {
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
    private String country;
    private int codice;
    private String categoria;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCategory(String category) {
        this.categoria = category;
    }
    public void inizio(int codice) throws NotFoundException {

        this.codice = codice;

        BookTripController bookTripController = new BookTripController();
        List<ItineraryStopBean> itineraryStopBeans = bookTripController.tableItinerary(codice);
        bookTripController.tableTrip(codice);

        TripBean tripBean = bookTripController.tableTrip(codice);

        this.categoria = tripBean.getCategory();

        // Imposta i valori delle colonne tabella viaggio
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        departureCityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        guideColumn.setCellValueFactory(new PropertyValueFactory<>("guide")); // ci stava guide
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

    public void cancelTripAction() throws NotFoundException, IOException {

        BookTripController bookTripController = new BookTripController();
        bookTripController.cancelTrip(codice);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripCancelled.fxml"));
        Parent root1 = fxmlLoader.load();
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
