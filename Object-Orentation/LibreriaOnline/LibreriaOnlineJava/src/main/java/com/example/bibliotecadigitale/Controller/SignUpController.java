package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.InfoStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController
{

    @FXML
    public TextField txtSignUpEmailField;
    @FXML
    public TextField txtSignUpPasswordField;


    public void back(ActionEvent event) throws IOException
    {
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        InfoStage myStage = new InfoStage();
        myStage.SwitchStage("welcome.fxml");

    }

    public void signUpDone(ActionEvent event) throws IOException
    {
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        InfoStage myStage = new InfoStage();
        myStage.SwitchStage("welcome.fxml");

    }
}
