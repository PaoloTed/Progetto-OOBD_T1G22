package com.example.bibliotecadigitale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InfoStage
{
    public InfoStage(){}

    private Stage stage;

    public void SwitchStage(String scenaFxml) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenaFxml)));
        stage = new Stage();
        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Libreria digitale");
        stage.setResizable(false);
        stage.show();
    }

    public void errorStage(String scenaFxml) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenaFxml)));
        stage = new Stage();
        stage.setScene(new Scene(root, 300, 100));
        stage.setTitle("ErroreControllore in Libreria digitale");
        stage.setResizable(false);
        stage.show();
    }

    public Stage getStage() {return stage;}

}
