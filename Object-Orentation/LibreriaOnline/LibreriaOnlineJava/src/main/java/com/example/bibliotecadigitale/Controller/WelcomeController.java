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
import java.sql.SQLException;
import java.sql.Statement;


public class WelcomeController {


    @FXML
    public Button ButtonSingUp;
    @FXML
    public Button ButtonLoginIn;
    //txtEmailField è il fxid, (dentro code in scene builder) del campo di testo dove l'utente inserisce il suo username sadasd
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

                    try {
                        String query = "SELECT * FROM utente WHERE email = '" + emailUser + "' AND password = '" + passwordUser  + "';";
                        Statement stat = conn.createStatement();
                        ResultSet rs = stat.executeQuery(query);
                        while(rs.next()) {
                            int sizeTest = rs.getRow();
                            if (sizeTest != 0) {
                                //Se l'utente è presente nel database, mostrare la sua home page
                                //TODO: mostrare la home page dell'utente
                                System.out.println("Email: " + rs.getString("email"));
                                System.out.println("Password: " + rs.getString("password"));

                            } else {
                                //Se l'utente non è presente nel database, mostrare un messaggio di errore
                                try {
                                    support.errorStage("errorStage.fxml", "Email e/o password errate");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                            conn.close();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                //Se la query non è stata eseguita con successo, mostrare un messaggio di errore
                }catch (Exception e){
                    try {
                        support.errorStage("errorStage.fxml","Query Select non eseguita con successo");
                    } catch (IOException ee) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("ErrorController query test");
                }
            }
            //Se l'email e/o la password non rispettano i requisiti, mostrare un messaggio di errore
            else {
                try {
                    support.errorStage("errorStage.fxml","Inserire una email e/o password valida");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //Se la connessione non è avvenuta con successo, mostrare un messaggio di errore
        else {
            try {
                support.errorStage("errorStage.fxml","Connessione non effettuata con successo");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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