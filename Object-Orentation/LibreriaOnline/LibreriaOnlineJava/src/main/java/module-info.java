module com.example.libreriaonlinejava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bibliotecadigitale to javafx.fxml;
    exports com.example.bibliotecadigitale;
    exports com.example.bibliotecadigitale.Controller;
    opens com.example.bibliotecadigitale.Controller to javafx.fxml;
}