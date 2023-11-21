module com.example.libreriaonlinejava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    exports com.example.bibliotecadigitale.Controller;
    opens com.example.bibliotecadigitale.Controller to javafx.fxml;
    exports com.example.bibliotecadigitale;
    opens com.example.bibliotecadigitale to javafx.fxml;
}