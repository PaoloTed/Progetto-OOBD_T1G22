package com.example.bibliotecadigitale;

import com.example.bibliotecadigitale.Connection.Connessione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage welcomeStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcomeStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        welcomeStage.setResizable(false);
        welcomeStage.setTitle("Benvenuto nella libreria online!");
        welcomeStage.setScene(scene);
        welcomeStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/biblioteca.png"))));
        welcomeStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void stop() {
        Connessione connessione = new Connessione();
        connessione.closeConnection();
        System.exit(0);
    }
}
