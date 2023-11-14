package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.SerieDAO;
import com.example.bibliotecadigitale.Connection.UtenteDAO;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

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

    private SupportStage support = new SupportStage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email = Utente.getUtente().getEmail();
        labelEmail.setText(email);
        SerieDAO serieDAO = new SerieDAO();
        UtenteDAO utenteDAO = new UtenteDAO();

        ArrayList<Integer> codPreferiti = utenteDAO.searchPreferiti(email);
        ArrayList<Serie> seriePreferite = new ArrayList<>();
        ArrayList<String> serieArrayContentView = new ArrayList<>();
        Serie serieAppoggio;
        String serieNomeAppoggio;
        int serieCodSAppoggio;

        for(int i = 0; i < codPreferiti.size(); i++) {
            serieAppoggio = serieDAO.findSerieFromCodev2(codPreferiti.get(i));
            seriePreferite.add(serieAppoggio);
            serieCodSAppoggio = serieAppoggio.getCodS();
            serieNomeAppoggio = serieAppoggio.getNome();
            serieArrayContentView.add(serieCodSAppoggio + " - " + serieNomeAppoggio );
        }
        setContentView(serieArrayContentView);
    }

    public void setContentView(ArrayList<String> serie) {
        for (String s : serie) {
            listViewSerie.getItems().add(s);
        }
    }

    public void changePassword(ActionEvent event) {
        String oldPassword = txtOldPassword.getText();
        String newPassword = txtNewPassword.getText();
        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            support.messageStage("Inserire valori validi");
        } else {
            if(oldPassword.equals(Utente.getUtente().getPassword())) {
                Utente.getUtente().setPassword(newPassword);
                UtenteDAO utenteDAO = new UtenteDAO();
                utenteDAO.updatePassword(Utente.getUtente().getEmail(), newPassword);
                support.messageStage("Password cambiata con successo");
                txtOldPassword.clear();
                txtNewPassword.clear();
            } else {
                support.messageStage("Password errata");
            }
        }
    }
    @FXML
    public void eliminaPreferito(ActionEvent event) {
        Serie serie = getSerieFromListView();
        if (serie != null) {
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serie.getCodS());

            deleteSerieFromListView(serie);
            support.messageStage("Preferito eliminato con successo");
            support.switchStage("infoUserStage.fxml",event);

        } else {
            support.messageStage("Selezionare una serie");
        }
    }
    public Serie getSerieFromListView() {
        String serieCodS_Nome = listViewSerie.getSelectionModel().getSelectedItem();
        Serie serieAppoggio = new Serie();
        serieAppoggio.setCodS(Integer.parseInt(serieCodS_Nome.substring(0, serieCodS_Nome.indexOf(" - "))));
        serieAppoggio.setNome(serieCodS_Nome.substring(serieCodS_Nome.indexOf(" - ")+3));
        return serieAppoggio;
    }
    public void deleteSerieFromListView(Serie serie) {
        listViewSerie.getItems().remove(serie.getCodS() + " - " + serie.getNome());
    }


    public void back_goToHome(ActionEvent event) {
        support.switchStage("home.fxml", event, 900, 900);
    }
}
