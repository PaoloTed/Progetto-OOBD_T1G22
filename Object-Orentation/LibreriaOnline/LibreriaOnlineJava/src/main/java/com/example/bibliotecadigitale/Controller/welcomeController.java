package com.example.bibliotecadigitale.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class welcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}