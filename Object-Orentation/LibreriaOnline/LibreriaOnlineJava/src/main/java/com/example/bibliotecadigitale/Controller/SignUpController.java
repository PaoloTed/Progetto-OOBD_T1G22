package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.UtenteDAO;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;

public class SignUpController {

    @FXML
    public TextField txtSignUpEmailField;
    @FXML
    public TextField txtSignUpPasswordField;

    private SupportStage support = new SupportStage();
    //SupportStage è una classe che contiene metodi che possono essere utilizzati da più controller
    // per gestire errori e cambiare scena

    public void back_goToWelcome(ActionEvent event) {
        support.switchStage("welcome.fxml", event);
    }

    public void signUpTry(ActionEvent event) {
        String emailUser = txtSignUpEmailField.getText();
        String passwordUser = txtSignUpPasswordField.getText();
        txtSignUpEmailField.clear();
        txtSignUpPasswordField.clear();

        if (!support.checkEmailPassword(emailUser, passwordUser)) {
            return;
        }
        //Controllare prima che non esista già un utente con la stessa email
        UtenteDAO utenteDAO = new UtenteDAO();
        int rowExists = utenteDAO.getRowsExsistUtenteEmail(emailUser);
        //Se nessun utente ha la stessa email, inserire l'utente nel database
        if (rowExists == 0) {
            utenteDAO.insertUser(emailUser, passwordUser);
            support.messageStage("Registrazione effettuata con successo");
            support.switchStage("welcome.fxml", event);
        } else {
            //Se esiste già un utente con la stessa email, setto a false la variabile controlloEmail
            support.messageStage("Esiste gia un utente con la stessa email");
        }
    }
}

