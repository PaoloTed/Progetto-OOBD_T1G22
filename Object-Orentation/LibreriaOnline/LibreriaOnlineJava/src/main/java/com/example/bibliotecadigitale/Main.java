package com.example.bibliotecadigitale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage welcomeStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcomeStage.fxml"));
        //Prova per prendere la risoluzione dello schermo e settare la finestra di conseguenza
        /*
        double w = Screen.getPrimary().getVisualBounds().getWidth();
        double h = Screen.getPrimary().getVisualBounds().getHeight();
        */
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        //Impostare la finestra non ridimensionabile.
        welcomeStage.setResizable(false);

        welcomeStage.setTitle("Benvenuto nella libreria online!");
        welcomeStage.setScene(scene);
        welcomeStage.getIcons().add(new Image("file:src/main/resources/photo/biblioteca.png"));
        welcomeStage.show();

    }
        public static void main (String[]args){
            launch();
        }

    }
