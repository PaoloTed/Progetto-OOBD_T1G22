package com.example.bibliotecadigitale.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaPaginaController {
    @FXML
    private ComboBox idComboBox;

    @FXML
    private TextField idBarSearch;

    @FXML
    void Select(ActionEvent event) {
        String scelta =idComboBox.getSelectionModel().getSelectedItem().toString();


    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list= FXCollections.observableArrayList("Libri","Autori","Generi");
        idComboBox.setItems(list);

    }
}