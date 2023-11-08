package com.example.bibliotecadigitale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class errorSignUpController {
    @FXML
    public Label errorLabel;

    @FXML
    public Button buttonOk;

    public void setText(ActionEvent event){
        System.out.println(errorLabel.getText());
    }



}
