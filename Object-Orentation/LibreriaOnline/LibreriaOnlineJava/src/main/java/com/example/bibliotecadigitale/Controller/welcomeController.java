package com.example.bibliotecadigitale.Controller;


import com.example.bibliotecadigitale.Model.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class welcomeController {

    @FXML
    public Button ButtonLoginIn;
    //txtUsernameField è il fxid,(dentro code in scene builder) del campo di testo dove l'utente inserisce il suo username
    public TextField txtUsernameField;

    @FXML
    public Utente LoginUtente() {
        txtUsernameField.setText("ciao");
        System.out.println("Login");
        return null;
    }
}