package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.appcontroller.TableTripController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.RequestBean;
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
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class UpcomingTripsGUIController {

    @FXML
    private Button acceptButton;
    @FXML
    private Button declineButton;
    @FXML
    private Label titleLabel;
    @FXML
    private TableView<RequestBean> tableViewRequests;
    @FXML
    private TableColumn<RequestBean, String> nameColumn;
    @FXML
    private TableColumn<RequestBean, String> tripColumn;
    @FXML
    private TableColumn<RequestBean, String> cityColumn;



    private String username;
    private int idGuide;
    private boolean guideController = true;

    public void initialize(){
/*
        // Controlla il tipo di utente attualmente loggato
        Session session = Session.getCurrentSession();
        if(session != null) {
            if(session.getGuideBean() != null) {
                // È un utente Guida
                GuideBean guideBean = session.getGuideBean();
                username = guideBean.getName(); // Utilizza l'email anziché il nome
                idGuide = guideBean.getId();//
                System.out.println("Sono nella tabella upcoming come GUIDA:" + username);
                System.out.println("Sono nella tabella upcoming come GUIDAid:" + idGuide);
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

 */
    }


    public void inizio()throws IOException, NotFoundException{

        // Ottieni la lista delle richieste dal RequestDAO
        Session session = Session.getCurrentSession();
        GuideBean guideBean = session.getGuideBean();
        System.out.println("Sto chiamando il book trip controller\n");
        BookTripController bookTripController = new BookTripController();
        List<RequestBean> requestBeans = bookTripController.tableRequestsGuide(guideBean);

        // Imposta i valori delle colonne
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("idTrip")); // prima era idColumn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        tripColumn.setCellValueFactory(new PropertyValueFactory<>("travelerName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("travelerSurname"));

        // Popola la tabella con i viaggi
        tableViewRequests.getItems().addAll(requestBeans);

        // Disabilita il pulsanti all'avvio dell'applicazione
        acceptButton.setDisable(true);
        declineButton.setDisable(true);

        // Aggiungo un listener per la selezione delle righe nella tabella
        tableViewRequests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Abilita il pulsanti se una riga è selezionata
                acceptButton.setDisable(false);
                declineButton.setDisable(false);

            } else {
                // Disabilita il pulsante se nessuna riga è selezionata
                acceptButton.setDisable(true);
                declineButton.setDisable(true);
            }
        });
    }

    public void acceptAction() {

        int index = tableViewRequests.getSelectionModel().getSelectedIndex();

        RequestBean selectedRequest = tableViewRequests.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {

            BookTripController bookTripController = new BookTripController();
            bookTripController.acceptRequest(selectedRequest);
            tableViewRequests.getItems().remove(index);
            tableViewRequests.refresh();
        }
    }

    public void declineAction() {

        int index = tableViewRequests.getSelectionModel().getSelectedIndex();

        RequestBean selectedRequest = tableViewRequests.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {

            BookTripController bookTripController = new BookTripController();
            bookTripController.declineRequest(selectedRequest);
            tableViewRequests.getItems().remove(index);
            tableViewRequests.refresh();
        }
    }


    public void homeAction() throws IOException {

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageGuideMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void backAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/HomePageGuideMap.fxml"));
        Parent root = loader.load();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
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


}
