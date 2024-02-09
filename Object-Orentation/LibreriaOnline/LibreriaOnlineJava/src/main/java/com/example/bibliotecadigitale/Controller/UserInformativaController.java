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
    public ListView<String> listViewSerie;

    @FXML
    public TableView<Serie> tableView;

    @FXML TableColumn<Serie, String> codsColumn;
    @FXML TableColumn<Serie, String> nomeColumn;
    @FXML TableColumn<Serie, Integer> numlibriColumn;
    @FXML TableColumn<Serie, Boolean> completataColumn;

    @FXML
    private ImageView imageLibriSfondo;

    //Supporto per la gestione delle finestre
    private SupportStage support = new SupportStage();

    @Override
    //Inizializza la pagina con i dati dell'utente e le serie preferite
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Mostro l'email dell'utente
        String email = Utente.getUtente().getEmail();
        labelEmail.setText(email);
        codsColumn.setCellValueFactory(new PropertyValueFactory<Serie, String>("cods"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Serie, String>("nome"));
        numlibriColumn.setCellValueFactory(new PropertyValueFactory<Serie, Integer>("numlibri"));
        completataColumn.setCellValueFactory(new PropertyValueFactory<Serie, Boolean>("completata"));
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
        setTableView(email);
    }

    public void setTableView(String email) {
        SerieDAOImpl serieDAO = new SerieDAOImpl();
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();

        ArrayList<Integer> codPreferiti = utenteDAO.searchPreferiti(email);
        //Recupero le serie preferite dell'utente
        //Mostro le serie preferite dell'utente nella listView
        for (int i = 0; i < codPreferiti.size(); i++) {
            //Viene inserito nella listView il codice della serie e il suo nome
            tableView.getItems().add(serieDAO.get(codPreferiti.get(i)));
        }
    }

    //Metodo che cambia la password dell'utente
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
        utenteDAO.updatePassword(Utente.getUtente().getEmail(), newPassword);
        support.messageStage("Password cambiata con successo");
    }

    //Metodo che elimina la serie selezionata dalla listView e dal database
    @FXML
    public void eliminaPreferito(ActionEvent event) {
        //Recupero la serie selezionata nella listView
        Serie serie = tableView.getSelectionModel().getSelectedItem();
        if (serie != null) {
            //Elimino la serie selezionata dal database
            UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
            utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serie.getCods());

            //Elimino la serie selezionata dalla listView
            tableView.getItems().remove(serie);
            support.messageStage("Preferito eliminato con successo");

        } else {
            support.messageStage("Selezionare una serie");
        }
    }

    public void goToPaginaInformativaSerie(ActionEvent event) {
        Serie serie = tableView.getSelectionModel().getSelectedItem();
        if (serie == null) {
            support.messageStage("Selezionare prima una serie");
            return;
        }
        Stage stage = (Stage) labelEmail.getScene().getWindow();
        stage.close();
        support.switchStageSerieStage("serieStage.fxml", serie.getCods());

    }

    public void back_goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }



}
