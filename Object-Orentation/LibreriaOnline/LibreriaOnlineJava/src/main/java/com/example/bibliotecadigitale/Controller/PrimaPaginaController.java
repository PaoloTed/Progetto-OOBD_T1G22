package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaPaginaController {
    @FXML
    private ComboBox idComboBox;

    @FXML
    private TextField idBarSearch;
    public SupportStage support = new SupportStage();

    @FXML
    void Select(ActionEvent event) {
        String scelta =idComboBox.getSelectionModel().getSelectedItem().toString();


    }
    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStage.fxml",event);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list= FXCollections.observableArrayList("Libri","Autori","Generi");
        idComboBox.setItems(list);

    }
}