package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class welcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private Utente LoginUtente() {
        Utente UtenteLogin= new Utente();


        return UtenteLogin;
    }
}