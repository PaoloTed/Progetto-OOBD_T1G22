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

    public void ChangeStage(String S) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(S)));
        stage = new Stage();
        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Libreria diggitale");
        stage.setResizable(false);
        stage.show();
    }


    public Stage getStage() {return stage;}
}
