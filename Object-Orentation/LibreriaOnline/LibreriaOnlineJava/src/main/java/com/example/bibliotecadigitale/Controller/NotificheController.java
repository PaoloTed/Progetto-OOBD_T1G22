package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NotificheController implements Initializable {
    private SupportStage support = new SupportStage();

    @FXML
    public ListView<String> listViewSerieAcquisto;

    @FXML
    public MenuItem prova1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utente utente = Utente.getUtente();
        String email = utente.getEmail();
        try {
            Connessione connessione = new Connessione();
            String query = "select * from show_preferiti('" + email + "');";
            ResultSet rs =  connessione.executeSearch(query);
            while (rs.next()) {
                listViewSerieAcquisto.getItems().add("SERIE: "+rs.getString(1)+" "+rs.getString(2)+" || DOVE ACQUISTARLA: "+rs.getString(3)+" "+rs.getString(4)+" || TIPO ACQUISTO: "+rs.getString(5));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,900);
    }

    public void home(ActionEvent event) {
        Stage stages = (Stage) prova1.getParentPopup().getOwnerWindow();
        stages.close();
        support.switchStage("homeStage.fxml",event,900,900);
        //support.messageStage("Hai cliccato su home");

    }
}
