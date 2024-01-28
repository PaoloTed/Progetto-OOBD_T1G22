package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.*;
import com.example.bibliotecadigitale.DAO.DAO;
import com.example.bibliotecadigitale.Model.*;
import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
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

public class HomeControllerAdminTest implements Initializable {
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

    private HashMap<String, TableView> tableViewHashMap = new HashMap<String, TableView>();
    private HashMap<String, ObservableList<String>> ricercaHashMap = new HashMap<String, ObservableList<String>>();
    private HashMap<String, DAO> implDaoHashMap = new HashMap<String, DAO>();

    private HashMap<String, Object> objectHashMap = new HashMap<String, Object>();

    @FXML
    private ImageView imageLibriSfondo;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(getClass().getResourceAsStream("/Images/libri800x900.png")));
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
        buttonView.setStyle("-fx-border-color: red;");
        buttonView.setDisable(true);
        buttonInsert.setStyle("-fx-border-color: grey;");

        setVisibleFalseAllTableView();
        libroTableView.setVisible(true);
        disponibileSTableView.setVisible(false);
        comboBoxTableView.setItems(FXCollections.observableArrayList("Libro", "Articolo", "Acquisto", "Conferenza", "Presentazione", "Rivista", "Serie", "DisponibileA", "DisponibileL", "DisponibileS"));
        comboBoxTableView.getSelectionModel().selectFirst();
        comboBoxRicerca.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Materia", "Descrizione", "Fruizione", "Successivo", "Serie", "Presentazione", "Lingua"));
        comboBoxRicerca.getSelectionModel().selectFirst();


        objectHashMap.put("Libro", new Libro());
        objectHashMap.put("Articolo", new ArticoloScientifico());
        objectHashMap.put("Acquisto", new Acquisto());
        objectHashMap.put("Conferenza", new Conferenza());
        objectHashMap.put("Presentazione", new Presentazione());
        objectHashMap.put("Rivista", new Rivista());
        objectHashMap.put("Serie", new Serie());
        objectHashMap.put("DisponibileA", new DisponibileA());
        objectHashMap.put("DisponibileL", new DisponibileL());
        objectHashMap.put("DisponibileS", new DisponibileS());

        implDaoHashMap.put("Libro", new LibroDAOImpl());
        implDaoHashMap.put("Articolo", new ArticoloScientificoDAOImpl());
        implDaoHashMap.put("Acquisto", new AcquistoDAOImpl());
        implDaoHashMap.put("Conferenza", new ConferenzaDAOImpl());
        implDaoHashMap.put("Presentazione", new PresentazioneDAOImpl());
        implDaoHashMap.put("Rivista", new RivistaDAOImpl());
        implDaoHashMap.put("Serie", new SerieDAOImpl());
        implDaoHashMap.put("DisponibileA", new DisponibileADAOImpl());
        implDaoHashMap.put("DisponibileL", new DisponibileLDAOImpl());
        implDaoHashMap.put("DisponibileS", new DisponibileSDAOImpl());

        tableViewHashMap.put("Libro", libroTableView);
        tableViewHashMap.put("Articolo", articoloTableView);
        tableViewHashMap.put("Acquisto", acquistoTableView);
        tableViewHashMap.put("Conferenza", conferenzaTableView);
        tableViewHashMap.put("Presentazione", presentazioneTableView);
        tableViewHashMap.put("Rivista", rivistaTableView);
        tableViewHashMap.put("Serie", serieTableView);
        tableViewHashMap.put("DisponibileA", disponibileATableView);
        tableViewHashMap.put("DisponibileL", disponibileLTableView);
        tableViewHashMap.put("DisponibileS", disponibileSTableView);

        ricercaHashMap.put("Libro", (FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Materia", "Descrizione", "Fruizione", "Successivo", "Serie", "Presentazione")));
        ricercaHashMap.put("Articolo", (FXCollections.observableArrayList("Doi", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Lingua", "Conferenza", "Nomer", "Datar")));
        ricercaHashMap.put("Acquisto", (FXCollections.observableArrayList("Coda", "Nome", "Tipo", "Url", "Indirizzo")));
        ricercaHashMap.put("Conferenza", (FXCollections.observableArrayList("Codc", "Nome", "Struttura", "Indirizzo", "Datai", "Dataf", "Responsabile")));
        ricercaHashMap.put("Presentazione", (FXCollections.observableArrayList("Codp", "Nome", "Indirizzo", "Data", "Tipo")));
        ricercaHashMap.put("Rivista", (FXCollections.observableArrayList("Nome", "Data", "Responsabile", "Argomento")));
        ricercaHashMap.put("Serie", (FXCollections.observableArrayList("Cods", "Nome", "NumLibri", "Completata")));
        ricercaHashMap.put("DisponibileA", FXCollections.observableArrayList("Coda", "Doi"));
        ricercaHashMap.put("DisponibileL", FXCollections.observableArrayList("Coda", "Isbn"));
        ricercaHashMap.put("DisponibileS", FXCollections.observableArrayList("Coda", "Cods"));

        sceltaSetter("insert");
//        cellEditEventHashMap = new HashMap<String, TableColumn.CellEditEvent>();
//        cellEditEventHashMap.put("Libro", new TableColumn.CellEditEvent<Libro,String>(libroTableView,);
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
        //todo Non serve si puo levare arraylistHashMap
        ArrayList arrayList = implDaoHashMap.get(scelta).getRicerca(modRicerca, titoloRicerche);
        //arrayListHashMap.put(scelta, implDaoHashMap.get(scelta).getRicerca(modRicerca, titoloRicerche));
        tableViewHashMap.get(scelta).getItems().clear();
        tableViewHashMap.get(scelta).setVisible(true);
        idBarSearch.clear();
        if (arrayList.isEmpty()) {
            support.messageStage("Nessun match trovato");
            return;
        }
        tableViewHashMap.get(scelta).getItems().addAll(arrayList);

    }

    @FXML
    private void logOff(ActionEvent event) throws IOException {
        Utente.getUtente().exitUtente();
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
        disponibileATableView.setVisible(false);
        disponibileLTableView.setVisible(false);
        disponibileSTableView.setVisible(false);
    }

    @FXML
    private void sceltaInsert() {
        sceltaInsertView = "insert";
        sceltaSetter(sceltaInsertView);
    }

    @FXML
    private void sceltaView() {
        sceltaInsertView = "view";
        sceltaSetter(sceltaInsertView);
    }

    private void sceltaSetter(String sceltaMode) {

        Boolean sceltaBool;
        int dimTable;
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();

        if (sceltaMode.equals("insert")) {
            sceltaBool = true;
            dimTable = 70;
            buttonInsert.setStyle("-fx-border-color: red;");
            buttonView.setStyle("-fx-border-color: grey;");
        } else {
            sceltaBool = false;
            dimTable = 535;
            buttonInsert.setStyle("-fx-border-color: grey;");
            buttonView.setStyle("-fx-border-color: red;");
        }
        for (TableView tableView : tableViewHashMap.values()) {
            tableView.getItems().clear();
            tableView.setVisible(false);
            tableView.setPrefHeight(dimTable);
            if (sceltaMode.equals("insert")) {
                tableView.getItems().add(objectHashMap.get(scelta));
            }
        }
        tableViewHashMap.get(scelta).setVisible(true);
        buttonView.setDisable(!sceltaBool);

        buttonInserisci.setVisible(sceltaBool);
        buttonInsert.setDisable(sceltaBool);
        idBarSearch.setDisable(sceltaBool);
        comboBoxRicerca.setDisable(sceltaBool);
        buttonCerca.setDisable(sceltaBool);

        isbnLibro.setEditable(sceltaBool);
        doiArticolo.setEditable(sceltaBool);
        nomerArticolo.setEditable(sceltaBool);
        datarArticolo.setEditable(sceltaBool);
        codaAcquisto.setEditable(sceltaBool);
        codcConferenza.setEditable(sceltaBool);
        codpPresentazione.setEditable(sceltaBool);
        nomeRivista.setEditable(sceltaBool);
        dataRivista.setEditable(sceltaBool);
        codsSerie.setEditable(sceltaBool);
        codaDisponibileA.setEditable(sceltaBool);
        doiDisponibileA.setEditable(sceltaBool);
        codaDisponibileL.setEditable(sceltaBool);
        isbnDisponibileL.setEditable(sceltaBool);
        codaDisponibileS.setEditable(sceltaBool);
        codsDisponibileS.setEditable(sceltaBool);
    }

    public void selezioneSceltaTableView(ActionEvent event) {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }

        setVisibleFalseAllTableView();
        tableViewHashMap.get(scelta).setVisible(true);
        comboBoxRicerca.setItems(ricercaHashMap.get(scelta));
        comboBoxRicerca.getSelectionModel().selectFirst();
    }


    @FXML
    private void deleteDao() {
        try {
            String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
            if (scelta == null) {
                support.messageStage("Selezionare prima un tipo di ricerca");
                return;
            }
            tableViewHashMap.get(scelta).getItems().remove(tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem());
            implDaoHashMap.get(scelta).delete(tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem());
            support.messageStage("Delete effettuato");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void insertDao() {
        try {
            implDaoHashMap.get(scelta).insert(tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem());
            support.messageStage("Insert effettuato");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void updateDao() {
        try {
            implDaoHashMap.get(scelta).update(tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem());
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


}