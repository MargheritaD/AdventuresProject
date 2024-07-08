package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.appcontroller.LoginController;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageTravelerMapGUIController{

    @FXML
    private Label nameLabel;
    @FXML
    private Label europeLabel;
    @FXML
    private Label indiaLable;
    @FXML
    private Label australiaLabel;
    @FXML
    private Label canadaLabel;
    @FXML
    private Label mexicoLabel;
    @FXML
    private Label peruLabel;
    @FXML
    private Label chileLabel;
    @FXML
    private Label brasilLabel;
    @FXML
    private Label argentinaLabel;


    public void initialize(){
        try {
            TravelerBean travelerBean = Session.getCurrentSession().getTravelerBean();
            nameLabel.setText(travelerBean.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleMouseEntered(String country, Label label) {

        BookTripController quoteController = new BookTripController();
        int num = quoteController.numberOfTrps(country);
        if(num == 1){
            label.setText(country + ": " + num + "avaiable trip");
        }else{
            label.setText(country + ": " + num + " avaiable trips");
        }
    }

    public void australiaMouseEntered(){

        handleMouseEntered("Australia", australiaLabel);
    }

    public void indiaMouseEntered(){

        handleMouseEntered("India", indiaLable);

    }

    public void spainMouseEntered(){

        handleMouseEntered("Spain", europeLabel);

    }

    public void italyMouseEntered(){

        handleMouseEntered("Italy", europeLabel);

    }

    public void brasilMouseEntered(){

        handleMouseEntered("Brasil", brasilLabel);
    }

    public void argentinaMouseEntered(){

        handleMouseEntered("Argentina", argentinaLabel);

    }

    public void peruMouseEntered(){

        handleMouseEntered("Perù", peruLabel);

    }

    public void chileMouseEntered(){

        handleMouseEntered("Chile", chileLabel);

    }

    public void usaMouseEntered(){

        handleMouseEntered("USA", europeLabel);

    }

    public void mexicoMouseEntered(){

        handleMouseEntered("Mexico", mexicoLabel);

    }

    public void australiaMouseExited(){
        australiaLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void indiaMouseExited(){
        indiaLable.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void spainMouseExtited(){
        europeLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void italyMouseExtited(){
        europeLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void brasilMouseExited(){
        brasilLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void argentinaMouseExited(){
        argentinaLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void peruMouseExited(){
        peruLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void chileMouseExited(){
        chileLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void iusaMouseExited(){
        europeLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    public void mexicoMouseExited(){
        mexicoLabel.setText(""); // Imposta il testo della label su una stringa vuota
    }

    private void loadCategoryView(String country) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController = loader.getController();
        categoryGUIController.setCountryLabel(country);

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }



    public void australiaAction() throws IOException {

        loadCategoryView("Australia");

    }

    public void indiaAction() throws IOException {

        loadCategoryView("India");

    }

    public void spainAction() throws IOException {

        loadCategoryView("Spain");

    }

    public void italyAction() throws IOException {

        loadCategoryView("Italy");

    }

    public void brasilAction() throws IOException {

        loadCategoryView("Brasil");

    }

    public void argentinaAction() throws IOException {

        loadCategoryView("Argentina");

    }

    public void peruAction() throws IOException {

        loadCategoryView("Perù");

    }

    public void chileAction() throws IOException {

        loadCategoryView("Chile");

    }

    public void usaAction() throws IOException {

        loadCategoryView("USA");

    }

    public void mexicoAction() throws IOException {

        loadCategoryView("Mexico");

    }


    public void newTripAction() throws IOException {
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("NewTrip.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void exitAction() throws IOException{

        LoginController loginController = new LoginController();
        loginController.logout();

        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void requestsAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/RequestStatus.fxml"));
        Parent root = loader.load();

        RequestsGUIController requestsGUIController = loader.getController();
        requestsGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();

    }

    public void upcomingAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpcomingTrips.fxml"));
        Parent root = loader.load();

        UpcomingTripsGUIController upcomingTripsGUIController = loader.getController();
        upcomingTripsGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();

    }
}
