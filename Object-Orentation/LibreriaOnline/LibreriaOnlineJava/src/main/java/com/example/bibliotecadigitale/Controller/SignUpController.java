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

    public void signUpDone(ActionEvent event)
    {
        String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
        Connection conn = Connessione.getConnection();

        String emailUser = txtSignUpEmailField.getText();
        String passwordUser = txtSignUpPasswordField.getText();
        /*
        //regex se il campo email è vuoto o non è una email e se la password è vuota
        if(txtSignUpEmailField.getText().isEmpty() || !txtSignUpEmailField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || txtSignUpPasswordField.getText().isEmpty())
        {
            System.out.println("Inserire una email valida e/o una password valida");
        }
        */

        System.out.println("Connessione effettuata");

        try {
            //Controllare prima che non esista già un utente con la stessa email
            String queryTest = "SELECT COUNT(*) FROM Utente WHERE email = '"+ emailUser +"';";
            Statement statTest = conn.createStatement();
            ResultSet rsTest = statTest.executeQuery(queryTest);
            while(rsTest.next()) {
                int sizeTest = rsTest.getInt(1);

                //Se nessun utente ha la stessa email, inserire l'utente nel database
                if (sizeTest == 0) {
                    String query = "INSERT INTO Utente VALUES('" + emailUser + "','" + passwordUser + "','" + date + "');";

                    try {
                        Statement stat = conn.createStatement();
                        stat.executeQuery(query);
                    } catch (Exception e) {
                        System.out.println("Errore Controllore query insert");
                    }
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    InfoStage myStage = new InfoStage();
                    myStage.SwitchStage("welcome.fxml");
                //Se esiste già un utente con la stessa email, mostrare un messaggio di errore
                } else {
                    InfoStage myStage = new InfoStage();
                    myStage.errorStage("errorSignUp.fxml");
                }
            }
        }catch (Exception e){
            System.out.println("Errore Controllore query select");
        }



    }
}
