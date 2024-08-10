package com.example.adventures.graphiccontroller;

import com.example.adventures.Main;
import com.example.adventures.appcontroller.LoginController;
import com.example.adventures.appcontroller.ViewTripDetailsController;
import com.example.adventures.bean.TravelerBean;
import com.example.adventures.engineering.Printer;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageTravelerMapGUIController{

    @FXML
    private Label australiaLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label indiaLable;
    @FXML
    private Label europeLabel;
    @FXML
    private Label peruLabel;

    @FXML
    private Label canadaLabel;
    @FXML
    private Label brasilLabel;
    @FXML
    private Label mexicoLabel;

    @FXML
    private Label argentinaLabel;
    @FXML
    private Label chileLabel;




    public void initialize(){
        try {
            TravelerBean travelerBean = Session.getCurrentSession().getTravelerBean();
            nameLabel.setText(travelerBean.getName());
        }catch (Exception e){
            Printer.printError(e.getMessage());
        }
    }

    private void handleMouseEnteredTraveler(String country, Label label) {

        ViewTripDetailsController viewTripDetailsController = new ViewTripDetailsController();
        int num = viewTripDetailsController.numberOfTrps(country);
        if(num == 1){
            label.setText(country + ": " + num + " avaiable trip");
        }else{
            label.setText(country + ": " + num + " avaiable trips");
        }
    }

    public void australiaMouseEntered(){

        handleMouseEnteredTraveler("Australia", australiaLabel);
    }

    public void indiaMouseEntered(){

        handleMouseEnteredTraveler("India", indiaLable);

    }

    public void spainMouseEntered(){

        handleMouseEnteredTraveler("Spain", europeLabel);

    }

    public void italyMouseEntered(){

        handleMouseEnteredTraveler("Italy", europeLabel);

    }

    public void brasilMouseEntered(){

        handleMouseEnteredTraveler("Brasil", brasilLabel);
    }

    public void argentinaMouseEntered(){

        handleMouseEnteredTraveler("Argentina", argentinaLabel);

    }

    public void peruMouseEntered(){

        handleMouseEnteredTraveler("Perù", peruLabel);

    }

    public void chileMouseEntered(){

        handleMouseEnteredTraveler("Chile", chileLabel);

    }

    public void usaMouseEntered(){

        handleMouseEnteredTraveler("USA", europeLabel);

    }

    public void mexicoMouseEntered(){

        handleMouseEnteredTraveler("Mexico", mexicoLabel);

    }

    public void indiaMouseExited(){
        indiaLable.setText("");
    }

    public void australiaMouseExited(){
        australiaLabel.setText("");
    }


    public void spainMouseExtited(){
        europeLabel.setText("");
    }

    public void italyMouseExtited(){
        europeLabel.setText("");
    }

    public void brasilMouseExited(){
        brasilLabel.setText("");
    }


    public void peruMouseExited(){
        peruLabel.setText("");
    }

    public void chileMouseExited(){
        chileLabel.setText("");
    }


    public void mexicoMouseExited(){
        mexicoLabel.setText("");
    }

    public void argentinaMouseExited(){
        argentinaLabel.setText("");
    }



    public void iusaMouseExited(){
        europeLabel.setText("");
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

        TravelerRequestsGUIController requestsGUIController = loader.getController();
        requestsGUIController.displayTravelerRequest();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();

    }

    public void upcomingAction() throws IOException, NotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpcomingTrips.fxml"));
        Parent root = loader.load();

        GuideRequestsGUIController upcomingTripsGUIController = loader.getController();
        upcomingTripsGUIController.displayGuideRequest();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();

    }
}
