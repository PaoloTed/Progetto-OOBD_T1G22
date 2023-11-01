package com.example.bibliotecadigitale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("welcome.fxml"));
        double w = Screen.getPrimary().getVisualBounds().getWidth();
        double h = Screen.getPrimary().getVisualBounds().getHeight();
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setResizable(false);

        stage.setTitle("Benvenuto nella libreria online!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}