package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.InfoStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class SignUpController
{

    @FXML
    public TextField txtSignUpEmailField;
    @FXML
    public TextField txtSignUpPasswordField;


    public void back(ActionEvent event) throws IOException
    {
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        InfoStage myStage = new InfoStage();
        myStage.SwitchStage("welcome.fxml");

    }

    public void signUpDone(ActionEvent event) throws IOException
    {
        String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
        Connection conn = Connessione.getConnection();
        String emailUser = txtSignUpEmailField.getText();
        String passwordUser = txtSignUpPasswordField.getText();
        System.out.println("Connessione effettuata");
        try {
            String query = "INSERT INTO Utente VALUES('"+ emailUser +"','"+ passwordUser +"','"+date+"');" ;
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);


        }catch (Exception e){
            System.out.println("Errore query test");
        }

        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        InfoStage myStage = new InfoStage();
        myStage.SwitchStage("welcome.fxml");

    }
}
