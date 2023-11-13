package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.SerieDAO;
import com.example.bibliotecadigitale.Connection.UtenteDAO;
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
        labelEmail.setText(Utente.getUtente().getEmail());
        SerieDAO serieDAO = new SerieDAO();
        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<String> serie = serieDAO.findSerieFromCode(utenteDAO.searchPreferiti(Utente.getUtente().getEmail()));
        setContentView(serie);
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
        String serie = listViewSerie.getSelectionModel().getSelectedItem();
        if (serie != null) {
            UtenteDAO utenteDAO = new UtenteDAO();
            SerieDAO serieDAO = new SerieDAO();
            ArrayList<String> serieCode = new ArrayList<>();
            serieCode.add(serie);
            utenteDAO.deletePreferiti(Utente.getUtente().getEmail(), serieDAO.findSerieFromName(serieCode).get(0));;
            listViewSerie.getItems().remove(serie);
            support.messageStage("Preferito eliminato con successo");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            support.switchStage("infoUserStage.fxml");

        } else {
            support.messageStage("Selezionare una serie");
        }
    }


    public void back_goToHome(ActionEvent event) {
        //Correggere che switch stage apre a 500 per 500 ma home è 900 per 900
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        support.switchStage("home.fxml");
    }
}
