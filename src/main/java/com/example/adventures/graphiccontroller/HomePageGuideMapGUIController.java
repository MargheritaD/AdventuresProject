package com.example.adventures.graphiccontroller;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.Main;
import com.example.adventures.appcontroller.LoginController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.RequestBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class HomePageGuideMapGUIController {
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
    @FXML
    private ImageView bellImage;



/*
    private int numberOfTrps(String country){

        //int number = TripDAO.getNumberTripsByCountry(country);

        return number;
    }*/

    public void initialize(){

        GuideBean guideBean = Session.getCurrentSession().getGuideBean();

        nameLabel.setText(guideBean.getName());
        BookTripController bookTripController = new BookTripController();
        boolean bell = bookTripController.bell(guideBean);
        System.out.println("TRUE OR FALSE: "+bell);

        if(bell){
            URL resource = getClass().getResource("/com/example/adventures/image/yellowBell.png");
            if (resource != null) {
                String imageUrl = resource.toExternalForm();
                System.out.println("Dopo string url\n");
                System.out.println("URL " + imageUrl);
                Image newImage = new Image(imageUrl);
                bellImage.setImage(newImage);
            } else {
                System.out.println("Immagine non trovata: /image/yellowBell.png");
            }
        }else {
            URL resource = getClass().getResource("/com/example/adventures/image/emptyBell.png");
            if (resource != null) {
                String imageUrl = resource.toExternalForm();
                System.out.println("Dopo string url\n");
                System.out.println("URL " + imageUrl);
                Image newImage = new Image(imageUrl);
                bellImage.setImage(newImage);
            } else {
                System.out.println("Immagine non trovata: /image/emptyBell.png");
            }
        }




       /* BookTripController bookTripController = new BookTripController();
        List <RequestBean> requestList = bookTripController.tableRequestsGuide(guideBean);

        if(){
        }

            CI VA IL CONTROLLO PER VEDERE SE CI SONO NUOVE RICHIESTE.
            SI -> YELLOWBELL -> PULSANTE ATTIVO
            NO -> EMPTYBELL  -> PULSANTE NON ATTIVO
         */

        /*

        URL resource = getClass().getResource("/com/example/adventures/image/emptyBell.png");
        if (resource != null) {
            String imageUrl = resource.toExternalForm();
            System.out.println("Dopo string url\n");
            System.out.println("URL " + imageUrl);
            Image newImage = new Image(imageUrl);
            bellImage.setImage(newImage);
        } else {
            System.out.println("Immagine non trovata: /image/yellowBell.png");
        }

         */
    }

    public void toRequestButton() throws IOException, NotFoundException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/UpcomingTrips.fxml"));
        Parent root = loader.load();

        UpcomingTripsGUIController upcomingTripsGUIController = loader.getController();
        //relaxGUIController.setTitle(category);

        //relaxGUIController.setCountry(country);
        upcomingTripsGUIController.initialize();
        upcomingTripsGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
    public void australiaMouseEntered(){

        URL resource = getClass().getResource("/com/example/adventures/image/yellowBell.png");
        if (resource != null) {
            String imageUrl = resource.toExternalForm();
            System.out.println("Dopo string url\n");
            System.out.println("URL " + imageUrl);
            Image newImage = new Image(imageUrl);
            bellImage.setImage(newImage);
        } else {
            System.out.println("Immagine non trovata: /image/yellowBell.png");
        }

        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("Australia");
        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Australia");
        if(num == 1){
            australiaLabel.setText("Australia: "+ num + " avaiable trip");
        }else{
            australiaLabel.setText("Australia: "+ num + " avaiable trips");
        }
    }

    public void indiaMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num = quoteController.numberOfTrps("India");
        if(num == 1) {
            indiaLable.setText("India: " + num + " avaiable trip");
        }else {
            indiaLable.setText("India: " + num + " avaiable trips");
        }
    }

    public void spainMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Spain");
        if(num == 1){
            europeLabel.setText("Spain: "+ num + " avaiable trip");
        }else {
            europeLabel.setText("Spain: "+ num + " avaiable trips");
        }
    }

    public void italyMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Italy");
        if(num == 1) {
            europeLabel.setText("Italy: " + num + " avaiable trip");
        }else {
            europeLabel.setText("Italy: " + num + " avaiable trips");
        }
    }

    public void brasilMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Brasil");
        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("Brasil");
        if(num == 1){
            brasilLabel.setText("Brasil: " + num + " avaiable trip");
        }else {
            brasilLabel.setText("Brasil: " + num + " avaiable trips");
        }
    }

    public void argentinaMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Argentina");
        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("Argentina");
        if(num == 1){
            argentinaLabel.setText("Argentina: "+ num + " avaiable trip");
        }else{
            argentinaLabel.setText("Argentina: "+ num + " avaiable trips");
        }
    }

    public void peruMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Perù");
        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("Perù");
        if (num == 1) {
            peruLabel.setText("Perù: "+ num + " avaiable trip");
        }else{
            peruLabel.setText("Perù: "+ num + " avaiable trips");
        }
    }

    public void chileMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Chile");
        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("Chile");
        if (num == 1){
            chileLabel.setText("Chile: "+ num + " avaiable trip");
        }else {
            chileLabel.setText("Chile: " + num + " avaiable trips");
        }
    }

    public void usaMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("USA");
        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("USA");
        if(num == 1){
            europeLabel.setText("USA: "+ num + " avaiable trip");
        }else{
            europeLabel.setText("USA: "+ num + " avaiable trips");
        }

    }

    public void mexicoMouseEntered(){

        BookTripController quoteController = new BookTripController();
        int num =  quoteController.numberOfTrps("Mexico");
        //HomePageGuideMapController homePageGuideController = new HomePageGuideMapController();
        //int num = homePageGuideController.numberOfTrps("Mexico");
        if(num == 1){
            mexicoLabel.setText("Mexico: "+ num + " avaiable trip");
        }else{
            mexicoLabel.setText("Mexico: "+ num + " avaiable trips");
        }

    }

    public void australiaMouseExited(){
        URL resource = getClass().getResource("/com/example/adventures/image/emptyBell.png");
        if (resource != null) {
            String imageUrl = resource.toExternalForm();
            System.out.println("Dopo string url\n");
            System.out.println("URL " + imageUrl);
            Image newImage = new Image(imageUrl);
            bellImage.setImage(newImage);
        } else {
            System.out.println("Immagine non trovata: /image/yellowBell.png");
        }

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

    public void canadaMouseExited(){
        mexicoLabel.setText("");}

    public void MouseExited(){
        mexicoLabel.setText("");}

    public void australiaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Australia");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void indiaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("India");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void spainAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Spain");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void italyAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Italy");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void brasilAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Brasil");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void argentinaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Argentina");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void peruAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Perù");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void chileAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("Chile");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void usaAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
        Parent root = loader.load();

        CategoryGUIController categoryGUIController  = loader.getController();
        categoryGUIController.setCountryLabel("USA");

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void mexicoAction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/Category.fxml"));
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
        Parent root;
        Stage dialog = Main.getStage();

        LoginController loginController = new LoginController();
        loginController.logout();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void upcomingAction() throws IOException, NotFoundException {
        /*Parent root;
        Stage dialog = Main.getStage();

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("UpcomingTrips.fxml")));

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();*/


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/adventures/UpcomingTrips.fxml"));
        Parent root = loader.load();

        UpcomingTripsGUIController upcomingTripsGUIController = loader.getController();
        //relaxGUIController.setTitle(category);

        //relaxGUIController.setCountry(country);
        upcomingTripsGUIController.initialize();
        upcomingTripsGUIController.inizio();

        Stage dialog = Main.getStage();
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
}
