package com.example.bibliotecadigitale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome2.fxml"));
            //Prova per prendere la risoluzione dello schermo e settare la finestra di conseguenza
        /*
        double w = Screen.getPrimary().getVisualBounds().getWidth();
        double h = Screen.getPrimary().getVisualBounds().getHeight();
        */
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            //Impostare la finestra non ridimensionabile.
            stage.setResizable(false);

            stage.setTitle("Benvenuto nella libreria online!");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // A cosa serve questo metodo?

    /*public static void main(String[] args) {
        launch();
    }

     */
}