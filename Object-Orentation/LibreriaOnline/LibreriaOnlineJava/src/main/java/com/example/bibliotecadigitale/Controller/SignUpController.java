package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.example.bibliotecadigitale.Model.Utente.getUtente;

public class SignUpController {

    @FXML
    public TextField txtSignUpEmailField;
    @FXML
    public TextField txtSignUpPasswordField;

    private SupportStage support = new SupportStage();
    //SupportStage è una classe che contiene metodi che possono essere utilizzati da più controller
    // per gestire errori e cambiare scena

    public void back_goToWelcome(ActionEvent event) {
        support.switchStage("welcomeStage.fxml", event);
    }

    public void signUpTry(ActionEvent event) {
        String emailUser = txtSignUpEmailField.getText();
        String passwordUser = txtSignUpPasswordField.getText();
        txtSignUpEmailField.clear();
        txtSignUpPasswordField.clear();

        if (!support.checkEmailPassword(emailUser, passwordUser)) {
            support.messageStage("Inserire una email valida e/o una password valida");
            return;
        }
        //Controllare prima che non esista già un utente con la stessa email
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        Utente utente = utenteDAO.get(emailUser);
        //Se nessun utente ha la stessa email, inserire l'utente nel database
        if (utente == null) {
            utente = getUtente();
            utente.setEmail(emailUser);
            utente.setPassword(passwordUser);
            utenteDAO.save(utente);
            support.messageStage("Registrazione effettuata con successo");
            support.switchStage("welcomeStage.fxml", event);
        } else {
            //Se esiste già un utente con la stessa email, setto a false la variabile controlloEmail
            support.messageStage("Esiste gia un utente con la stessa email");
        }
    }
}

