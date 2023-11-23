package com.example.bibliotecadigitale;

import com.example.bibliotecadigitale.Controller.errorStageController;
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
            stage.getIcons().add(new Image("file:src/main/resources/photo/biblioteca.png"));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchStage(String scenaNew, ActionEvent eventClose, int altezza, int larghezza)
    {
        stage = (Stage) ((Node) eventClose.getSource()).getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scenaNew)));
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root, altezza, larghezza));
            stage.getIcons().add(new Image("file:src/main/resources/photo/biblioteca.png"));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();
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
            stage.getIcons().add(new Image("file:src/main/resources/photo/biblioteca.png"));
            stage.setTitle("Libreria digitale");
            stage.setResizable(false);
            stage.show();

            errorStageController controller = loader.getController();
            controller.setText(tipoErrore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stage getStage() {return stage;}

}
