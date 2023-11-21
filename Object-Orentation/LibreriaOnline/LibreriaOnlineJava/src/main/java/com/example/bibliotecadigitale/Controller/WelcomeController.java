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
import javafx.scene.image.ImageView;
import javafx.scene.shape.Box;
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
    private ImageView imageWelcome;
    @FXML
    private ImageView imageWelcome2;
    private Box box1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String numeroUtenti = String.valueOf(getUserRegistered());
        String numeroUtentiOnline = String.valueOf(getUserOnline());
        imageWelcome.setImage(new Image("file:src/main/resources/photo/welcomeFoto1.png"));
        imageWelcome2.setImage(new Image("file:src/main/resources/photo/welcomeFoto2.png"));


        txtUserRegistrati.setText(numeroUtenti);
        txtUserOnline.setText(numeroUtentiOnline);
    }

    @FXML
    public void LogIn(ActionEvent PressLogin) {
        UtenteDAO utenteDAO = new UtenteDAO();
        String emailUser = txtEmailField.getText();
        String passwordUser = txtPasswordField.getText();
        txtEmailField.clear();
        txtPasswordField.clear();
        if (!(support.checkEmailPassword(emailUser, passwordUser))) {
            support.messageStage("Inserire una email valida e/o una password valida");
            return;
        }

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


    private int getUserRegistered() {
        String query = "SELECT COUNT(*) FROM utente";
        return executeQuerySearch(query);
    }

    private int getUserOnline() {
        String query = "SELECT count(*) FROM pg_stat_activity WHERE datname = 'bibliotecadigitaledb';";
        return executeQuerySearch(query);
    }

    private int executeQuerySearch(String query) {
        int numeroUtenti = 0;
        try {
            Connessione connessione = new Connessione();
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                numeroUtenti = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numeroUtenti;
    }
}