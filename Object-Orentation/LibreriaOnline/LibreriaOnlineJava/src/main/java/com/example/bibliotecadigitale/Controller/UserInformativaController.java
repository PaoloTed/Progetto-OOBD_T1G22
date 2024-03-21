package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.SerieDAOImpl;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserInformativaController implements Initializable {
    @FXML
    public Label labelEmail;
    @FXML
    public PasswordField txtOldPassword;
    @FXML
    public PasswordField txtNewPassword;

    @FXML
    public TableView<Serie> tableView;

    @FXML
    TableColumn<Serie, String> codsColumn;
    @FXML
    TableColumn<Serie, String> nomeColumn;
    @FXML
    TableColumn<Serie, Integer> numlibriColumn;
    @FXML
    TableColumn<Serie, Boolean> completataColumn;

    @FXML
    private ImageView imageLibriSfondo;

    //Supporto per la gestione delle finestre
    private final SupportStage support = new SupportStage();

    @Override
    //Inizializza la pagina con i dati dell'utente e le serie preferite
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Mostro l'email dell'utente
        String email = Utente.getUtente().getEmail();
        labelEmail.setText(email);
        codsColumn.setCellValueFactory(new PropertyValueFactory<>("cods"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        numlibriColumn.setCellValueFactory(new PropertyValueFactory<>("numlibri"));
        completataColumn.setCellValueFactory(new PropertyValueFactory<>("completata"));
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
        setTableView(email);
    }

    public void setTableView(String email) {
        SerieDAOImpl serieDAO = new SerieDAOImpl();
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();

        ArrayList<Integer> codPreferiti = null;
        try {
            codPreferiti = utenteDAO.searchPreferiti(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Recupero le serie preferite dell'utente
        //Mostro le serie preferite dell'utente nella listView
        for (Integer codicePreferito : codPreferiti) {
            //Viene inserito nella listView il codice della serie e il suo nome
            try {
                tableView.getItems().add(new Serie(serieDAO.get(codicePreferito)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metodo che permette di cambiare la password dell'utente
     * Controlla che la vecchia password sia corretta e che la nuova password rispetti i requisiti
     * Se la vecchia password è corretta, cambia la password dell'utente nel database e nella variabile Utente
     * Se la vecchia password è errata, mostra un messaggio di errore
     *
     * @param event
     */
    public void changePassword(ActionEvent event) {
        String oldPassword = txtOldPassword.getText();
        String newPassword = txtNewPassword.getText();
        txtOldPassword.clear();
        txtNewPassword.clear();
        //Controllo che la vecchia password e la nuova password rispettino i requisiti
        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            support.messageStage("Inserire una password valida");
            return;
        }
        //Controllo che la vecchia password sia corretta
        if (!oldPassword.equals(Utente.getUtente().getPassword())) {
            support.messageStage("Password errata");
            return;
        }

        //Se la vecchia password è corretta, cambio la password dell'utente nel database e nella variabile Utente
        Utente.getUtente().setPassword(newPassword);
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        try {
            utenteDAO.updatePassword(Utente.getUtente().getEmail(), newPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        support.messageStage("Password cambiata con successo");
        event.consume();
    }

    /**
     * Metodo che permette di eliminare una serie preferita
     * Elimina la serie selezionata dalla listView Preferiti e dal database
     *
     * @param event
     */
    @FXML
    public void eliminaPreferito(ActionEvent event) {
        //Recupero la serie selezionata nella listView
        Serie serie = tableView.getSelectionModel().getSelectedItem();
        if (serie != null) {
            //Elimino la serie selezionata dal database
            UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
            try {
                utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serie.getCods());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //Elimino la serie selezionata dalla listView
            tableView.getItems().remove(serie);
            support.messageStage("Preferito eliminato con successo");
        } else {
            support.messageStage("Selezionare una serie");
        }
        event.consume();
    }

    /**
     * Metodo che permette di andare alla pagina informativa di una serie
     * Mostra la pagina informativa della serie selezionata nella listView
     *
     * @param event
     */
    public void goToPaginaInformativaSerie(ActionEvent event) {
        Serie serie = tableView.getSelectionModel().getSelectedItem();
        if (serie == null) {
            support.messageStage("Selezionare prima una serie");
            return;
        }
        Stage stage = (Stage) labelEmail.getScene().getWindow();
        stage.close();
        support.switchStageSerie("serieStage.fxml", serie.getCods());
        event.consume();
    }

    /**
     * Metodo che si avvia quando si preme il tasto "Elimina account"
     * Metodo che permette di eliminare l'account dell'utente
     * Elimina l'account dell'utente dal database e mostra la pagina di benvenuto
     * Mostrando un messaggio di conferma
     *
     * @param event
     */
    @FXML
    void eliminareAccount(ActionEvent event) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminazione account");
        alert.setHeaderText("Sei sicuro di voler eliminare il tuo profilo ?");
        alert.setContentText("Una volta eliminato non potrai più accedere al tuo account");
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                utenteDAO.delete(Utente.getUtente().ObjToArrayList());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            support.switchStage("welcomeStage.fxml", event, 500, 500);
            support.messageStage("Account eliminato con successo");
        }
    }

    public void back_goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }
}
