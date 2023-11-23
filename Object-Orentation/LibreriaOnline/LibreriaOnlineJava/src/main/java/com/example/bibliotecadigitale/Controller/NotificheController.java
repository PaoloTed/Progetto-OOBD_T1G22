package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NotificheController implements Initializable {
    private SupportStage support = new SupportStage();

    @FXML
    public ListView<String> listViewSerieAcquisto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utente utente = Utente.getUtente();
        String email = utente.getEmail();
        try {
            Connessione connessione = new Connessione();
            String query = "select * from show_preferiti('" + email + "');";
            ResultSet rs =  connessione.executeSearch(query);
            while (rs.next()) {
                listViewSerieAcquisto.getItems().add(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
