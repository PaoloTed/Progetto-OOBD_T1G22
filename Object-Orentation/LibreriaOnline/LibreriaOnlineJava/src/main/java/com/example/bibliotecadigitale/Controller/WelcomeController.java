package com.example.bibliotecadigitale.Controller;


import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;

import static com.example.bibliotecadigitale.Model.Utente.getUtente;


public class WelcomeController {


    @FXML
    public Button ButtonSingUp;
    @FXML
    public Button ButtonLoginIn;
    //txtEmailField è il fxid, (dentro code in scene builder) del campo di testo dove l'utente inserisce il suo username sadasd
    @FXML
    public TextField txtEmailField;

    @FXML
    public TextField txtPasswordField;

    private SupportStage support = new SupportStage();


    @FXML
    public void LogIn(ActionEvent PressLogin) {
        //Connessione al database e controllo che la connessione sia avvenuta con successo

        Connection conn = Connessione.getConnection();
        if (conn != null) {
            //Se la connessione è avvenuta con successo, controllare che l'email rispetti la regex e che la password non sia vuota
            UtenteDAO utenteDAO = new UtenteDAO();
            String emailUser = txtEmailField.getText();
            String passwordUser = txtPasswordField.getText();

            if (support.checkEmailPassword(emailUser, passwordUser)) {
                //Se l'email e la password rispettano i requisiti, controllare che l'utente sia presente nel database
                int rowsExsist = utenteDAO.getRowsExsistUtenteEmailPassword(emailUser, passwordUser);
                if (rowsExsist != 0) {
                    //Se l'utente è presente nel database, mostrare la sua home page
                    //TODO: mostrare la home page dell'utente
                    System.out.println(emailUser);
                    System.out.println(passwordUser);
                    support.messageStage( "Ciao " + emailUser + "!");

                    //Log in utente e setto i suoi dati nella classe Utente, istanza statica unica nel programma
                    Utente utenteNew = getUtente();
                    utenteNew.setUtente(emailUser, passwordUser,utenteDAO.searchData(emailUser));
                } else {
                    //Se l'utente non è presente nel database, mostrare un messaggio di errore
                    support.messageStage("Email e/o password errate");
                }
            } else {
                //Se l'email e/o la password non rispettano i requisiti, mostrare un messaggio di errore
                support.messageStage("Inserire una email e/o password valida");
            }
        } else {
            //Se la connessione non è avvenuta con successo, mostrare un messaggio di errore
            support.messageStage("Connessione non effettuata con successo");
            System.out.println("Connessione non effettuata con successo");
        }
    }

    public void goToSingUpUtente(ActionEvent PressSingUp) {
        Stage stage = (Stage) ((Node) PressSingUp.getSource()).getScene().getWindow();
        stage.close();
        support.switchStage("signUp.fxml");
    }


}