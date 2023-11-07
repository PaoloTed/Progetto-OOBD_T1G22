package com.example.bibliotecadigitale.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;



public class ErroreControllore
{
    public void ok(ActionEvent buttonOk) {

        Stage stage= (Stage)((Node) buttonOk.getSource()).getScene().getWindow();
        stage.close();

    }


}
