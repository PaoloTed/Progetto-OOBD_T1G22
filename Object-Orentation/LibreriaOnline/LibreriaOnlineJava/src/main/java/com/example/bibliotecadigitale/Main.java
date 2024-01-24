package com.example.bibliotecadigitale;

import com.example.bibliotecadigitale.Connection.Connessione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {

    //TODO :
    // 1) Aggiungere la possibilità di aggiungere una serie alla lista dei preferiti e rimuovererla
    // 2) Completare visualizzazione libro con informazioni
    // 3) Visualizzare informazioni serie
    // 4) Visualizzare informazioni autore
    // 5) Visualizzare informazioni editore forse
    // 6) Visualizzare informazioni genere forse
    // 7) Visualizzare informazioni Acquisto
    // 8) Visualizzare informazioni Collane (view) forse
    // 9) risolvere problema di visualizzazione delle immagini
    // 10) risolvere problema di avvio programma su di un altro computer

    // 7)

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
        welcomeStage.getIcons().add(new Image("https://raw.githubusercontent.com/PaoloTed/Progetto-OOBD_T1G22/3388e4fb3e1e90d0792ee8abd540b0dbd3c50e91/Object-Orentation/LibreriaOnline/LibreriaOnlineJava/src/main/resources/Images/biblioteca.png"));
        welcomeStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void stop() {
        System.out.println("Addio");
        Connessione connessione = new Connessione();
        connessione.closeConnection();
        System.exit(0);
    }

}
