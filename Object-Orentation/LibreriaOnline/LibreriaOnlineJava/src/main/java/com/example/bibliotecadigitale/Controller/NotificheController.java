package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class NotificheController implements Initializable {
    private final SupportStage support = new SupportStage();

    @FXML
    public ListView<String> listViewSerieAcquisto;

    @FXML
    private ImageView imageLibriSfondo;

    private final Utente utente = Utente.getUtente();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
        try {
            UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
            ArrayList<ArrayList<String>> preferiti = utenteDAO.getPreferiti(utente.getEmail());
            for (ArrayList<String> preferito : preferiti) {
                listViewSerieAcquisto.getItems().add("SERIE: " + preferito.get(0) + " || " + preferito.get(1) + " || DOVE ACQUISTARLA: " + preferito.get(2) + " || " + preferito.get(3) + " || TIPO ACQUISTO: " + preferito.get(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToPaginaInformativaSerie(ActionEvent event) {
        int cods = Integer.parseInt(listViewSerieAcquisto.getSelectionModel().getSelectedItem().substring(7,listViewSerieAcquisto.getSelectionModel().getSelectedItem().indexOf(" || ")));
        Stage stage = (Stage) listViewSerieAcquisto.getScene().getWindow();
        stage.close();
        support.switchStageSerieStage("serieStage.fxml", cods);
        event.consume();
    }

    public void eliminaPreferito(ActionEvent event) {
        Serie serie = getSerieFromListView();
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        try {
            utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serie.getCods());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        Stage stage = (Stage) listViewSerieAcquisto.getScene().getWindow();
//        stage.close();
        support.switchStage("notificheStage.fxml",event ,800,900);
        //Elimino la serie selezionata dalla listView
        deleteSerieFromListView(serie);
        support.messageStage("Preferito eliminato con successo.");
    }

    private Serie getSerieFromListView() {
        String serieCodS_Nome = listViewSerieAcquisto.getSelectionModel().getSelectedItem();
        Serie serieAppoggio = new Serie();
        serieAppoggio.setCods(Integer.parseInt(serieCodS_Nome.substring(serieCodS_Nome.indexOf(" ")+1, serieCodS_Nome.indexOf(" || "))));
        serieAppoggio.setNome(serieCodS_Nome.substring(serieCodS_Nome.indexOf(" - ") + 3));
        return serieAppoggio;
    }

    //Metodo che elimina la serie selezionata dalla listView
    private void deleteSerieFromListView(Serie serie) {
        listViewSerieAcquisto.getItems().remove(serie.getCods() + " - " + serie.getNome());
    }

    public void goBack(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,800);
    }
}
