package com.example.bibliotecadigitale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageController {
    @FXML
    public Text textError;

    @FXML
    public void setText( String errore) {
        textError.setText(errore);
    }
    public void okButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
