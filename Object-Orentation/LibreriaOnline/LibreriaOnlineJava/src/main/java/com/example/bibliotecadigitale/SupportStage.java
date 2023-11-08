package com.example.bibliotecadigitale;

import com.example.bibliotecadigitale.Controller.errorSignUpController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SupportStage
{
    public SupportStage(){}


    private Stage stage;

    public void switchStage(String scenaNew) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenaNew)));
        stage = new Stage();
        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Libreria digitale");
        stage.setResizable(false);
        stage.show();
    }

    public boolean checkEmailPassword(String email, String password)
    {
        if( !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || password.isEmpty())
        {
            System.out.println("Inserire una email valida e/o una password valida");
            return false;
        }
        System.out.println("L'email rispetta la regex e password non è vuota");
        return true;
    }


    public void errorStage(String scenaFxml, String tipoErrore) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenaFxml)));
        stage = new Stage();
        stage.setScene(new Scene(root, 300, 100));
        stage.setAlwaysOnTop(true);
        stage.setTitle("Libreria digitale");
        stage.setResizable(false);
        stage.show();

    }

    public Stage getStage() {return stage;}

}
