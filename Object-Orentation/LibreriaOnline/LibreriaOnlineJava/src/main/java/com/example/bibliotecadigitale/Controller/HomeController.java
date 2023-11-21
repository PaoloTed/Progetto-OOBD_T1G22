package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    private TextField idBarSearch;
    public SupportStage support = new SupportStage();

    @FXML
    void Select(ActionEvent event) {
        String scelta =idComboBox.getSelectionModel().getSelectedItem();
        System.out.println(scelta);

    }
    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStage.fxml",event);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
       idComboBox.setItems(FXCollections.observableArrayList("Titolo","Autore","Genere","Editore"));

    }
}