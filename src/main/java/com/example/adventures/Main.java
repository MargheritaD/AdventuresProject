package com.example.adventures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;
    private static Stage secondaryStage;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1315, 780);
        setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage stage){
        Main.stage = stage;
    }

    public static Stage getSecondaryStage(){
        return secondaryStage;
    }

    public void setSecondaryStage(Stage secondaryStage){
        Main.secondaryStage = secondaryStage;
    }

}