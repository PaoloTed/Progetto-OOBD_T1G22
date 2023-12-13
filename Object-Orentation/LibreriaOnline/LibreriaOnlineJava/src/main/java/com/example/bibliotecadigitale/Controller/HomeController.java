package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.Connessione;
import com.example.bibliotecadigitale.Connection.LibroDAOImpl;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    private TextField idBarSearch;
    public SupportStage support = new SupportStage();
    @FXML
    public ListView<String> listViewLibri;
    @FXML
    private Button buttonMostra;

    @FXML
    public TableView<Libro> tableView;

    @FXML TableColumn<Libro, String> isbnColumn;
    @FXML TableColumn<Libro, String> titoloColumn;
    @FXML TableColumn<Libro, String> autoreColumn;
    @FXML TableColumn<Libro, String> editoreColumn;
    @FXML TableColumn<Libro, String> genereColumn;

    @FXML
    void Select(ActionEvent event) {
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        String modRicerca = idComboBox.getSelectionModel().getSelectedItem();
        //listViewLibri.getItems().clear();
        System.out.println(modRicerca);
        String titoloRicerce = idBarSearch.getText();
        if (titoloRicerce.isEmpty()) {
            support.messageStage("Inserire una ricerca non vuota");
            return;
        }
        ArrayList<Libro> libri = libroDAO.getRicerca(modRicerca, titoloRicerce);
        tableView.getItems().addAll(libri);
        /*
        String titolo;
        String autore;
        String genere;
        String editore;
        String isbnAppoggio;
        for (int i = 0; i < libri.size(); i++) {
            titolo = libri.get(i).getTitolo();
            autore = libri.get(i).getAutore();
            genere = libri.get(i).getGenere();
            editore = libri.get(i).getEditore();
            isbnAppoggio = libri.get(i).getISBN();
            listViewLibri.getItems().add(isbnAppoggio + " - " + titolo + " - " + autore + " - " + genere + " - " + editore);
        }

         */
        if (libri.isEmpty()) {
            support.messageStage("Nessun match trovato");
            idBarSearch.clear();
            buttonMostra.setDisable(true);
        } else {
            buttonMostra.setDisable(false);
        }
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

    //todo log off
    public void logout(Stage scene) {
        System.out.println("Addio");
        Connessione connessione = new Connessione();
        connessione.closeConnection();
        scene.close();
    }

    public void checkEnter(ActionEvent event) {
        idComboBox.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                System.out.println("The 'A' key was pressed");
            }
        });
    }


    public Libro getLibroFromListView() throws SQLException {
        String libroSelezionato = listViewLibri.getSelectionModel().getSelectedItem();
        String[] libroSelezionatoSplit = libroSelezionato.split(" - ");
        String isbn = libroSelezionatoSplit[0];
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        return libroDAO.get(isbn);
    }

    public void goToNotifiche(ActionEvent event) {
        support.switchStage("notificheStage.fxml", event, 900, 900);
    }

    public void goToPaginaInformativaLibro(ActionEvent event) throws SQLException, IOException {
        SupportStage support = new SupportStage();
        Libro libro = getLibroFromListView();
        support.switchStage("paginaInformativaLibro.fxml", event, libro);
    }


}