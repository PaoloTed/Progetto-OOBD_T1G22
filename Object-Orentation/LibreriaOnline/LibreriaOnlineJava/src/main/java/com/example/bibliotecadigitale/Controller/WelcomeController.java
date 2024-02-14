package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static com.example.bibliotecadigitale.Model.Utente.getUtente;

public class WelcomeController implements Initializable {
    @FXML
    public Button ButtonSingUp;
    @FXML
    public Button ButtonLoginIn;
    @FXML
    public TextField txtEmailField;

    @FXML
    public TextField txtPasswordField;

    private final SupportStage support = new SupportStage();

    @FXML
    private Text txtUserRegistrati;

    @FXML
    private Text txtUserOnline;

    @FXML
    private ImageView imageWelcome;
    @FXML
    private ImageView imageWelcome2;

    @FXML
    private TextField txtPasswordFieldAdmin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String numeroUtenti = String.valueOf(getUserRegistered());
        String numeroUtentiOnline = String.valueOf(getUserOnline());
        imageWelcome.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/welcomeFoto1.png"))));
        imageWelcome2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/welcomeFoto2.png"))));
        txtUserRegistrati.setText(numeroUtenti);
        txtUserOnline.setText(numeroUtentiOnline);
    }

    @FXML
    private void LogIn(ActionEvent PressLogin) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        String emailUser = txtEmailField.getText();
        String passwordUser = txtPasswordField.getText();
        txtEmailField.clear();
        txtPasswordField.clear();
        if (!(support.checkEmailPassword(emailUser, passwordUser))) {
            support.messageStage("Inserire una email valida e/o una password valida");
            return;
        }

        //Se l'email e la password rispettano i requisiti, controllare che l'utente sia presente nel database
        Utente utente = utenteDAO.get(emailUser);
        if (utente == null || !utente.getPassword().equals(passwordUser)) {
            support.messageStage("Email e/o password errate");
            return;
        }
        //Se l'utente è presente nel database, mostrare la sua home page
        Utente utenteNew = getUtente();
        utenteNew.setUtente(emailUser, passwordUser, utenteDAO.get(emailUser).getData());
        support.switchStage("homeStage.fxml", PressLogin, 900, 800);
    }

    public void goToSingUpUtente(ActionEvent PressSingUp) {
        support.switchStage("signUpStage.fxml", PressSingUp);
    }


    private int getUserRegistered() {
        Connessione connessione = new Connessione();
        return connessione.getNumeroUtenti();
    }

    private int getUserOnline() {
        Connessione connessione = new Connessione();
        return connessione.getNumeroOnline();
    }

    @FXML
    private void skip(ActionEvent event) {
        Utente utenteNew = getUtente();
        utenteNew.setEmail("giulio@ruopolo.it");
        utenteNew.setPassword("salernitana");
        utenteNew.setData("2002-10-05");
        support.switchStage("homeStage.fxml", event, 900, 800);
    }

    @FXML
    private void goToAdmin(ActionEvent event) {
        if (!txtPasswordFieldAdmin.getText().equals("1926")) {
            support.messageStage("Password errata");
            return;
        }
        support.switchStage("homeStageAdmin.fxml", event, 900, 800);
    }
}