package com.example.adventures.graphiccontroller;


import com.example.adventures.Main;
import com.example.adventures.appController.NewTripController;
import com.example.adventures.appController.TableTripController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.dao.TripDAO;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.model.Trip;
import com.example.adventures.model.UserProfile;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class RelaxGUIController {

    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button detailsButton;
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

    private String valore;
    private String country;

    //private TableRowData selectedRowData;

    public void setTitle(String valore) {
        this.valore = valore;
        titleLabel.setText(valore);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void inizio()throws IOException, NotFoundException{
        // Ottieni la lista dei viaggi dal TripDAO
        System.out.println("\nVALORE CATEGORIA initialize: "+ valore);

        TableTripController tableTripController = new TableTripController();

        //List<TripBean> tripBeans = tableTripController.tableTrip(valore);
        List<TripBean> tripBeans = tableTripController.tableTripCountry(valore, country);

        // Imposta i valori delle colonne
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idTrip")); // prima era idColumn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        //cittaColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));

        // Popola la tabella con i viaggi
        tableViewTrips.getItems().addAll(tripBeans);

        // Disabilita il pulsante all'avvio dell'applicazione
        detailsButton.setDisable(true);

        // Aggiungo un listener per la selezione delle righe nella tabella
        tableViewTrips.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Abilita il pulsante se una riga è selezionata
                detailsButton.setDisable(false);
                // Salva i dati della riga selezionata
                //selectedRowData = newSelection;
            } else {
                // Disabilita il pulsante se nessuna riga è selezionata
                detailsButton.setDisable(true);
            }
        });

    }

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

   /*public void initialize(){

        GuideBean guideBean = Session.getCurrentSession().getGuideBean();
        username = guideBean.getName(); // Però andrebbe fatto con la mail perchè se no significa che il nome è univoco
        System.out.println("+++++++++++++++++++++++++++++++NAME GUIDA:"+username);
    }*/

    public void detailsAction() throws IOException, NotFoundException {

        TripBean selectedTrip = tableViewTrips.getSelectionModel().getSelectedItem();

        System.out.println("\n\nRELAX ID VIAGGIO prima: " + selectedTrip.getIdTrip());
        System.out.println("\n\nRELAX nome VIAGGIO prima: " + selectedTrip.getTripName());

        if (selectedTrip != null) {


            System.out.println("++++++++++++++++++++++++++++++++Selected Guide: " + selectedTrip.getGuide());

            System.out.println("++++++++++++++++++++++++++++++++Current Guide: " + username);

            System.out.println("CONTROLLO GUIDA: "+ guideController);


            if(!username.equals(selectedTrip.getGuide())  && guideController){

                System.out.println("L'organizzatore e la utente sono diversi e l'utente loggato è una guida");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripDetailsQuoteGuide.fxml"));

                try{
                    Parent root = fxmlLoader.load();
                    Stage dialog = Main.getStage();
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                    dialog.show();

                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Eccezione nel caricamento nuova pagina");
                }

                DetailQuoteGuideGUIController detailQuoteGuideGUIController = fxmlLoader.getController();

                detailQuoteGuideGUIController.setTripId(selectedTrip.getIdTrip());
                detailQuoteGuideGUIController.inizio(selectedTrip.getIdTrip());
                detailQuoteGuideGUIController.setCountry(country);
                detailQuoteGuideGUIController.setCategory(valore);


            }
            else if(!username.equals(selectedTrip.getGuide()) && !guideController){
            //if(!username.equals(selectedTrip.getGuide())){ //username != selectedTrip.getGuide()

                System.out.println("La utente corrente e l'organizzatore sono diversi ma l'utente loggato è un viaggiatore");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripDetailsQuote.fxml"));

                try{
                    Parent root = fxmlLoader.load();
                    Stage dialog = Main.getStage();
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                    dialog.show();

                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Eccezione nel caricamento nuova pagina");
                }

                DetailsQuoteGUIController detailsQuoteGUIController = fxmlLoader.getController();
                detailsQuoteGUIController.setTripId(selectedTrip.getIdTrip());
                detailsQuoteGUIController.inizio(selectedTrip.getIdTrip());
                detailsQuoteGUIController.setCountry(country);
                detailsQuoteGUIController.setCategory(valore);

            }else {
                System.out.println("La guida corrente e l'organizzatore sono uguali");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/EditTripDetails.fxml"));


                try {
                    Parent root = fxmlLoader.load();
                    Stage dialog = Main.getStage();
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                    dialog.show();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Eccezione nel caricamento nuova pagian");
                }

                EditTripDetailsGUIController editTripDetailsGUIController = fxmlLoader.getController();
                //editTripDetailsGUIController.setTripId(selectedTrip.getIdTrip());
                editTripDetailsGUIController.inizio(selectedTrip.getIdTrip());
                System.out.println("COUNTRY prima di passare a dettagli: "+ country);
                System.out.println("CATEGORY prima di passare a dettagli: "+ valore);
                editTripDetailsGUIController.setCountry(country);
                editTripDetailsGUIController.setCategory(valore);

            }
        }
    }

    public void homeAction() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void backAction() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel(country);

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void logoutAction() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }


}
