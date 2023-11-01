package com.example.libreriaonlinejava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Caricamento della schermata di login
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Prendere la risoluzione dello schermo
        /*
        double altezzaSchermo=  Screen.getPrimary().getBounds().getHeight();
        double larghezzaSchermo=   Screen.getPrimary().getBounds().getWidth();

         */

        Scene scene = new Scene(fxmlLoader.load(), 1920,1080
                );
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}