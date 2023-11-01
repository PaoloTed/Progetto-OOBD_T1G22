module com.example.libreriaonlinejava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.libreriaonlinejava to javafx.fxml;
    opens com.example.libreriaonlinejava.Model to javafx.fxml;
    opens com.example.libreriaonlinejava.Controller to javafx.fxml;
}