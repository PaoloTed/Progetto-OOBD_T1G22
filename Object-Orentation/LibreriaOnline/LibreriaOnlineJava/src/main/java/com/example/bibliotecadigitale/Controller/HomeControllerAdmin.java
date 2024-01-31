package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.*;
import com.example.bibliotecadigitale.DAO.DAO;
import com.example.bibliotecadigitale.Model.*;
import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HomeControllerAdmin implements Initializable {
    //todo se l'acquisto non ha una conferenza esce zero invece di null, risolvere
    public String scelta = "libro";
    public String sceltaInsertView = "insert";
    @FXML
    private Button buttonCerca;

    @FXML
    private Button buttonInserisci;
    @FXML
    private TextField idBarSearch;
    private SupportStage support = new SupportStage();
    @FXML
    private ComboBox<String> comboBoxTableView;
    @FXML
    private ComboBox<String> comboBoxRicerca;

    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonView;

    //Table view libro
    @FXML
    public TableView<Libro> libroTableView;
    @FXML
    TableColumn<Libro, String> isbnLibro;
    @FXML
    TableColumn<Libro, String> titoloLibro;
    @FXML
    TableColumn<Libro, String> genereLibro;
    @FXML
    TableColumn<Libro, Integer> numPagineLibro;
    @FXML
    TableColumn<Libro, String> materiaLibro;
    @FXML
    TableColumn<Libro, String> descrizioneLibro;
    @FXML
    TableColumn<Libro, String> fruizioneLibro;
    @FXML
    TableColumn<Libro, String> editoreLibro;
    @FXML
    TableColumn<Libro, String> autoreLibro;
    @FXML
    TableColumn<Libro, String> dataUscitaLibro;
    @FXML
    TableColumn<Libro, String> successivoLibro;
    @FXML
    TableColumn<Libro, Integer> serieLibro;
    @FXML
    TableColumn<Libro, Integer> presentazioneLibro;

    //Table view articolo
    @FXML
    public TableView<ArticoloScientifico> articoloTableView;
    @FXML
    TableColumn<ArticoloScientifico, String> doiArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> titoloArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> genereArticolo;
    @FXML
    TableColumn<ArticoloScientifico, Integer> numPagineArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> dataUscitaArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> descrizioneArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> fruizioneArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> editoreArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> autoreArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> linguaArticolo;
    @FXML
    TableColumn<ArticoloScientifico, Integer> conferenzaArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> nomerArticolo;
    @FXML
    TableColumn<ArticoloScientifico, String> datarArticolo;

    //Table view acquisto
    @FXML
    public TableView<Acquisto> acquistoTableView;
    @FXML
    TableColumn<Acquisto, Integer> codaAcquisto;
    @FXML
    TableColumn<Acquisto, String> nomeAcquisto;
    @FXML
    TableColumn<Acquisto, String> tipoAcquisto;
    @FXML
    TableColumn<Acquisto, String> urlAcquisto;
    @FXML
    TableColumn<Acquisto, String> indirizzoAcquisto;

    //Table view conferenza
    @FXML
    public TableView<Conferenza> conferenzaTableView;
    @FXML
    TableColumn<Conferenza, Integer> codcConferenza;
    @FXML
    TableColumn<Conferenza, String> nomeConfernza;
    @FXML
    TableColumn<Conferenza, String> strutturaConferenza;
    @FXML
    TableColumn<Conferenza, String> indirizzoConferenza;
    @FXML
    TableColumn<Conferenza, String> dataiConferenza;
    @FXML
    TableColumn<Conferenza, String> datafConferenza;
    @FXML
    TableColumn<Conferenza, String> responsabileConferenza;

    //Table view presentazione
    @FXML
    public TableView<Presentazione> presentazioneTableView;
    @FXML
    TableColumn<Presentazione, Integer> codpPresentazione;
    @FXML
    TableColumn<Presentazione, String> nomePresentazione;
    @FXML
    TableColumn<Presentazione, String> indirizzoPresentazione;
    @FXML
    TableColumn<Presentazione, String> dataPresentazione;
    @FXML
    TableColumn<Presentazione, String> tipoPresentazione;

    //Table view rivista
    @FXML
    public TableView<Rivista> rivistaTableView;
    @FXML
    TableColumn<Rivista, String> nomeRivista;
    @FXML
    TableColumn<Rivista, String> dataRivista;
    @FXML
    TableColumn<Rivista, String> responsabileRivista;
    @FXML
    TableColumn<Rivista, String> argomentoRivista;

    //Table view serie
    @FXML
    public TableView<Serie> serieTableView;
    @FXML
    TableColumn<Serie, Integer> codsSerie;
    @FXML
    TableColumn<Serie, String> nomeSerie;
    @FXML
    TableColumn<Serie, Integer> numLibriSerie;
    @FXML
    TableColumn<Serie, Boolean> completataSerie;

    //Table view utente
    @FXML
    public TableView<Utente> utenteTableView;
    @FXML
    TableColumn<Utente, String> emailUtente;
    @FXML
    TableColumn<Utente, String> passwordUtente;
    @FXML
    TableColumn<Utente, String> dataIscrizioneUtente;
    @FXML
    TableColumn<Utente, String> isAdminUtente;

    //Table view disponibileA
    @FXML
    public TableView<DisponibileA> disponibileATableView;
    @FXML
    TableColumn<DisponibileA, Integer> codaDisponibileA;
    @FXML
    TableColumn<DisponibileA, String> doiDisponibileA;

    //Table view disponibileL
    @FXML
    public TableView<DisponibileL> disponibileLTableView;
    @FXML
    TableColumn<DisponibileL, Integer> codaDisponibileL;
    @FXML
    TableColumn<DisponibileL, String> isbnDisponibileL;

    //Table view disponibileS
    @FXML
    public TableView<DisponibileS> disponibileSTableView;
    @FXML
    TableColumn<DisponibileS, Integer> codaDisponibileS;
    @FXML
    TableColumn<DisponibileS, Integer> codsDisponibileS;

    @FXML
    private ImageView imageLibriSfondo;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/Images/libri800x900.png"));
        imageLibriSfondo.setImage(image);
        buttonInserisci.setVisible(false);
        //inizializzo le colonne della tabella libro
        isbnLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
        isbnLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnLibro.setEditable(false);
        titoloLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("titolo"));
        titoloLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        genereLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("genere"));
        genereLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        numPagineLibro.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("numPagine"));
        numPagineLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        materiaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("materia"));
        materiaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        descrizioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("descrizione"));
        descrizioneLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        fruizioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("fruizione"));
        fruizioneLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        editoreLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("editore"));
        editoreLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        autoreLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("autore"));
        autoreLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        dataUscitaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("dataUscita"));
        dataUscitaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        successivoLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("successivo"));
        successivoLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        serieLibro.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("serie"));
        serieLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        presentazioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("presentazione"));
        presentazioneLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //inizializzo le colonne della tabella articolo
        doiArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("doi"));
        doiArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        doiArticolo.setEditable(false);
        titoloArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("titolo"));
        titoloArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        genereArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("genere"));
        genereArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        numPagineArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, Integer>("numPagine"));
        numPagineArticolo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dataUscitaArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("dataUscita"));
        dataUscitaArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        descrizioneArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("descrizione"));
        descrizioneArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        fruizioneArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("fruizione"));
        fruizioneArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        editoreArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("editore"));
        editoreArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        autoreArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("autore"));
        autoreArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        linguaArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("lingua"));
        linguaArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        conferenzaArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, Integer>("conferenza"));
        conferenzaArticolo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nomerArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("nomer"));
        nomerArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        nomerArticolo.setEditable(false);
        datarArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("datar"));
        datarArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        datarArticolo.setEditable(false);

        //inizializzo le colonne della tabella acquisto
        codaAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, Integer>("codA"));
        codaAcquisto.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaAcquisto.setEditable(false);
        nomeAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("nome"));
        nomeAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("tipoA"));
        tipoAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());
        urlAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("url"));
        urlAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("indirizzo"));
        indirizzoAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella conferenza
        codcConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, Integer>("codC"));
        codcConferenza.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codcConferenza.setEditable(false);
        nomeConfernza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("nome"));
        nomeConfernza.setCellFactory(TextFieldTableCell.forTableColumn());
        strutturaConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("struttura"));
        strutturaConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("indirizzo"));
        indirizzoConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        dataiConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("dataI"));
        dataiConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        datafConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("dataF"));
        datafConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        responsabileConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("responsabile"));
        responsabileConferenza.setCellFactory(TextFieldTableCell.forTableColumn());


        //inizializzo le colonne della tabella presentazione
        codpPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, Integer>("codP"));
        codpPresentazione.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codpPresentazione.setEditable(false);
        nomePresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("nome"));
        nomePresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("indirizzo"));
        indirizzoPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        dataPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("dataPresentazione"));
        dataPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("tipo"));
        tipoPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella rivista
        nomeRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("nome"));
        nomeRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        nomeRivista.setEditable(false);
        dataRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("data"));
        responsabileRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("responsabile"));
        responsabileRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        argomentoRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("argomento"));
        argomentoRivista.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella serie
        codsSerie.setCellValueFactory(new PropertyValueFactory<Serie, Integer>("codS"));
        codsSerie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codsSerie.setEditable(false);
        nomeSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("nome"));
        nomeSerie.setCellFactory(TextFieldTableCell.forTableColumn());
        numLibriSerie.setCellValueFactory(new PropertyValueFactory<Serie, Integer>("numLibri"));
        completataSerie.setCellValueFactory(new PropertyValueFactory<Serie, Boolean>("completata"));
        completataSerie.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));


        //inizializzo le colonne della tabella utente
        emailUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("email"));
        emailUtente.setCellFactory(TextFieldTableCell.forTableColumn());
        emailUtente.setEditable(false);
        passwordUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("password"));
        passwordUtente.setCellFactory(TextFieldTableCell.forTableColumn());
        dataIscrizioneUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("dataIscrizione"));
        dataIscrizioneUtente.setCellFactory(TextFieldTableCell.forTableColumn());
        isAdminUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("isAdmin"));
        isAdminUtente.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella disponibileA
        codaDisponibileA.setCellValueFactory(new PropertyValueFactory<DisponibileA, Integer>("codA"));
        codaDisponibileA.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaDisponibileA.setEditable(false);
        doiDisponibileA.setCellValueFactory(new PropertyValueFactory<DisponibileA, String>("doi"));
        doiDisponibileA.setCellFactory(TextFieldTableCell.forTableColumn());
        doiDisponibileA.setEditable(false);

        //inizializzo le colonne della tabella disponibileL
        codaDisponibileL.setCellValueFactory(new PropertyValueFactory<DisponibileL, Integer>("codA"));
        codaDisponibileL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaDisponibileL.setEditable(false);
        isbnDisponibileL.setCellValueFactory(new PropertyValueFactory<DisponibileL, String>("isbn"));
        isbnDisponibileL.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnDisponibileL.setEditable(false);

        //inizializzo le colonne della tabella disponibileS
        codaDisponibileS.setCellValueFactory(new PropertyValueFactory<DisponibileS, Integer>("codA"));
        codaDisponibileS.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaDisponibileS.setEditable(false);
        codsDisponibileS.setCellValueFactory(new PropertyValueFactory<DisponibileS, Integer>("codS"));
        codsDisponibileS.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codsDisponibileS.setEditable(false);

        //Imposto la ricerca su libro come default e nascondo la tabella articolo
        setVisibleFalseAllTableView();
        buttonView.setStyle("-fx-border-color: red;");
        buttonView.setDisable(true);
        buttonInsert.setStyle("-fx-border-color: grey;");

        setVisibleFalseAllTableView();
        libroTableView.setVisible(true);
        comboBoxTableView.setItems(FXCollections.observableArrayList("Libro", "Articolo", "Acquisto", "Conferenza", "Presentazione", "Rivista", "Serie", "Utente", "DisponibileA", "DisponibileL", "DisponibileS"));
        comboBoxTableView.getSelectionModel().selectFirst();
        comboBoxRicerca.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Materia", "Descrizione", "Fruizione", "Successivo", "Serie", "Presentazione", "Lingua"));
        comboBoxRicerca.getSelectionModel().selectFirst();

    }

    @FXML
    private void Select(ActionEvent event) {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }

        String modRicerca = comboBoxRicerca.getSelectionModel().getSelectedItem();
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
        switch (scelta) {
            case "Libro":
                LibroDAOImpl libroDAO = new LibroDAOImpl();
                ArrayList<Libro> libri = libroDAO.getRicerca(modRicerca, titoloRicerche);
                libroTableView.getItems().clear();
                libroTableView.setEditable(true);
                idBarSearch.clear();
                if (libri.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }

                libroTableView.getItems().addAll(libri);
                break;
            case "Articolo":
                ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
                ArrayList<ArticoloScientifico> articoli = articoloScientificoDAO.getRicerca(modRicerca, titoloRicerche);
                idBarSearch.clear();
                articoloTableView.getItems().clear();
                if (articoli.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                articoloTableView.getItems().addAll(articoli);
                break;
            case "Acquisto":
                AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
                ArrayList<Acquisto> acquisti = acquistoDAO.getRicerca(modRicerca, titoloRicerche);
                acquistoTableView.getItems().clear();
                idBarSearch.clear();
                if (acquisti.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                acquistoTableView.getItems().addAll(acquisti);
                break;
            case "Conferenza":
                ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
                ArrayList<Conferenza> conferenze = conferenzaDAO.getRicerca(modRicerca, titoloRicerche);
                idBarSearch.clear();
                conferenzaTableView.getItems().clear();
                if (conferenze.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                conferenzaTableView.getItems().addAll(conferenze);
                break;
            case "Presentazione":
                PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
                ArrayList<Presentazione> presentazioni = presentazioneDAO.getRicerca(modRicerca, titoloRicerche);
                presentazioneTableView.getItems().clear();
                idBarSearch.clear();
                if (presentazioni.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                presentazioneTableView.getItems().addAll(presentazioni);
                break;
            case "Rivista":
                RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
                ArrayList<Rivista> riviste = rivistaDAO.getRicerca(modRicerca, titoloRicerche);
                rivistaTableView.getItems().clear();
                idBarSearch.clear();
                if (riviste.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                rivistaTableView.getItems().addAll(riviste);
                break;
            case "Serie":
                SerieDAOImpl serieDAO = new SerieDAOImpl();
                ArrayList<Serie> serie = serieDAO.getRicerca(modRicerca, titoloRicerche);
                serieTableView.getItems().clear();
                idBarSearch.clear();
                if (serie.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                serieTableView.getItems().addAll(serie);
                break;
            case "Utente":
                UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
                ArrayList<Utente> utenti = utenteDAO.getRicerca(modRicerca, titoloRicerche);
                idBarSearch.clear();
                utenteTableView.getItems().clear();
                if (utenti.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                utenteTableView.getItems().addAll(utenti);
                break;
            case "DisponibileA":
                DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
                ArrayList<DisponibileA> disponibileA = disponibileADAO.getRicerca(modRicerca, titoloRicerche);
                idBarSearch.clear();
                disponibileATableView.getItems().clear();
                if (disponibileA.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                disponibileATableView.getItems().addAll(disponibileA);
                break;
            case "DisponibileL":
                DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
                ArrayList<DisponibileL> disponibileL = disponibileLDAO.getRicerca(modRicerca, titoloRicerche);
                disponibileLTableView.getItems().clear();
                idBarSearch.clear();
                if (disponibileL.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                disponibileLTableView.getItems().addAll(disponibileL);
                break;
            case "DisponibileS":
                DisponibileSDAOImpl disponibileSDAO = new DisponibileSDAOImpl();
                ArrayList<DisponibileS> disponibileS = disponibileSDAO.getRicerca(modRicerca, titoloRicerche);
                disponibileSTableView.getItems().clear();
                idBarSearch.clear();
                if (disponibileS.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    return;
                }
                disponibileSTableView.getItems().addAll(disponibileS);
                break;
        }

    }

    @FXML
    private void logOff(ActionEvent event) {
        support.switchStage("welcomeStage.fxml", event);
    }

    private void setVisibleFalseAllTableView() {
        libroTableView.setVisible(false);
        articoloTableView.setVisible(false);
        acquistoTableView.setVisible(false);
        conferenzaTableView.setVisible(false);
        presentazioneTableView.setVisible(false);
        rivistaTableView.setVisible(false);
        serieTableView.setVisible(false);
        utenteTableView.setVisible(false);
        disponibileATableView.setVisible(false);
        disponibileLTableView.setVisible(false);
        disponibileSTableView.setVisible(false);
    }
    @FXML
    private void sceltaInsert(){
        sceltaInsertView = "insert";
        buttonInserisci.setVisible(true);
        buttonInsert.setDisable(true);
        buttonInsert.setStyle("-fx-border-color: red;");
        idBarSearch.setDisable(true);
        comboBoxRicerca.setDisable(true);
        buttonCerca.setDisable(true);

        buttonView.setDisable(false);
        buttonView.setStyle("-fx-border-color: grey;");
        insertScelta();
    }

    @FXML
    private void sceltaView(){
        sceltaInsertView = "view";
        buttonInserisci.setVisible(false);
        buttonInsert.setDisable(false);
        buttonInsert.setStyle("-fx-border-color: grey;");
        idBarSearch.setDisable(false);
        comboBoxRicerca.setDisable(false);
        buttonCerca.setDisable(false);

        buttonView.setDisable(true);
        buttonView.setStyle("-fx-border-color: red;");
        viewScelta();
    }

    public void selezioneSceltaTableView(ActionEvent event) {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
            setVisibleFalseAllTableView();
            switch (scelta) {
                case "Libro":
                    libroTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Materia", "Descrizione", "Fruizione", "Successivo", "Serie", "Presentazione"));
                    break;
                case "Articolo":
                    articoloTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Doi", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Lingua", "Conferenza", "Nomer", "Datar"));
                    break;
                case "Acquisto":
                    acquistoTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Coda", "Nome", "Tipo", "Url", "Indirizzo"));
                    break;
                case "Conferenza":
                    conferenzaTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Codc", "Nome", "Struttura", "Indirizzo", "Datai", "Dataf", "Responsabile"));
                    break;
                case "Presentazione":
                    presentazioneTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Codp", "Nome", "Indirizzo", "Data", "Tipo"));
                    break;
                case "Rivista":
                    rivistaTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Nome", "Data", "Responsabile", "Argomento"));
                    break;
                case "Serie":
                    serieTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Cods", "Nome", "NumLibri", "Completata"));
                    break;
                case "Utente":
                    utenteTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Email", "Password", "DataIscrizione", "IsAdmin"));
                    break;
                case "DisponibileA":
                    disponibileATableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Coda", "Doi"));
                    break;
                case "DisponibileL":
                    disponibileLTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Coda", "Isbn"));
                    break;
                case "DisponibileS":
                    disponibileSTableView.setVisible(true);
                    comboBoxRicerca.setItems(FXCollections.observableArrayList("Coda", "Cods"));
                    break;

            }
            comboBoxRicerca.getSelectionModel().selectFirst();
         if (sceltaInsertView.equals("insert")) {
            insertScelta();
        } else if (sceltaInsertView.equals("view")) {
            viewScelta();
         }
    }


    @FXML
    private void deleteDao() {
        try {
            String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
            if (scelta == null) {
                support.messageStage("Selezionare prima un tipo di ricerca");
                return;
            }

            switch (scelta) {
                case "Libro":
                    LibroDAOImpl libroDAO = new LibroDAOImpl();
                    Libro libroDelete = libroTableView.getSelectionModel().getSelectedItem();
                    libroTableView.getItems().remove(libroDelete);
                    libroDAO.delete(libroDelete);
                    break;
                case "Articolo":
                    ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
                    ArticoloScientifico articoloScientificoDelete = articoloTableView.getSelectionModel().getSelectedItem();
                    articoloTableView.getItems().remove(articoloScientificoDelete);
                    articoloScientificoDAO.delete(articoloScientificoDelete);
                    break;
                case "Acquisto":
                    AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
                    Acquisto acquistoDelete = acquistoTableView.getSelectionModel().getSelectedItem();
                    acquistoTableView.getItems().remove(acquistoDelete);
                    acquistoDAO.delete(acquistoDelete);
                    break;
                case "Conferenza":
                    ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
                    Conferenza conferenzaDelete = conferenzaTableView.getSelectionModel().getSelectedItem();
                    conferenzaTableView.getItems().remove(conferenzaDelete);
                    conferenzaDAO.delete(conferenzaDelete);
                    break;
                case "Presentazione":
                    PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
                    Presentazione presentazioneDelete = presentazioneTableView.getSelectionModel().getSelectedItem();
                    presentazioneTableView.getItems().remove(presentazioneDelete);
                    presentazioneDAO.delete(presentazioneDelete);
                    break;
                case "Rivista":
                    RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
                    Rivista rivistaDelete = rivistaTableView.getSelectionModel().getSelectedItem();
                    rivistaTableView.getItems().remove(rivistaDelete);
                    rivistaDAO.delete(rivistaDelete);
                    break;
                case "Serie":
                    SerieDAOImpl serieDAO = new SerieDAOImpl();
                    Serie serieDelete = serieTableView.getSelectionModel().getSelectedItem();
                    serieTableView.getItems().remove(serieDelete);
                    serieDAO.delete(serieDelete);
                    break;
                case "Utente":
                    UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
                    Utente utenteDelete = utenteTableView.getSelectionModel().getSelectedItem();
                    utenteTableView.getItems().remove(utenteDelete);
                    utenteDAO.delete(utenteDelete);
                    break;
                case "DisponibileA":
                    DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
                    DisponibileA disponibileADelete = disponibileATableView.getSelectionModel().getSelectedItem();
                    disponibileATableView.getItems().remove(disponibileADelete);
                    disponibileADAO.delete(disponibileADelete);
                    break;
                case "DisponibileL":
                    DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
                    DisponibileL disponibileLDelete = disponibileLTableView.getSelectionModel().getSelectedItem();
                    disponibileLTableView.getItems().remove(disponibileLDelete);
                    disponibileLDAO.delete(disponibileLDelete);
                    break;
                case "DisponibileS":
                    DisponibileSDAOImpl disponibileSDAO = new DisponibileSDAOImpl();
                    DisponibileS disponibileSDelete = disponibileSTableView.getSelectionModel().getSelectedItem();
                    disponibileSTableView.getItems().remove(disponibileSDelete);
                    disponibileSDAO.delete(disponibileSDelete);
                    break;
            }
            support.messageStage("Delete effettuato");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void insertScelta() {
        //todo fix non si rimpiccilisce quando cambi di nuovo scelta ricerca
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        buttonInserisci.setVisible(true);
        setVisibleFalseAllTableView();
        try {
            switch (scelta) {
                case "Libro":
                    System.out.println("Libro");
                    libroTableView.getItems().clear();
                    libroTableView.setVisible(true);
                    libroTableView.setPrefHeight(70);
                    libroTableView.getItems().add(new Libro());
                    isbnLibro.setEditable(true);
                    break;
                case "Articolo":
                    System.out.println("Articolo");
                    articoloTableView.getItems().clear();
                    articoloTableView.setVisible(true);
                    articoloTableView.setPrefHeight(70);
                    articoloTableView.getItems().add(new ArticoloScientifico());
                    doiArticolo.setEditable(true);
                    nomerArticolo.setEditable(true);
                    datarArticolo.setEditable(true);
                    break;
                case "Acquisto":
                    System.out.println("Acquisto");
                    acquistoTableView.getItems().clear();
                    acquistoTableView.setVisible(true);
                    acquistoTableView.setPrefHeight(70);
                    acquistoTableView.getItems().add(new Acquisto());
                    codaAcquisto.setEditable(true);
                    break;
                case "Conferenza":
                    System.out.println("Conferenza");
                    conferenzaTableView.getItems().clear();
                    conferenzaTableView.setVisible(true);
                    conferenzaTableView.setPrefHeight(70);
                    conferenzaTableView.getItems().add(new Conferenza());
                    codcConferenza.setEditable(true);
                    break;
                case "Presentazione":
                    System.out.println("Presentazione");
                    presentazioneTableView.getItems().clear();
                    presentazioneTableView.setVisible(true);
                    presentazioneTableView.setPrefHeight(70);
                    presentazioneTableView.getItems().add(new Presentazione());
                    codpPresentazione.setEditable(true);
                    break;
                case "Rivista":
                    System.out.println("Rivista");
                    rivistaTableView.getItems().clear();
                    rivistaTableView.setVisible(true);
                    rivistaTableView.setPrefHeight(70);
                    rivistaTableView.getItems().add(new Rivista());
                    nomeRivista.setEditable(true);
                    dataRivista.setEditable(true);
                    break;
                case "Serie":
                    System.out.println("Serie");
                    serieTableView.getItems().clear();
                    serieTableView.setVisible(true);
                    serieTableView.setPrefHeight(70);
                    serieTableView.getItems().add(new Serie());
                    codsSerie.setEditable(true);
                    break;
                case "DisponibileA":
                    System.out.println("DisponibileA");
                    disponibileATableView.getItems().clear();
                    disponibileATableView.setVisible(true);
                    disponibileATableView.setPrefHeight(70);
                    disponibileATableView.getItems().add(new DisponibileA());
                    codaDisponibileA.setEditable(true);
                    doiDisponibileA.setEditable(true);
                    break;
                case "DisponibileL":
                    System.out.println("DisponibileL");
                    disponibileLTableView.getItems().clear();
                    disponibileLTableView.setVisible(true);
                    disponibileLTableView.setPrefHeight(70);
                    disponibileLTableView.getItems().add(new DisponibileL());
                    codaDisponibileL.setEditable(true);
                    isbnDisponibileL.setEditable(true);
                    break;
                case "DisponibileS":
                    System.out.println("DisponibileS");
                    disponibileSTableView.getItems().clear();
                    disponibileSTableView.setVisible(true);
                    disponibileSTableView.setPrefHeight(70);
                    disponibileSTableView.getItems().add(new DisponibileS());
                    codaDisponibileS.setEditable(true);
                    codsDisponibileS.setEditable(true);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void viewScelta(){
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        switch (scelta){
            case "Libro":
                libroTableView.getItems().clear();
                libroTableView.setPrefHeight(535);
                isbnLibro.setEditable(false);
                break;
            case "Articolo":
                articoloTableView.getItems().clear();
                articoloTableView.setPrefHeight(535);
                doiArticolo.setEditable(false);
                nomerArticolo.setEditable(false);
                datarArticolo.setEditable(false);
                break;
            case "Acquisto":
                acquistoTableView.getItems().clear();
                acquistoTableView.setPrefHeight(535);
                codaAcquisto.setEditable(false);
                break;
            case "Conferenza":
                conferenzaTableView.getItems().clear();
                conferenzaTableView.setPrefHeight(535);
                codcConferenza.setEditable(false);
                break;
            case "Presentazione":
                presentazioneTableView.getItems().clear();
                presentazioneTableView.setPrefHeight(535);
                codpPresentazione.setEditable(false);
                break;
            case "Rivista":
                rivistaTableView.getItems().clear();
                rivistaTableView.setPrefHeight(535);
                nomeRivista.setEditable(false);
                dataRivista.setEditable(false);
                break;
            case "Serie":
                serieTableView.getItems().clear();
                serieTableView.setPrefHeight(535);
                codsSerie.setEditable(false);
                break;
            case "DisponibileA":
                disponibileATableView.getItems().clear();
                disponibileATableView.setPrefHeight(535);
                codaDisponibileA.setEditable(false);
                doiDisponibileA.setEditable(false);
                break;
            case "DisponibileL":
                disponibileLTableView.getItems().clear();
                disponibileLTableView.setPrefHeight(535);
                codaDisponibileL.setEditable(false);
                isbnDisponibileL.setEditable(false);
                break;
            case "DisponibileS":
                disponibileSTableView.getItems().clear();
                disponibileSTableView.setPrefHeight(535);
                codaDisponibileS.setEditable(false);
                codsDisponibileS.setEditable(false);
                break;
        }
    }

    @FXML
    private void insertDao(){
        try {
            switch (scelta) {
                case "Libro":
                    LibroDAOImpl libroDAO = new LibroDAOImpl();
                    libroDAO.insert(libroTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Articolo":
                    ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
                    articoloScientificoDAO.insert(articoloTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Acquisto":
                    AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
                    acquistoDAO.insert(acquistoTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Conferenza":
                    ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
                    conferenzaDAO.insert(conferenzaTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Presentazione":
                    PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
                    presentazioneDAO.insert(presentazioneTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Rivista":
                    RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
                    rivistaDAO.insert(rivistaTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Serie":
                    SerieDAOImpl serieDAO = new SerieDAOImpl();
                    serieDAO.insert(serieTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Utente":
                    UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
                    utenteDAO.insert(utenteTableView.getSelectionModel().getSelectedItem());
                    break;
                case "DisponibileA":
                    DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
                    disponibileADAO.insert(disponibileATableView.getSelectionModel().getSelectedItem());
                    break;
                case "DisponibileL":
                    DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
                    disponibileLDAO.insert(disponibileLTableView.getSelectionModel().getSelectedItem());
                    break;
                case "DisponibileS":
                    DisponibileSDAOImpl disponibileSDAO = new DisponibileSDAOImpl();
                    disponibileSDAO.insert(disponibileSTableView.getSelectionModel().getSelectedItem());
                    break;
            }
            support.messageStage("Insert effettuato");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void updateDao() {
        try {
            switch (scelta) {
                case "Libro":
                    LibroDAOImpl libroDAO = new LibroDAOImpl();
                    libroDAO.update(libroTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Articolo":
                    ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
                    articoloScientificoDAO.update(articoloTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Acquisto":
                    AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
                    acquistoDAO.update(acquistoTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Conferenza":
                    ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
                    conferenzaDAO.update(conferenzaTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Presentazione":
                    PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
                    presentazioneDAO.update(presentazioneTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Rivista":
                    RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
                    rivistaDAO.update(rivistaTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Serie":
                    SerieDAOImpl serieDAO = new SerieDAOImpl();
                    serieDAO.update(serieTableView.getSelectionModel().getSelectedItem());
                    break;
                case "Utente":
                    UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
                    utenteDAO.update(utenteTableView.getSelectionModel().getSelectedItem());
                    break;
                case "DisponibileA":
                    DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
                    disponibileADAO.update(disponibileATableView.getSelectionModel().getSelectedItem());
                    break;
                case "DisponibileL":
                    DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
                    disponibileLDAO.update(disponibileLTableView.getSelectionModel().getSelectedItem());
                    break;
                case "DisponibileS":
                    DisponibileSDAOImpl disponibileSDAO = new DisponibileSDAOImpl();
                    disponibileSDAO.update(disponibileSTableView.getSelectionModel().getSelectedItem());
                    break;
            }
            support.messageStage("Update effettuato");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEditChangedLibroString(TableColumn.CellEditEvent<Libro, String> libroStringCellEditEvent) {
        System.out.println("Modifica");
        Libro libro = libroTableView.getSelectionModel().getSelectedItem();
        libroTableView.getItems().remove(libro);
        String tipoColumn = libroStringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = libroStringCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "isbnLibro":
                libro.setISBN(valoreColumnString);
                break;
            case "titoloLibro":
                libro.setTitolo(valoreColumnString);
                break;
            case "genereLibro":
                libro.setGenere(valoreColumnString);
                break;
            case "tipoLibro":
                libro.setTipo(valoreColumnString);
                break;
            case "materiaLibro":
                libro.setMateria(valoreColumnString);
                break;
            case "descrizioneLibro":
                libro.setDescrizione(valoreColumnString);
                break;
            case "fruizioneLibro":
                libro.setFruizione(valoreColumnString);
                break;
            case "editoreLibro":
                libro.setEditore(valoreColumnString);
                break;
            case "autoreLibro":
                libro.setAutore(valoreColumnString);
                break;
            case "dataUscitaLibro":
                libro.setDataUscita(valoreColumnString);
                break;
            case "linguaLibro":
                libro.setLingua(valoreColumnString);
                break;
            case "successivoLibro":
                libro.setSuccessivo(valoreColumnString);
                break;
        }
        libroTableView.getItems().add(libro);
        libroTableView.refresh();
    }

    @FXML
    private void onEditChangedLibroInt(TableColumn.CellEditEvent<Libro, Integer> libroIntegerCellEditEvent) {
        Libro libro = libroTableView.getSelectionModel().getSelectedItem();
        libroTableView.getItems().remove(libro);
        String tipoColumn = libroIntegerCellEditEvent.getTableColumn().getId();
        Integer valoreColumnInt = libroIntegerCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "numPagineLibro":
                libro.setNumPagine(valoreColumnInt);
                break;
            case "serieLibro":
                libro.setSerie(valoreColumnInt);
                break;
            case "presentazioneLibro":
                libro.setPresentazione(valoreColumnInt);
                break;
        }
        libroTableView.getItems().add(libro);
        libroTableView.refresh();
    }

    @FXML
    private void onEditChangedAcquistoString(TableColumn.CellEditEvent<Acquisto, String> acquistoStringCellEditEvent) {
        System.out.println("Modifica");
        Acquisto acquisto = acquistoTableView.getSelectionModel().getSelectedItem();
        acquistoTableView.getItems().remove(acquisto);
        String tipoColumn = acquistoStringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = acquistoStringCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "nomeAcquisto":
                acquisto.setNome(valoreColumnString);
                break;
            case "tipoAcquisto":
                acquisto.setTipoA(valoreColumnString);
                break;
            case "urlAcquisto":
                acquisto.setUrl(valoreColumnString);
                break;
            case "indirizzoAcquisto":
                acquisto.setIndirizzo(valoreColumnString);
                break;
        }
        acquistoTableView.getItems().add(acquisto);
        acquistoTableView.refresh();
    }

    @FXML
    private void onEditChangedAcquistoInt(TableColumn.CellEditEvent<Acquisto, Integer> acquistoIntegerCellEditEvent) {
        Acquisto acquisto = acquistoTableView.getSelectionModel().getSelectedItem();
        acquistoTableView.getItems().remove(acquisto);
        Integer valoreColumnInt = acquistoIntegerCellEditEvent.getNewValue();
        acquisto.setCodA(valoreColumnInt);
        acquistoTableView.getItems().add(acquisto);
        acquistoTableView.refresh();
    }

    @FXML
    private void onEditChangedArticoloScientificoString(TableColumn.CellEditEvent<ArticoloScientifico, String> articoloScientificoStringCellEditEvent) {
        ArticoloScientifico articoloScientifico = articoloTableView.getSelectionModel().getSelectedItem();
        articoloTableView.getItems().remove(articoloScientifico);
        String tipoColumn = articoloScientificoStringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = articoloScientificoStringCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "doiArticolo":
                articoloScientifico.setDoi(valoreColumnString);
                break;
            case "titoloArticolo":
                articoloScientifico.setTitolo(valoreColumnString);
                break;
            case "genereArticolo":
                articoloScientifico.setGenere(valoreColumnString);
                break;
            case "linguaArticolo":
                articoloScientifico.setLingua(valoreColumnString);
                break;
            case "descrizioneArticolo":
                articoloScientifico.setDescrizione(valoreColumnString);
                break;
            case "fruizioneArticolo":
                articoloScientifico.setFruizione(valoreColumnString);
                break;
            case "editoreArticolo":
                articoloScientifico.setEditore(valoreColumnString);
                break;
            case "autoreArticolo":
                articoloScientifico.setAutore(valoreColumnString);
                break;
            case "dataUscitaArticolo":
                articoloScientifico.setDataUscita(valoreColumnString);
                break;
            case "nomerArticolo":
                articoloScientifico.setNomer(valoreColumnString);
                break;
            case "datarArticolo":
                articoloScientifico.setDatar(valoreColumnString);
                break;
        }
        articoloTableView.getItems().add(articoloScientifico);
        articoloTableView.refresh();
    }

    @FXML
    private void onEditChangedArticoloScientificoInt(TableColumn.CellEditEvent<ArticoloScientifico, Integer> articoloScientificoIntegerCellEditEvent) {
        ArticoloScientifico articoloScientifico = articoloTableView.getSelectionModel().getSelectedItem();
        articoloTableView.getItems().remove(articoloScientifico);
        String tipoColumn = articoloScientificoIntegerCellEditEvent.getTableColumn().getId();
        Integer valoreColumnInt = articoloScientificoIntegerCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "numPagineArticolo":
                articoloScientifico.setNumPagine(valoreColumnInt);
                break;
            case "conferenzaArticolo":
                articoloScientifico.setConferenza(valoreColumnInt);
                break;
        }
        articoloTableView.getItems().add(articoloScientifico);
        articoloTableView.refresh();
    }

    @FXML
    private void onEditChangedConferenzaString(TableColumn.CellEditEvent<Conferenza, String> conferenzaStringCellEditEvent) {
        Conferenza conferenza = conferenzaTableView.getSelectionModel().getSelectedItem();
        conferenzaTableView.getItems().remove(conferenza);
        String tipoColumn = conferenzaStringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = conferenzaStringCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "nomeConfernza":
                conferenza.setNome(valoreColumnString);
                break;
            case "strutturaConferenza":
                conferenza.setStruttura(valoreColumnString);
                break;
            case "indirizzoConferenza":
                conferenza.setIndirizzo(valoreColumnString);
                break;
            case "dataiConferenza":
                conferenza.setDataI(valoreColumnString);
                break;
            case "datafConferenza":
                conferenza.setDataF(valoreColumnString);
                break;
            case "responsabileConferenza":
                conferenza.setResponsabile(valoreColumnString);
                break;
        }
        conferenzaTableView.getItems().add(conferenza);
        conferenzaTableView.refresh();
    }

    @FXML
    private void onEditChangedConferenzaInt(TableColumn.CellEditEvent<Conferenza, Integer> conferenzaIntegerCellEditEvent) {
        Conferenza conferenza = conferenzaTableView.getSelectionModel().getSelectedItem();
        conferenzaTableView.getItems().remove(conferenza);
        Integer valoreColumnInt = conferenzaIntegerCellEditEvent.getNewValue();
        conferenza.setCodC(valoreColumnInt);
        conferenzaTableView.getItems().add(conferenza);
        conferenzaTableView.refresh();
    }


    @FXML
    private void onEditChangedPresentazioneString(TableColumn.CellEditEvent<Presentazione, String> presentazioneStringCellEditEvent) {
        Presentazione presentazione = presentazioneTableView.getSelectionModel().getSelectedItem();
        presentazioneTableView.getItems().remove(presentazione);
        String tipoColumn = presentazioneStringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = presentazioneStringCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "nomePresentazione":
                presentazione.setNome(valoreColumnString);
                break;
            case "indirizzoPresentazione":
                presentazione.setIndirizzo(valoreColumnString);
                break;
            case "dataPresentazione":
                presentazione.setDataPresentazione(valoreColumnString);
                break;
            case "tipoPresentazione":
                presentazione.setTipo(valoreColumnString);
                break;
        }
        presentazioneTableView.getItems().add(presentazione);
        presentazioneTableView.refresh();
    }

    @FXML
    private void onEditChangedPresentazioneInt(TableColumn.CellEditEvent<Presentazione, Integer> presentazioneIntegerCellEditEvent) {
        Presentazione presentazione = presentazioneTableView.getSelectionModel().getSelectedItem();
        presentazioneTableView.getItems().remove(presentazione);
        Integer valoreColumnInt = presentazioneIntegerCellEditEvent.getNewValue();
        presentazione.setCodP(valoreColumnInt);
        presentazioneTableView.getItems().add(presentazione);
        presentazioneTableView.refresh();
    }

    @FXML
    private void onEditChangedRivistaString(TableColumn.CellEditEvent<Rivista, String> rivistaStringCellEditEvent) {
        Rivista rivista = rivistaTableView.getSelectionModel().getSelectedItem();
        rivistaTableView.getItems().remove(rivista);
        String tipoColumn = rivistaStringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = rivistaStringCellEditEvent.getNewValue();
        switch (tipoColumn) {
            case "nomeRivista":
                rivista.setNome(valoreColumnString);
                break;
            case "dataRivista":
                rivista.setData(valoreColumnString);
                break;
            case "responsabileRivista":
                rivista.setResponsabile(valoreColumnString);
                break;
            case "argomentoRivista":
                rivista.setArgomento(valoreColumnString);
                break;
        }
        rivistaTableView.getItems().add(rivista);
        rivistaTableView.refresh();
    }

    @FXML
    private void onEditChangedSerieString(TableColumn.CellEditEvent<Serie, String> serieStringCellEditEvent) {
        Serie serie = serieTableView.getSelectionModel().getSelectedItem();
        serieTableView.getItems().remove(serie);
        String valoreColumnString = serieStringCellEditEvent.getNewValue();
        serie.setNome(valoreColumnString);
        serieTableView.getItems().add(serie);
        serieTableView.refresh();
    }

    @FXML
    private void onEditChangedSerieBool(TableColumn.CellEditEvent<Serie, Boolean> serieStringCellEditEvent) {
        Serie serie = serieTableView.getSelectionModel().getSelectedItem();
        serieTableView.getItems().remove(serie);
        boolean valoreColumnBool = serieStringCellEditEvent.getNewValue();
        serie.setCompletata(valoreColumnBool);
        serieTableView.getItems().add(serie);
        serieTableView.refresh();
    }

    @FXML
    private void onEditChangedSerieInt(TableColumn.CellEditEvent<Serie, Integer> serieIntegerCellEditEvent) {
        Serie serie = serieTableView.getSelectionModel().getSelectedItem();
        serieTableView.getItems().remove(serie);
        Integer valoreColumnInt = serieIntegerCellEditEvent.getNewValue();
        String tipoColumn = serieIntegerCellEditEvent.getTableColumn().getId();
        switch (tipoColumn) {
            case "codsSerie":
                serie.setCodS(valoreColumnInt);
                break;
            case "numLibriSerie":
                serie.setNumLibri(valoreColumnInt);
                break;
        }
        serieTableView.getItems().add(serie);
        serieTableView.refresh();
    }

    @FXML
    private void onEditChangedUtenteString(TableColumn.CellEditEvent<Utente, String> utenteStringCellEditEvent) {
        Utente utente = utenteTableView.getSelectionModel().getSelectedItem();
        utenteTableView.getItems().remove(utente);
        String valoreColumnString = utenteStringCellEditEvent.getNewValue();
        String tipoColumn = utenteStringCellEditEvent.getTableColumn().getId();
        switch (tipoColumn) {
            case "emailUtente":
                utente.setEmail(valoreColumnString);
                break;
            case "passwordUtente":
                utente.setPassword(valoreColumnString);
                break;
            case "dataIscrizioneUtente":
                utente.setData(valoreColumnString);
                break;
        }
        utenteTableView.getItems().add(utente);
        utenteTableView.refresh();
    }
}