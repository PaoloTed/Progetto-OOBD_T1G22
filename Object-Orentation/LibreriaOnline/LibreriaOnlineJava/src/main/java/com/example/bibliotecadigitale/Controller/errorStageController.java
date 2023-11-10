package com.example.bibliotecadigitale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class errorStageController {
    @FXML
    public TextField txtSignUpEmailField;

    @FXML
    public void setText( String errore) {
        txtSignUpEmailField.setText(errore);
    }

    public void okButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
