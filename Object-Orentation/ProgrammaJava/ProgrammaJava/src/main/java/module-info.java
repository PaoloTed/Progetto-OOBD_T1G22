module com.example.programmajava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programmajava to javafx.fxml;
    exports com.example.programmajava;
}