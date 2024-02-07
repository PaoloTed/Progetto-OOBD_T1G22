package com.example.bibliotecadigitale;

import com.example.bibliotecadigitale.Connection.Connessione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {

//    TODO :
//    1) aggiustare grafica informativa libro
//    2) aggiustare grafica informativa articolo
//    3) aggiustare grafica e funzionamento pagina presentazione
//    4) aggiustare grafica pagina acquisto
//    6) Errore disponibileA non funziona bene quadno si visualizzano tutti/
//    7) aggiustare conferenza in articolo mettere codice conferenza come integer


    @Override
    public void start(Stage welcomeStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcomeStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
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
        Connessione connessione = new Connessione();
        connessione.closeConnection();
        System.exit(0);
    }
}
