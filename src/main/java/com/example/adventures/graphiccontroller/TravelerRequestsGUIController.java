package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.RequestBean;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.bean.TripBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TravelerRequestsGUIController {

    @FXML
    private TableView<RequestBean> tableViewRequests;
    @FXML
    private TableColumn<TripBean, String> nameColumn;
    @FXML
    private TableColumn<TripBean, String> guideColumn;
    @FXML
    private TableColumn<TripBean, String> statusColumn;
    @FXML
    private TableColumn<TripBean, Integer> idColumn;

    public void displayTravelerRequest()throws IOException, NotFoundException{

        // Ottieni la lista delle richieste dal RequestDAO
        Session session = Session.getCurrentSession();
        TravelerBean travelerBean = session.getTravelerBean();
        BookTripController bookTripController = new BookTripController();
        List<RequestBean> requestBeans = bookTripController.listRequestTraveler(travelerBean);

        // Imposta i valori delle colonne
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("idTrip")); // prima era idColumn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tripName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusString"));

        // Popola la tabella con i viaggi
        tableViewRequests.getItems().addAll(requestBeans);

    }


    public void homeAction() throws IOException {

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePageTravelerMap.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void backAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/HomePageTravelerMap.fxml"));
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
