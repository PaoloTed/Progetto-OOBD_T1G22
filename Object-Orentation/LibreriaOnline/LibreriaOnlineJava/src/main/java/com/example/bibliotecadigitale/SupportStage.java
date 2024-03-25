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

public class SupportStage {
    private Stage stage;

    /**
     * Questo metodo permette di cambiare la scena
     * settandone la larghezza e l'altezza
     * e settando l'icona della finestra e il titolo
     *
     * @param root
     * @param larghezza
     * @param altezza
     * @throws IOException
     */
    private void cambiaStage(Parent root, int larghezza, int altezza) throws IOException {
        stage = new Stage();
        stage.setScene(new Scene(root, larghezza, altezza));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/biblioteca.png"))));
        stage.setTitle("Libreria digitale");
        stage.setResizable(false);
        stage.show();
    }


    public void switchStage(String scenaNew, ActionEvent eventClose, int larghezza, int altezza) {
        stage = (Stage) ((Node) eventClose.getSource()).getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,larghezza,altezza);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchStageLibro(String scenaNew, Libro libro) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,900,800);
            LibroInformativaController libroInformativaController = loader.getController();
            libroInformativaController.showInfoLibro(libro);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStagePresentazione(String scenaNew, int presentazione) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,300,300);
            PresentazioneController presentazioneController = loader.getController();
            presentazioneController.showInfoPresentazione(presentazione);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageArticolo(String scenaNew, ArticoloScientifico Articolo) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,900,800);
            ArticoloInformativaController articoloInformativaController = loader.getController();
            articoloInformativaController.showInfoArticolo(Articolo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchStageConferenza(String scenaNew, int CodC) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,300,300);
            ConferenzaController conferenzaController = loader.getController();
            conferenzaController.showInfoConferenza(CodC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageRivista(String scenaNew, String Nome, String Data) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,300,300);
            RivistaController rivistaController = loader.getController();
            rivistaController.showInfoRivista(Nome, Data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchStageAcquistiLibri(String scenaNew, String ISBN) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,900,800);
            AcquistoController acquistoController = loader.getController();
            acquistoController.showInfoAcquisto(ISBN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageAcquistiArticoli(String scenaNew, String Doi) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,900,800);
            AcquistoController acquistoController = loader.getController();
            acquistoController.showInfoArticolo(Doi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageSerie(String scenaNew, int CodS) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,900,800);
            SerieController serieController = loader.getController();
            serieController.showSerie(CodS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean checkEmailPassword(String email, String password) {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || password.isEmpty() || email.isEmpty()) {
            return false;
        }
        return true;
    }


    public void messageStage(String tipoMessaggio) {
        String scenaFxml = "messageStage.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaFxml)));
            Parent root = loader.load();
            cambiaStage(root,300,100);
            MessageController controller = loader.getController();
            controller.setText(tipoMessaggio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void messageStageError(String tipoErrore) {
        String scenaFxml = "messageStage.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaFxml)));
            Parent root = loader.load();
            cambiaStage(root,400,200);
            MessageController controller = loader.getController();
            controller.setText(tipoErrore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStageNoClose(String scenaNew) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            cambiaStage(root,900,800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stage getStage() {
        return stage;
    }
}
