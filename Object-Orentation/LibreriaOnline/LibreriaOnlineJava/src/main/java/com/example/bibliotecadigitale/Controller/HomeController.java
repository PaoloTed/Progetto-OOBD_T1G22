package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.ArticoloScientificoDAOImpl;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button buttonLibro;
    @FXML
    private Button buttonArticolo;
    private String scelta = "libro";
    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    private TextField idBarSearch;
    public SupportStage support = new SupportStage();
    @FXML
    private Button buttonMostra;


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

    @FXML
    public TableView<ArticoloScientifico> articoloTableView;

    @FXML
    TableColumn<ArticoloScientifico, String> doiColumn;
    @FXML
    TableColumn<ArticoloScientifico, String> titoloAColumn;
    @FXML
    TableColumn<ArticoloScientifico, String> autoreAColumn;
    @FXML
    TableColumn<ArticoloScientifico, String> editoreAColumn;
    @FXML
    TableColumn<ArticoloScientifico, String> genereAColumn;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo le colonne della tabella libro
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
        titoloColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("titolo"));
        autoreColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("autore"));
        editoreColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("editore"));
        genereColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("genere"));

        //inizializzo le colonne della tabella articolo
        doiColumn.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("doi"));
        titoloAColumn.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("titolo"));
        autoreAColumn.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("autore"));
        editoreAColumn.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("editore"));
        genereAColumn.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("genere"));

        //Imposto la ricerca su libro come default e nascondo la tabella articolo
        articoloTableView.setVisible(false);
        buttonLibro.setStyle("-fx-border-color: red;");
        buttonLibro.setDisable(true);
        buttonArticolo.setStyle("-fx-border-color: grey;");

        idComboBox.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Autore", "Genere", "Editore"));
        idComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    void Select(ActionEvent event) {
        //Gestione errori per la ricerca
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
        String modRicerca = idComboBox.getSelectionModel().getSelectedItem();
        if (modRicerca == null) {
            support.messageStage("Selezionare prima per cosa si vuole cercare");
            return;
        }
        String titoloRicerche = idBarSearch.getText();
        if (titoloRicerche.isEmpty()) {
            support.messageStage("Inserire una ricerca non vuota");
            return;
        }

        //Ricerca e visualizzazione risultati libri
        if (scelta.equals("libro")) {
            LibroDAOImpl libroDAO = new LibroDAOImpl();
            ArrayList<Libro> libri = libroDAO.getRicerca(modRicerca, titoloRicerche);
            if (libri.isEmpty()) {
                support.messageStage("Nessun match trovato");
                idBarSearch.clear();
                return;
            }

            libroTableView.getItems().clear();
            libroTableView.getItems().addAll(libri);
        }

        //Ricerca e visualizzazione risultati articoli
        if (scelta.equals("articolo")) {
            ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
            ArrayList<ArticoloScientifico> articoli = articoloScientificoDAO.getRicerca(modRicerca, titoloRicerche);
            if (articoli.isEmpty()) {
                support.messageStage("Nessun match trovato");
                idBarSearch.clear();
                return;
            }

            articoloTableView.getItems().clear();
            articoloTableView.getItems().addAll(articoli);
        }
    }

    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStage.fxml", event);
    }

    @FXML
    private void clearTableView(ActionEvent event) {
        if (scelta.equals("libro")) {
            libroTableView.getItems().clear();
        }
        if (scelta.equals("articolo")) {
            articoloTableView.getItems().clear();
        }
    }


    public void logOff(ActionEvent event) throws IOException {
        Utente.getUtente().exitUtente();
        support.switchStage("welcomeStage.fxml", event);
    }

//    public void checkEnter(ActionEvent event) {
//        idComboBox.getScene().setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.A) {
//                System.out.println("The 'A' key was pressed");
//            }
//        });
//    }


    public void goToNotifiche(ActionEvent event) {
        support.switchStage("notificheStage.fxml", event, 900, 800);
    }

    public void goToPaginaInformativaLibro(ActionEvent event) {
        SupportStage support = new SupportStage();
        Libro libro = libroTableView.getSelectionModel().getSelectedItem();
        if (libro == null) {
            support.messageStage("Selezionare prima un libro");
            return;
        }
        Stage stage = (Stage) libroTableView.getScene().getWindow();
        stage.close();
        support.switchStage("paginaInformativaLibro.fxml", libro);
    }
    public void goToPaginaInformativaSerie(ActionEvent event) {
        SupportStage support = new SupportStage();
        Libro libro = libroTableView.getSelectionModel().getSelectedItem();
        if (libro == null) {
            support.messageStage("Selezionare prima un libro");
            return;
        }
        Stage stage = (Stage) libroTableView.getScene().getWindow();
        stage.close();
        support.switchStageSerieStage("serieStage.fxml", libro.getSerie());
    }

    public void goToPaginaInformativaArticolo(ActionEvent event) {
        SupportStage support = new SupportStage();
        ArticoloScientifico articoloScientifico = articoloTableView.getSelectionModel().getSelectedItem();
        if (articoloScientifico == null) {
            support.messageStage("Selezionare prima un articolo");
            return;
        }
        Stage stage = (Stage) articoloTableView.getScene().getWindow();
        stage.close();
        support.switchStage("paginaInformativaArticolo.fxml", articoloScientifico);
    }

    public void selezioneLibro(ActionEvent event) {
        idComboBox.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Autore", "Genere", "Editore"));
        scelta = "libro";
        idComboBox.getSelectionModel().selectFirst();
        libroTableView.setVisible(true);
        buttonLibro.setDisable(true);
        buttonLibro.setStyle("-fx-border-color: red;");

        articoloTableView.setVisible(false);
        buttonArticolo.setDisable(false);
        buttonArticolo.setStyle("-fx-border-color: grey;");

    }

    public void selezioneArticolo(ActionEvent event) {
        idComboBox.setItems(FXCollections.observableArrayList("Doi", "Titolo", "Autore", "Genere", "Editore"));
        scelta = "articolo";
        idComboBox.getSelectionModel().selectFirst();
        articoloTableView.setVisible(true);
        buttonArticolo.setDisable(true);
        buttonArticolo.setStyle("-fx-border-color: red;");

        libroTableView.setVisible(false);
        buttonLibro.setDisable(false);
        buttonLibro.setStyle("-fx-border-color: grey;");

    }

    @FXML
    public void goToAdmin(ActionEvent event) {
        Stage stage = (Stage) articoloTableView.getScene().getWindow();
        stage.close();
        support.switchStage("homeStageAdmin.fxml", 900, 800);
    }

}