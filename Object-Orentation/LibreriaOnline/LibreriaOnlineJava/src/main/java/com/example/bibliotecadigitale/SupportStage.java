package com.example.bibliotecadigitale;


import com.example.bibliotecadigitale.Controller.*;
import com.example.bibliotecadigitale.Model.ArticoloScientifico;
import com.example.bibliotecadigitale.Model.Libro;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SupportStage
{
    public SupportStage(){}


    private Stage stage;

    public void switchStage(String scenaNew,ActionEvent eventClose)
    {
        stage = (Stage) ((Node) eventClose.getSource()).getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 500, 500));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStage(String scenaNew, ActionEvent eventClose, int larghezza, int altezza)
    {
        stage = (Stage) ((Node) eventClose.getSource()).getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, larghezza, altezza));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void switchStage(String scenaNew, int altezza, int larghezza)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, altezza, larghezza));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void switchStage(String scenaNew, Libro libro)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 500, 500));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            LibroInformativaController libroInformativaController = loader.getController();
            libroInformativaController.showInfoLibro(libro);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStage(String scenaNew, String presentazione)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 250, 250));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            PresentazioneController presentazioneController = loader.getController();
            presentazioneController.showInfoPresentazione(presentazione);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStage(String scenaNew, ArticoloScientifico Articolo)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 500, 500));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            ArticoloInformativaController articoloInformativaController = loader.getController();
            articoloInformativaController.showInfoArticolo(Articolo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchStage(String scenaNew, int CodC)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 250, 250));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            ConferenzaController conferenzaController = loader.getController();
            conferenzaController.showInfoConferenza(CodC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStage(String scenaNew, String Data, String Nome)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 250, 250));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            RivistaController rivistaController = loader.getController();
            rivistaController.showInfoRivista(Data ,Nome );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchStageAquistiLibri(String scenaNew, String ISBN)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 900, 800));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            AquistoController aquistoController = loader.getController();
            aquistoController.showInfoAquisto(ISBN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageAquistiArticoli(String scenaNew, String Doi)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 900, 800));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            AquistoController aquistoController = loader.getController();
            aquistoController.showInfoArtcolo(Doi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageSerieStage(String scenaNew, int CodS)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 900, 800));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
            SerieController serieController = loader.getController();
            serieController.showSerie(CodS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean checkEmailPassword(String email, String password)
    {
        //Implementare controllo sql injection
        if( !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || password.isEmpty() || email.isEmpty())
        {
            return false;
        }
        return true;
    }


    public void messageStage( String tipoErrore)
    {
        String scenaFxml = "messageStage.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaFxml)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, 300, 100));
            stage.setAlwaysOnTop(true);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/biblioteca.png")));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();

            MessageController controller = loader.getController();
            controller.setText(tipoErrore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stage getStage() {return stage;}

}
