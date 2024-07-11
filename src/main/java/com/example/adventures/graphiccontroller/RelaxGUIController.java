package com.example.adventures.graphiccontroller;


import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.Main;
import com.example.adventures.bean.CountryCategoryBean;
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


    public void setTitle(String valore) {
        this.valore = valore;
        titleLabel.setText(valore);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void inizio()throws IOException, NotFoundException{
        // Ottieni la lista dei viaggi dal TripDAO

        BookTripController bookTripController= new BookTripController();
        CountryCategoryBean countryCategoryBean = new CountryCategoryBean(country,valore);
        List<TripBean> tripBeans = bookTripController.selectCountryAndCategory(countryCategoryBean);

        // Imposta i valori delle colonne
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idTrip")); // prima era idColumn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        outboundColumn.setCellValueFactory(new PropertyValueFactory<>("outboundDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        // Popola la tabella con i viaggi
        tableViewTrips.getItems().addAll(tripBeans);

        // Disabilita il pulsante all'avvio dell'applicazione
        detailsButton.setDisable(true);


        // Aggiungo un listener per la selezione delle righe nella tabella
        tableViewTrips.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
                detailsButton.setDisable(newSelection == null)
        );

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
            } else if(session.getTravelerBean() != null) {
                // È un utente Viaggiatore
                TravelerBean travelerBean = session.getTravelerBean();
                username = travelerBean.getName(); // Utilizza l'email anche per i viaggiatori
                guideController = false;
            } else {
                // Tipo di utente non riconosciuto
            }
        } else {
            // Sessione non valida
        }
    }

    public void detailsAction() throws IOException, NotFoundException {

        TripBean selectedTrip = tableViewTrips.getSelectionModel().getSelectedItem();
        System.out.println("details action - relaxGUI + id " +selectedTrip.getIdTrip());
        System.out.println("details action - relaxGUI + name " +selectedTrip.getTripName());
        System.out.println("details action - relaxGUI + price " +selectedTrip.getPrice());
        System.out.println("details action - relaxGUI + country " +selectedTrip.getCountry());
        System.out.println("details action - relaxGUI + category " +selectedTrip.getCategory());
        System.out.println("details action - relaxGUI + city " +selectedTrip.getDepartureCity());
        System.out.println("details action - relaxGUI + outbound " +selectedTrip.getOutboundDate());
        System.out.println("details action - relaxGUI + return " +selectedTrip.getReturnDate());

        if (selectedTrip != null) {

            System.out.println("VIAGGIO é STATO SELEZIONATO");

            BookTripController bookTripController = new BookTripController();
            TripBean detailedTripBean = bookTripController.getTripDetails(selectedTrip);

            System.out.println("details action1 - relaxGUI + id " +detailedTripBean.getIdTrip());
            System.out.println("details action1 - relaxGUI + name " +detailedTripBean.getTripName());
            System.out.println("details action1 - relaxGUI + price " +detailedTripBean.getPrice());
            System.out.println("details action1 - relaxGUI + country " +detailedTripBean.getCountry());
            System.out.println("details action1 - relaxGUI + category " +detailedTripBean.getCategory());
            System.out.println("details action1 - relaxGUI + city " +detailedTripBean.getDepartureCity());
            System.out.println("details action1 - relaxGUI + outbound " +detailedTripBean.getOutboundDate());
            System.out.println("details action1 - relaxGUI + return " +detailedTripBean.getReturnDate());

            if(!username.equals(detailedTripBean.getGuide())  && guideController){
//if(!username.equals(selectedTrip.getGuide())  && guideController){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripDetailsQuoteGuide.fxml"));

                try{
                    Parent root = fxmlLoader.load();
                    Stage dialog = Main.getStage();
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                    dialog.show();

                }catch (IOException e) {
                    e.printStackTrace();
                }

                DetailQuoteGuideGUIController detailQuoteGuideGUIController = fxmlLoader.getController();

                detailQuoteGuideGUIController.setTripId(selectedTrip.getIdTrip());
                System.out.println("Un attimo prima di passare selected trip " + selectedTrip);
                detailQuoteGuideGUIController.inizio(detailedTripBean);
                //detailQuoteGuideGUIController.inizio(selectedTrip.getIdTrip());
                detailQuoteGuideGUIController.setCountry(country);
                detailQuoteGuideGUIController.setCategory(valore);


            }
            else if(!username.equals(selectedTrip.getGuide()) && !guideController){

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/TripDetailsQuote.fxml"));

                try{
                    Parent root = fxmlLoader.load();
                    Stage dialog = Main.getStage();
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                    dialog.show();

                }catch (IOException e) {
                    e.printStackTrace();
                }

                DetailsQuoteGUIController detailsQuoteGUIController = fxmlLoader.getController();
                detailsQuoteGUIController.setTripId(selectedTrip.getIdTrip());
                detailsQuoteGUIController.inizio(selectedTrip.getIdTrip());
                detailsQuoteGUIController.setCountry(country);
                detailsQuoteGUIController.setCategory(valore);

            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/adventures/EditTripDetails.fxml"));


                try {
                    Parent root = fxmlLoader.load();
                    Stage dialog = Main.getStage();
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                    dialog.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                EditTripDetailsGUIController editTripDetailsGUIController = fxmlLoader.getController();
                editTripDetailsGUIController.inizio(selectedTrip.getIdTrip());
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
