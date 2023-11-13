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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        support.switchStage("welcome.fxml");
    }

    public void signUpTry(ActionEvent event) {
        //Connessione al database e controllo che la connessione sia avvenuta con successo
        Connection conn = Connessione.getConnection();
        if (conn != null) {
            //Se la connessione è avvenuta con successo, controllare che l'email rispetti la regex e che la password non sia vuota

            UtenteDAO utenteDAO = new UtenteDAO();
            String emailUser = txtSignUpEmailField.getText();
            String passwordUser = txtSignUpPasswordField.getText();
            if (support.checkEmailPassword(emailUser, passwordUser)) {
                //Controllare prima che non esista già un utente con la stessa email
                int rowExists = utenteDAO.getRowsExsistUtenteEmail(emailUser);
                //Se nessun utente ha la stessa email, inserire l'utente nel database
                if (rowExists == 0) {
                    utenteDAO.insertUser(emailUser, passwordUser);
                    support.messageStage("Registrazione effettuata con successo");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    support.switchStage("welcome.fxml");
                } else {
                    //Se esiste già un utente con la stessa email, setto a false la variabile controlloEmail
                    support.messageStage("Esiste gia un utente con la stessa email");
                    txtSignUpEmailField.clear();
                    txtSignUpPasswordField.clear();
                }
            }
        } else {
            //Se l'email non rispetta la regex e/o la password è vuota, mostrare un messaggio di errore
            support.messageStage("Connessione non effettuata con successo");

        }
    }
}

