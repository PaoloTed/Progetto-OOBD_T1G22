package com.example.bibliotecadigitale.Controller;


import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Model.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class welcomeController {


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
    public void TestConn(ActionEvent PressLogin) {
        Connection conn = Connessione.getConnection();
        String emailUser = txtEmailField.getText();
        String passwordUser = txtPasswordField.getText();
        System.out.println("Connessione effettuata");
        try {
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "' AND password = '" + passwordUser + "'";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Password: " + rs.getString("password"));
            }

        }catch (Exception e){
            System.out.println("Errore query test");
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SingUpUtente(ActionEvent PressSingUp) throws IOException {
        root= FXMLLoader.load(getClass().getResource("singUp.fxml"));
        stage = (Stage)((Node)PressSingUp.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }










}