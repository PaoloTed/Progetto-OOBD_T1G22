package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.SerieDAO;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InfoUserController implements Initializable {
    @FXML
    public Label labelEmail;
    @FXML
    public PasswordField txtOldPassword;
    @FXML
    public PasswordField txtNewPassword;
    @FXML
    public ListView<String> listViewSerie;

    //Supporto per la gestione delle finestre
    private SupportStage support = new SupportStage();

    @Override
    //Inizializza la pagina con i dati dell'utente e le serie preferite
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Mostro l'email dell'utente
        String email = Utente.getUtente().getEmail();
        labelEmail.setText(email);
        setContentView(email);
    }

    public void setContentView(String email) {
        SerieDAO serieDAO = new SerieDAO();
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();

        //Recupero le serie preferite dell'utente
        ArrayList<Integer> codPreferiti = utenteDAO.searchPreferiti(email);
        Serie serieAppoggio;
        String serieNomeAppoggio;
        String serieCodSAppoggio;

        //Mostro le serie preferite dell'utente nella listView
        for (int i = 0; i < codPreferiti.size(); i++) {
            serieAppoggio = serieDAO.findSerieFromCodev2(codPreferiti.get(i));
            serieCodSAppoggio = serieAppoggio.getCodS();
            serieNomeAppoggio = serieAppoggio.getNome();
            //Viene inserito nella listView il codice della serie e il suo nome
            listViewSerie.getItems().add(serieCodSAppoggio + " - " + serieNomeAppoggio);
        }
    }

    //Metodo che cambia la password dell'utente
    public void changePassword(ActionEvent event) {
        String oldPassword = txtOldPassword.getText();
        String newPassword = txtNewPassword.getText();
        txtOldPassword.clear();
        txtNewPassword.clear();
        //Controllo che la vecchia password e la nuova password rispettino i requisiti
        if (!support.checkEmailPassword(oldPassword, newPassword)) {
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
        Serie serie = getSerieFromListView();
        if (serie != null) {
            //Elimino la serie selezionata dal database
            UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
            utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serie.getCodS());

            //Elimino la serie selezionata dalla listView
            deleteSerieFromListView(serie);
            support.messageStage("Preferito eliminato con successo");
            support.switchStage("infoUserStage.fxml", event);

        } else {
            support.messageStage("Selezionare una serie");
        }
    }

    //Metodo che restituisce la serie selezionata nella listView
    public Serie getSerieFromListView() {
        String serieCodS_Nome = listViewSerie.getSelectionModel().getSelectedItem();
        Serie serieAppoggio = new Serie();
        serieAppoggio.setCodS(serieCodS_Nome.substring(0, serieCodS_Nome.indexOf(" - ")));
        serieAppoggio.setNome(serieCodS_Nome.substring(serieCodS_Nome.indexOf(" - ") + 3));
        return serieAppoggio;
    }

    //Metodo che elimina la serie selezionata dalla listView
    public void deleteSerieFromListView(Serie serie) {
        listViewSerie.getItems().remove(serie.getCodS() + " - " + serie.getNome());
    }


    public void back_goToHome(ActionEvent event) {
        support.switchStage("home.fxml", event, 900, 900);
    }
}
