module com.example.adventures {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;


    opens com.example.adventures to javafx.fxml;
    exports com.example.adventures;
    exports com.example.adventures.graphiccontroller;
    opens com.example.adventures.graphiccontroller to javafx.fxml;
    opens com.example.adventures.bean to javafx.base;

}