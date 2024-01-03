package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.LibroDAOImpl;
import com.example.bibliotecadigitale.Model.ArticoloScientifico;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeControllerTest implements Initializable {
    private String scelta = null;
    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    private TextField idBarSearch;
    public SupportStage support = new SupportStage();
    @FXML
    private Button buttonMostra;

    @FXML
    private Button buttonLibro;

    @FXML
    private Button buttonArticolo;

    @FXML
    public TableView<Libro> libroTableView;

    @FXML
    TableColumn<Libro, String> isbnColumn;
    @FXML
    TableColumn<Libro, String> titoloColumn;
    @FXML
    TableColumn<Libro, String> autoreColumn;
    @FXML
    TableColumn<Libro, String> editoreColumn;
    @FXML
    TableColumn<Libro, String> genereColumn;

//    @FXML
//    public TableView<ArticoloScientifico> articoloTableView;
//
//    @FXML
//    TableColumn<ArticoloScientifico, String> doiColumn;
//    @FXML
//    TableColumn<ArticoloScientifico, String> titoloAColumn;
//    @FXML
//    TableColumn<ArticoloScientifico, String> autoreAColumn;
//    @FXML
//    TableColumn<ArticoloScientifico, String> editoreAColumn;
//    @FXML
//    TableColumn<ArticoloScientifico, String> genereAColumn;

    @FXML
    void Select(ActionEvent event) {
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        String modRicerca = idComboBox.getSelectionModel().getSelectedItem();
        System.out.println(modRicerca);
        String titoloRicerce = idBarSearch.getText();
        buttonMostra.setDisable(false);
        if (titoloRicerce.isEmpty()) {
            support.messageStage("Inserire una ricerca non vuota");
            return;
        }
        ArrayList<Libro> libri = libroDAO.getRicerca(modRicerca, titoloRicerce);
        if (libri.isEmpty()) {
            support.messageStage("Nessun match trovato");
            idBarSearch.clear();
            return;
        }
        libroTableView.getItems().clear();
        libroTableView.getItems().addAll(libri);
    }

    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStageTest.fxml", event);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        idComboBox.setItems(FXCollections.observableArrayList("Titolo", "Autore", "Genere", "Editore"));
        idComboBox.getSelectionModel().selectFirst();
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
        titoloColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("titolo"));
        autoreColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("autore"));
        editoreColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("editore"));
        genereColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("genere"));
    }

    public void logOut(Stage scene) {
        System.out.println("Addio");
        Connessione connessione = new Connessione();
        connessione.closeConnection();
        Utente.getUtente().exitUtente();
        scene.close();
        support.switchStage("welcomeStage.fxml", 500,300);
    }

    public void checkEnter(ActionEvent event) {
        idComboBox.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                System.out.println("The 'A' key was pressed");
            }
        });
    }


    public void goToNotifiche(ActionEvent event) {
        support.switchStage("notificheStage.fxml", event, 900, 900);
    }

    public void goToPaginaInformativaLibro(ActionEvent event) throws SQLException, IOException {
        SupportStage support = new SupportStage();
        Libro libro = libroTableView.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) libroTableView.getScene().getWindow();
        stage.close();
        support.switchStage("paginaInformativaLibro.fxml", libro);
    }

    public void selezioneLibro(ActionEvent event) {
        scelta = "libro";
//        articoloTableView.setVisible(false);
//        libroTableView.setVisible(true);
        buttonArticolo.setStyle("-fx-border-color: grey;");
        buttonLibro.setStyle("-fx-border-color: red;");
    }
    public void selezioneArticolo(ActionEvent event) {
        scelta = "articolo";
//        articoloTableView.setVisible(true);
//        libroTableView.setVisible(false);
        buttonLibro.setStyle("-fx-border-color: grey;");
        buttonArticolo.setStyle("-fx-border-color: red;");
    }

}