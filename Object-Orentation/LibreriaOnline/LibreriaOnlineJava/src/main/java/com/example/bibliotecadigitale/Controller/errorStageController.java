package com.example.bibliotecadigitale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class errorStageController {
    @FXML
    public TextField txtSignUpEmailField;

    @FXML
    public void setText( String errore) {
        txtSignUpEmailField.setText(errore);
    }

}
