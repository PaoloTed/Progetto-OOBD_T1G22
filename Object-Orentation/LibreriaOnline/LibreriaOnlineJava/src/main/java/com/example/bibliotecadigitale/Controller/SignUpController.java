package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.bibliotecadigitale.Model.Utente.getUtente;

public class SignUpController implements Initializable {

    @FXML
    public TextField txtSignUpEmailField;
    @FXML
    public TextField txtSignUpPasswordField;

    @FXML
    private ImageView imageLibriSfondo;

    private final SupportStage support = new SupportStage();
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
        try {
            if (utenteDAO.esisteUtente(emailUser)) {
                support.messageStage("Esiste gia un utente con la stessa email");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Se nessun utente ha la stessa email, inserire l'utente nel database
        Utente utente = getUtente();
        utente.setEmail(emailUser);
        utente.setPassword(passwordUser);
        utente.setData(LocaleDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        try {
            utenteDAO.insert(utente.objToArrayList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        support.messageStage("Registrazione effettuata con successo");
        support.switchStage("welcomeStage.fxml", event);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri500x900.png"))));
    }
}

