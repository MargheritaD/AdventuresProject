package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.NewTripController;
import com.example.adventures.bean.*;
import com.example.adventures.engineering.Printer;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.FormEmptyException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewTripGUIController {

    @FXML
    Button addStopButton;
    @FXML
    Button removeStopButton;
    @FXML
    Button createTripButton;
    @FXML
    Button logoutButton;
    @FXML
    Button homeButton;
    @FXML
    TextField nomeViaggio;
    @FXML
    TextField cittaPartenza;
    @FXML
    TextField prezzo;
    @FXML
    TextField tappa;
    @FXML
    DatePicker dataPartenza;
    @FXML
    DatePicker dataRitorno;
    @FXML
    DatePicker arrivoTappa;
    @FXML
    DatePicker tappaFine;
    @FXML
    TableView<ItineraryStopBean> tabellaItinerario;
    @FXML
    TableColumn<ItineraryStopBean, String>colonnaTappa;
    @FXML
    TableColumn<ItineraryStopBean, String>colonnaArrivo;
    @FXML
    TableColumn<ItineraryStopBean, String>colonnaFine;
    @FXML
    ChoiceBox<String> categoria;
    @FXML
    ObservableList<String> categoryList = FXCollections.observableArrayList("Dog trekking","Food tasting","Fun","Relax", "Safari", "Sport");
    @FXML
    ChoiceBox<String> paese;
    @FXML
    ObservableList<String> countryList = FXCollections.observableArrayList("Argentina", "Australia", "Brasil", "Chile", "India","Italy","Mexico", "Perù","Spain");

    @FXML
    public void initialize() {
        categoria.setItems(categoryList);
        paese.setItems(countryList);

        colonnaTappa.setCellValueFactory(new PropertyValueFactory<>("city"));
        colonnaArrivo.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        colonnaFine.setCellValueFactory(new PropertyValueFactory<>("departure"));
    }

    public void logoutAction() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void homeAction() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void addStop(){ // aggiungi tappa alla tabella
        ItineraryStopBean itineraryItem = new ItineraryStopBean(tappa.getText(), arrivoTappa.getValue(), tappaFine.getValue());
        ObservableList<ItineraryStopBean> itinenraryItems = tabellaItinerario.getItems();
        itinenraryItems.add(itineraryItem);
        tabellaItinerario.setItems(itinenraryItems);
        tappa.setText("");
        arrivoTappa.setValue(null);
        tappaFine.setValue(null);

    }

    public void removeStop(){ // elimina la tappa selezionata
        int selectedID = tabellaItinerario.getSelectionModel().getSelectedIndex();
        tabellaItinerario.getItems().remove(selectedID);
    }

    public void createTrip() throws IOException{ // aggiungi il viaggio nel db

        List<ItineraryStopBean> stopList;

        try{
            if(nomeViaggio.getText() == null)
                throw new FormEmptyException("Name");
            if(categoria.getValue() == null)
                throw new FormEmptyException("Category");
            if(cittaPartenza.getText() == null)
                throw new FormEmptyException("Departure city ");
            if(prezzo.getText() == null)
                throw new FormEmptyException("Price ");
            if(dataPartenza.getValue() == null)
                throw new FormEmptyException("Outbound date");
            if(dataRitorno.getValue() == null)
                throw new FormEmptyException("Return date");
            if(paese.getValue() == null)
                throw new FormEmptyException("Country");

            stopList = getStop();

            LocationInfoBean locationInfoBean = new LocationInfoBean(cittaPartenza.getText(), paese.getValue());
            PeriodInfoBean periodInfoBean = new PeriodInfoBean(dataPartenza.getValue(), dataRitorno.getValue());


            TripBean tripBean = new TripBean(nomeViaggio.getText(), locationInfoBean, periodInfoBean, categoria.getValue(), prezzo.getText());
            tripBean.setStops(stopList);
            GuideBean guideBean = Session.getCurrentSession().getGuideBean();

            NewTripController newTripController = new NewTripController();
            newTripController.createTrip(tripBean, guideBean);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/NewTripAdded.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setScene(new Scene(root1));
            dialog.centerOnScreen();
            dialog.show();

            nomeViaggio.setText("");
            cittaPartenza.setText("");
            prezzo.setText("");
            dataPartenza.setValue(null);
            dataRitorno.setValue(null);
            paese.setValue(null);
            categoria.setValue(null);
            tabellaItinerario.getItems().clear();


        }catch(FormEmptyException e ){
            Printer.printError(e.getMessage());
        }
    }
    public void addName(){ // nome del viaggio

    }

    public void addCitta(){ // la citàà di partenza

    }

    public void setPrezzo(){ // prezzo del viaggio

    }

    public void setDataPartenza(){ // data inizio viaggio

    }

    public void setDataRitorno(){ // data fine viaggio

    }

    public void setTappaArrivo(){ //CANCELLALA

    }

    public void setTappa(){ // nome della tappa

    }

    public void setArrivoTappa(){ // data di arrivo nella tappa

    }

    public void setTappaFine(){ // data ultimo giorno della tappa

    }



    public List<ItineraryStopBean> getStop() {
        List<ItineraryStopBean> stops = new ArrayList<>();
        ItineraryStopBean stop;

        ObservableList<ItineraryStopBean> itinenraryItems = tabellaItinerario.getItems();

        for (ItineraryStopBean itineraryItem : itinenraryItems) {
            stop = itineraryItem;
            stops.add(stop);
        }
        return stops;
    }

}
