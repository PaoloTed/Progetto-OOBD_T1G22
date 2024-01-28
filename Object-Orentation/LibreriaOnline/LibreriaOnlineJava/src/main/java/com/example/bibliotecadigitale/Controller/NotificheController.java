package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView imageLibriSfondo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utente utente = Utente.getUtente();
        String email = utente.getEmail();
        imageLibriSfondo.setImage(new Image(getClass().getResourceAsStream("/Images/libri800x900.png")));
        try {
            Connessione connessione = new Connessione();
            String query = "select * from show_preferiti('" + email + "');";
            ResultSet rs =  connessione.executeSearch(query);
            while (rs.next()) {
                listViewSerieAcquisto.getItems().add("SERIE: "+rs.getString(1)+" || "+rs.getString(2)+" || DOVE ACQUISTARLA: "+rs.getString(3)+" || "+rs.getString(4)+" || TIPO ACQUISTO: "+rs.getString(5));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void home(ActionEvent event) {
        //Prendo la finestra corrente e la chiudo, non posso prendere la scena
        //da menuitem perchè non è un nodo, la prendo da listViewSerieAcquisto
        Stage stage = (Stage) listViewSerieAcquisto.getScene().getWindow();
        stage.close();
        support.switchStage("homeStage.fxml",800,900);
    }

    public void eliminaPreferito(ActionEvent event) {
        Serie serie = getSerieFromListView();
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serie.getCodS());

        //Elimino la serie selezionata dalla listView
        deleteSerieFromListView(serie);
        support.messageStage("Preferito eliminato con successo.");
    }

    public Serie getSerieFromListView() {
        String serieCodS_Nome = listViewSerieAcquisto.getSelectionModel().getSelectedItem();
        Serie serieAppoggio = new Serie();
        serieAppoggio.setCodS(Integer.parseInt(serieCodS_Nome.substring(serieCodS_Nome.indexOf(": "), serieCodS_Nome.indexOf(" || "))));
        serieAppoggio.setNome(serieCodS_Nome.substring(serieCodS_Nome.indexOf(" - ") + 3));
        return serieAppoggio;
    }

    //Metodo che elimina la serie selezionata dalla listView
    public void deleteSerieFromListView(Serie serie) {
        listViewSerieAcquisto.getItems().remove(serie.getCodS() + " - " + serie.getNome());
    }

    public void goBack(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,800);
    }
}
