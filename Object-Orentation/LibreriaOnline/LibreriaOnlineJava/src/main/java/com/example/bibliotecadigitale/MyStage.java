package com.example.bibliotecadigitale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MyStage
{
    public MyStage(){}

    private Stage stage;

    public void CreateStage(String S) throws IOException
    {
        //crea un nuovo stage partendo dal file fxml
        //riceve come input il nome del file, presente nella cartella resources/com.examples..

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(S)));
        stage = new Stage();

        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Project Gallery");
        stage.setResizable(false);


        stage.setWidth(920);
        stage.setHeight(620);
        stage.show();

    }


    public Stage getStage() {return stage;}
}
