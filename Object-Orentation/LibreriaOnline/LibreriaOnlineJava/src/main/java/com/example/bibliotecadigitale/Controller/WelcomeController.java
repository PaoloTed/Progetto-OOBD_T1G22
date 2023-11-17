package com.example.bibliotecadigitale.Controller;


import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.bibliotecadigitale.Model.Utente.getUtente;


public class WelcomeController implements Initializable {


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
    private Text txtUserRegistrati;

    @FXML
    private Text txtUserOnline;


    @FXML
    public void LogIn(ActionEvent PressLogin) {
        UtenteDAO utenteDAO = new UtenteDAO();
        String emailUser = txtEmailField.getText();
        String passwordUser = txtPasswordField.getText();
        if (!(support.checkEmailPassword(emailUser, passwordUser))) {return;}

        //Se l'email e la password rispettano i requisiti, controllare che l'utente sia presente nel database
        int rowsExsist = utenteDAO.getRowsExsistUtenteEmailPassword(emailUser, passwordUser);
        if (rowsExsist != 1) {
            support.messageStage("Email e/o password errate");
            return;
        }
        //Se l'utente è presente nel database, mostrare la sua home page
        Utente utenteNew = getUtente();
        utenteNew.setUtente(emailUser, passwordUser, utenteDAO.searchData(emailUser));
        support.switchStage("home.fxml", PressLogin, 900, 900);
    }

    public void goToSingUpUtente(ActionEvent PressSingUp) {
        support.switchStage("signUp.fxml", PressSingUp);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Connessione al database e controllo che la connessione sia avvenuta con successo
        Connessione connessione = new Connessione();
        String queryNumeroUtenti = "SELECT COUNT(*) FROM utente";
        String queryNumeroUtentiOnline = "SELECT count(*) FROM pg_stat_activity WHERE datname = 'bibliotecadigitaledb';";
        int numeroUtenti = 0;
        try {
            ResultSet rs = null;
            rs = connessione.executeSearch(queryNumeroUtentiOnline);
            while (rs.next()){
                numeroUtenti = rs.getInt(1) -1;
            }
            //TODO: capire bene perchè esce 2 utenti online quando sono solo io
            txtUserOnline.setText("" + numeroUtenti);
            rs.close();
             rs = connessione.executeSearch(queryNumeroUtenti);
            while (rs.next()){
                numeroUtenti = rs.getInt(1);
            }
            rs.close();
            txtUserRegistrati.setText("" + numeroUtenti);

        } catch (Exception e) {
            support.messageStage("Errore nel caricamento del numero di utenti");
        }

    }
}