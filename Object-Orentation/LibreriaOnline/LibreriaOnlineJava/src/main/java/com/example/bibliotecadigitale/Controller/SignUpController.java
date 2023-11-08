package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.SupportStage;
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

    private SupportStage support = new SupportStage();
    //SupportStage è una classe che contiene metodi che possono essere utilizzati da più controller
    // per gestire errori e cambiare scena

    public void back_goToWelcome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        support.switchStage("welcome.fxml");

    }

    public void signUpTry(ActionEvent event) {
        String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
        //Connessione al database e controllo che la connessione sia avvenuta con successo
        Connection conn = Connessione.getConnection();
        if (conn != null) {
            //Se la connessione è avvenuta con successo, controllare che l'email rispetti la regex e che la password non sia vuota
            String emailUser = txtSignUpEmailField.getText();
            String passwordUser = txtSignUpPasswordField.getText();
            if (support.checkEmailPassword(emailUser, passwordUser)) {
                try {
                    //Controllare prima che non esista già un utente con la stessa email
                    String queryTest = "SELECT COUNT(*) FROM Utente WHERE email = '" + emailUser + "';";
                    Statement statTest = conn.createStatement();
                    ResultSet rsTest = statTest.executeQuery(queryTest);
                    while (rsTest.next()) {
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
                            //Chiudere la finestra di registrazione e aprire la finestra di login
                            back_goToWelcome(event);
                        } else {
                            //Se esiste già un utente con la stessa email, mostrare un messaggio di errore
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.close();
                            support.switchStage("errorStage.fxml");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Errore Controllore query select");
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
            else {
                //Se l'email non rispetta la regex e/o la password è vuota, mostrare un messaggio di errore
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                try {
                    support.switchStage("errorStage.fxml");
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
}

