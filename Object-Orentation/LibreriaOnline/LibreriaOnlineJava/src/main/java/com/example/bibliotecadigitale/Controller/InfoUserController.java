package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InfoUserController {
    @FXML
    public Label labelEmail;
    @FXML
    public TextField txtOldPassword;
    @FXML
    public TextField txtNewPassword;
    @FXML
    public ListView<String> listViewSerie;

    private SupportStage support = new SupportStage();

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
            } else {
                support.messageStage("Password errata");
            }
        }
    }
    public void back_goToWelcome(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        support.switchStage("welcome.fxml");
    }
}
