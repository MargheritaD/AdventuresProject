package com.example.adventures.graphiccontroller;


import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.Main;
import com.example.adventures.bean.CountryCategoryBean;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.engineering.Printer;
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

    public void detailsAction() throws NotFoundException {
        TripBean selectedTrip = tableViewTrips.getSelectionModel().getSelectedItem();

        if (selectedTrip != null) {
            handleTripDetails(selectedTrip);
        }
    }

    private void handleTripDetails(TripBean selectedTrip) throws NotFoundException {
        BookTripController bookTripController = new BookTripController();
        TripBean detailedTripBean = bookTripController.getTripDetails(selectedTrip);

        if (!username.equals(detailedTripBean.getGuide()) && guideController) {
            showQuoteGuideView(selectedTrip, detailedTripBean);
        } else if (!username.equals(selectedTrip.getGuide()) && !guideController) {
            showQuoteView(selectedTrip, detailedTripBean);
        } else {
            showEditTripDetailsView(detailedTripBean);
        }
    }

    private void showQuoteGuideView(TripBean selectedTrip, TripBean detailedTripBean) {
        String resource = "/com/example/adventures/TripDetailsQuoteGuide.fxml";
        showDetailView(resource, selectedTrip, detailedTripBean);
    }

    private void showQuoteView(TripBean selectedTrip, TripBean detailedTripBean) {
        String resource = "/com/example/adventures/TripDetailsQuote.fxml";
        showDetailView(resource, selectedTrip, detailedTripBean);
    }

    private void showEditTripDetailsView(TripBean detailedTripBean) {
        String resource = "/com/example/adventures/EditTripDetails.fxml";
        showDetailView(resource, null, detailedTripBean); // Not passing selectedTrip here
    }

    private void showDetailView(String resource, TripBean selectedTrip, TripBean detailedTripBean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));

        try {
            Parent root = fxmlLoader.load();
            Stage dialog = Main.getStage();
            Scene scene = new Scene(root);
            dialog.setScene(scene);
            dialog.show();

            if (resource.equals("/com/example/adventures/TripDetailsQuoteGuide.fxml")) {
                DetailQuoteGuideGUIController controller = fxmlLoader.getController();
                controller.setTripId(selectedTrip.getIdTrip());
                controller.inizio(detailedTripBean);
                controller.setCountry(country);
                controller.setCategory(valore);
            } else if (resource.equals("/com/example/adventures/TripDetailsQuote.fxml")) {
                DetailsQuoteGUIController controller = fxmlLoader.getController();
                controller.setTripId(selectedTrip.getIdTrip());
                controller.inizio(detailedTripBean);
                controller.setCountry(country);
                controller.setCategory(valore);
            } else if (resource.equals("/com/example/adventures/EditTripDetails.fxml")) {
                EditTripDetailsGUIController controller = fxmlLoader.getController();
                controller.inizio(detailedTripBean);
                controller.setCountry(country);
                controller.setCategory(valore);
            }

        } catch (IOException | NotFoundException e) {
            Printer.printError(e.getMessage());
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
