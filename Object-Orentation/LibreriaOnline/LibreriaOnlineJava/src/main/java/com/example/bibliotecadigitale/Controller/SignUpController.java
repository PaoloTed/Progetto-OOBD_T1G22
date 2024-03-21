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
import java.time.Instant;
import java.time.ZoneOffset;
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

    public void back_goToWelcome(ActionEvent event) {
        support.switchStage("welcomeStage.fxml", event, 500, 500);
    }


    /**
     * Questo metodo permette di registrare un nuovo utente
     * Controlla che l'email e la password siano valide
     * Controlla che non esista già un utente con la stessa email
     * Se nessun utente ha la stessa email, inserisce l'utente nel database
     *
     * @param event
     */
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
        utente.setData(Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE));
        try {
            utenteDAO.insert(utente.ObjToArrayList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        support.messageStage("Registrazione effettuata con successo");
        support.switchStage("welcomeStage.fxml", event,500,500);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri500x900.png"))));
    }
}

