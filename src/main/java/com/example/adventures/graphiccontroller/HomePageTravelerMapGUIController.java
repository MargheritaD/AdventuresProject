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


    String avaiable = " avaiable trips";
    String relaxPath = "/com/example/adventures/Category.fxml";

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
            label.setText(country + ": " + num + avaiable);
        }
    }

    public void australiaMouseEntered(){

        handleMouseEntered("Australia", australiaLabel);
/*
        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Australia");
        if(num == 1){
            australiaLabel.setText("Australia: "+ num + avaiable);
        }else{
            australiaLabel.setText("Australia: "+ num + avaiable);
        }*/
    }

    public void indiaMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("India");
        if(num == 1) {
            indiaLable.setText("India: " + num + avaiable);
        }else {
            indiaLable.setText("India: " + num + avaiable);
        }
    }

    public void spainMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Spain");
        if(num == 1){
            europeLabel.setText("Spain: "+ num + avaiable);
        }else {
            europeLabel.setText("Spain: "+ num + avaiable);
        }
    }

    public void italyMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Italy");
        if(num == 1) {
            europeLabel.setText("Italy: " + num + avaiable);
        }else {
            europeLabel.setText("Italy: " + num + avaiable);
        }
    }

    public void brasilMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Brasil");
        if(num == 1){
            brasilLabel.setText("Brasil: " + num + avaiable);
        }else {
            brasilLabel.setText("Brasil: " + num + avaiable);
        }
    }

    public void argentinaMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Argentina");
        if(num == 1){
            argentinaLabel.setText("Argentina: "+ num + avaiable);
        }else{
            argentinaLabel.setText("Argentina: "+ num + avaiable);
        }
    }

    public void peruMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Perù");
        if (num == 1) {
            peruLabel.setText("Perù: "+ num + avaiable);
        }else{
            peruLabel.setText("Perù: "+ num + avaiable);
        }
    }

    public void chileMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Chile");
        if (num == 1){
            chileLabel.setText("Chile: "+ num + avaiable);
        }else {
            chileLabel.setText("Chile: " + num + avaiable);
        }
    }

    public void usaMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("USA");
        if(num == 1){
            europeLabel.setText("USA: "+ num + avaiable);
        }else{
            europeLabel.setText("USA: "+ num + avaiable);
        }

    }

    public void mexicoMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Mexico");
        if(num == 1){
            mexicoLabel.setText("Mexico: "+ num + avaiable);
        }else{
            mexicoLabel.setText("Mexico: "+ num + avaiable);
        }

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

    public void australiaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Australia");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void indiaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("India");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void spainAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Spain");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void italyAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Italy");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void brasilAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Brasil");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void argentinaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Argentina");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void peruAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Perù");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void chileAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Chile");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void usaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("USA");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void mexicoAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(relaxPath));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Mexico");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
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

    public void requestsAction() throws IOException{
        Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("RequestStatus.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void upcomingAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpcomingTrips.fxml"));
        Parent root = loader.load();

        UpcomingTripsGUIController upcomingTripsGUIController = loader.getController();
        //relaxGUIController.setTitle(category);

        //relaxGUIController.setCountry(country);

        //upcomingTripsGUIController.initialize();
        upcomingTripsGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();


    }



}
