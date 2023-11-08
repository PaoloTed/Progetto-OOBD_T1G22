package com.example.bibliotecadigitale.Controller;


import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class WelcomeController {


    @FXML
    public Button ButtonSingUp;
    @FXML
    public Button ButtonLoginIn;
    //txtEmailField è il fxid,(dentro code in scene builder) del campo di testo dove l'utente inserisce il suo username sadasd
    @FXML
    public TextField txtEmailField;

    @FXML
    public TextField txtPasswordField;


    @FXML
    public void LogIn(ActionEvent PressLogin) {
        Connection conn = Connessione.getConnection();
        if(conn != null) {
            SupportStage support = new SupportStage();
            String emailUser = txtEmailField.getText();
            String passwordUser = txtPasswordField.getText();
            if(support.checkEmailPassword(emailUser, passwordUser))
            {

                try {
                    String query = "SELECT * FROM utente WHERE email = '" + emailUser + "' AND password = '" + passwordUser + "'";
                    Statement stat = conn.createStatement();
                    ResultSet rs = stat.executeQuery(query);
                    while (rs.next()) {
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Password: " + rs.getString("password"));
                    }
                }catch (Exception e){
                    System.out.println("ErrorController query test");
                }
            }
            else
            {
                Stage stage = (Stage) ((Node) PressLogin.getSource()).getScene().getWindow();
                stage.close();
                try {
                    support.switchStage("errorSignUp.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            System.out.println("Connessione non effettuata con successo");
        }
    }



    public void SingUpUtente(ActionEvent PressSingUp) throws IOException
    {
        Stage stage= (Stage) ((Node) PressSingUp.getSource()).getScene().getWindow();
        stage.close();
        SupportStage myStage = new SupportStage();
        myStage.switchStage("singUP.fxml");
    }












}