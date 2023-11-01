module com.example.libreriaonlinejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.libreriaonlinejava to javafx.fxml;
    exports com.example.libreriaonlinejava;
    exports com.example.libreriaonlinejava.Controller;
    opens com.example.libreriaonlinejava.Controller to javafx.fxml;
}