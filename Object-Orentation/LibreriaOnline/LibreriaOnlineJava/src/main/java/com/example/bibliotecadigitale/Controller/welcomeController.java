package com.example.bibliotecadigitale.Controller;


import com.example.bibliotecadigitale.Model.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class welcomeController {

    @FXML
    private Button ButtonLoginIn;
    @FXML
    public Utente LoginUtente(ActionEvent PressLogin) {
        System.out.println("Login");
        return null;
    }
}