package com.example.bibliotecadigitale;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SupportStage
{
    public SupportStage(){}


    private Stage stage;

    public void switchStage(String scenaNew) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenaNew)));
        stage = new Stage();
        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Libreria digitale");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public Text textErrore;

    public void errorStage(String scenaFxml, String tipoErrore) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenaFxml)));
        stage = new Stage();
        stage.setScene(new Scene(root, 300, 100));
        stage.setTitle("Libreria digitale");
        stage.setResizable(false);
        stage.show();
    }

    public Stage getStage() {return stage;}

}
