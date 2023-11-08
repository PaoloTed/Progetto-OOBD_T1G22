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

    private SupportStage support = new SupportStage();


    @FXML
    public void LogIn(ActionEvent PressLogin) {
        //Connessione al database e controllo che la connessione sia avvenuta con successo
        Connection conn = Connessione.getConnection();
        if(conn != null) {
            //Se la connessione è avvenuta con successo, controllare che l'email rispetti la regex e che la password non sia vuota
            String emailUser = txtEmailField.getText();
            String passwordUser = txtPasswordField.getText();
            if(support.checkEmailPassword(emailUser, passwordUser)) {
                //Se l'email e la password rispettano i requisiti, controllare che l'utente sia presente nel database
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
            else {


                //Se l'email e/o la password non rispettano i requisiti, mostrare un messaggio di errore
                Stage stage = (Stage) ((Node) PressLogin.getSource()).getScene().getWindow();
                stage.close();
                try {
                    support.errorStage("errorStage.fxml","Inserire una email e/o password valida");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            //Se la connessione non è avvenuta con successo, mostrare un messaggio di errore
            System.out.println("Connessione non effettuata con successo");
        }
    }



    public void goToSingUpUtente(ActionEvent PressSingUp) throws IOException
    {
        Stage stage= (Stage) ((Node) PressSingUp.getSource()).getScene().getWindow();
        stage.close();
        support.switchStage("signUp.fxml");
    }












}