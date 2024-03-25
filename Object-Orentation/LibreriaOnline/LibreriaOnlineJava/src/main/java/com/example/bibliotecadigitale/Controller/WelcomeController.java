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
import java.sql.SQLException;
import java.util.ArrayList;
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

    /**
     * Questo metodo inizializza la pagina di benvenuto
     * Mostra il numero di utenti registrati e il numero di utenti online
     * Carica le immagini della schermata di benvenuto
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String numeroUtenti = String.valueOf(getUserRegistered());
        String numeroUtentiOnline = String.valueOf(getUserOnline());
        imageWelcome.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/welcomeFoto1.png"))));
        imageWelcome2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/welcomeFoto2.png"))));
        txtUserRegistrati.setText(numeroUtenti);
        txtUserOnline.setText(numeroUtentiOnline);
    }


    /**
     * Evento che si verifica quando si preme il tasto "Accedi"
     * Questo metodo controlla se l'utente è presente nel database e se la password è corretta
     * Se l'utente è presente, viene mostrata la sua home page
     * Se l'utente non è presente o la password è errata o la email non è correttamente formattata,
     * viene mostrato un messaggio di errore
     * @param PressLogin
     */
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
        Utente utente;
        try {
            ArrayList<String> utenteString = utenteDAO.get(emailUser);
            if (utenteString.isEmpty()) {
                support.messageStage("Email e/o password errate");
                return;
            }
            utente = new Utente(utenteString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!utente.getPassword().equals(passwordUser)) {
            support.messageStage("Email e/o password errate");
            return;
        }
        //Se l'utente è presente nel database, mostrare la sua home page
        Utente utenteNew = getUtente();
        utenteNew.setUtente(utente.getEmail(), utente.getPassword(), utente.getData());
        support.switchStage("homeStage.fxml", PressLogin, 900, 800);
    }

    public void goToSingUpUtente(ActionEvent PressSingUp) {
        support.switchStage("signUpStage.fxml", PressSingUp,500,500);
    }


    private int getUserRegistered() {
        Connessione connessione = new Connessione();
        return connessione.getNumeroUtenti();
    }

    private int getUserOnline() {
        Connessione connessione = new Connessione();
        return connessione.getNumeroOnline();
    }

    /**
     * Evento che si verifica quando si preme il tasto "Accedi come admin"
     * Questo metodo controlla se la password admin è corretta
     * Se la password è corretta, viene mostrata la home page dell'admin
     * @param event
     */
    @FXML
    private void goToAdmin(ActionEvent event) {
        if (!txtPasswordFieldAdmin.getText().equals("1926")) {
            support.messageStage("Password errata");
            return;
        }
        support.switchStage("homeStageAdmin.fxml", event, 900, 800);
    }
}