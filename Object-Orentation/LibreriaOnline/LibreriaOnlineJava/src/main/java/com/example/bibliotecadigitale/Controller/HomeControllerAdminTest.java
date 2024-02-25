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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeControllerAdminTest implements Initializable {
    private String scelta = "libro";
    private String sceltaInsertView = "insert";
    @FXML
    private Button buttonCerca;

    @FXML
    private TextField idBarSearch;
    private final SupportStage support = new SupportStage();
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
    private TableView<Libro> libroTableView;
    @FXML
    private TableColumn<Libro, String> isbnLibro;
    @FXML
    private TableColumn<Libro, String> titoloLibro;
    @FXML
    private TableColumn<Libro, String> genereLibro;
    @FXML
    private TableColumn<Libro, Integer> numpagineLibro;
    @FXML
    private TableColumn<Libro, String> tipoLibro;
    @FXML
    private TableColumn<Libro, String> materiaLibro;
    @FXML
    private TableColumn<Libro, String> descrizioneLibro;
    @FXML
    private TableColumn<Libro, String> fruizioneLibro;
    @FXML
    private TableColumn<Libro, String> editoreLibro;
    @FXML
    private TableColumn<Libro, String> autoreLibro;
    @FXML
    private TableColumn<Libro, String> datauscitaLibro;
    @FXML
    private TableColumn<Libro, String> linguaLibro;
    @FXML
    private TableColumn<Libro, String> successivoLibro;
    @FXML
    private TableColumn<Libro, Integer> serieLibro;
    @FXML
    private TableColumn<Libro, Integer> presentazioneLibro;

    //Table view articolo
    @FXML
    private TableView<ArticoloScientifico> articoloTableView;
    @FXML
    private TableColumn<ArticoloScientifico, String> doiArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> titoloArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> genereArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, Integer> numpagineArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> datauscitaArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> descrizioneArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> fruizioneArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> editoreArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> autoreArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> linguaArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, Integer> conferenzaArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> nomerArticolo;
    @FXML
    private TableColumn<ArticoloScientifico, String> datarArticolo;

    //Table view acquisto
    @FXML
    public TableView<Acquisto> acquistoTableView;
    @FXML
    private TableColumn<Acquisto, Integer> codaAcquisto;
    @FXML
    private TableColumn<Acquisto, String> nomeAcquisto;
    @FXML
    private TableColumn<Acquisto, String> tipoAcquisto;
    @FXML
    private TableColumn<Acquisto, String> urlAcquisto;
    @FXML
    private TableColumn<Acquisto, String> indirizzoAcquisto;

    //Table view conferenza
    @FXML
    public TableView<Conferenza> conferenzaTableView;
    @FXML
    private TableColumn<Conferenza, Integer> codcConferenza;
    @FXML
    private TableColumn<Conferenza, String> nomeConferenza;
    @FXML
    private TableColumn<Conferenza, String> strutturaConferenza;
    @FXML
    private TableColumn<Conferenza, String> indirizzoConferenza;
    @FXML
    private TableColumn<Conferenza, String> dataiConferenza;
    @FXML
    private TableColumn<Conferenza, String> datafConferenza;
    @FXML
    private TableColumn<Conferenza, String> responsabileConferenza;

    //Table view presentazione
    @FXML
    public TableView<Presentazione> presentazioneTableView;
    @FXML
    private TableColumn<Presentazione, Integer> codpPresentazione;
    @FXML
    private TableColumn<Presentazione, String> nomePresentazione;
    @FXML
    private TableColumn<Presentazione, String> indirizzoPresentazione;
    @FXML
    private TableColumn<Presentazione, String> dataPresentazione;
    @FXML
    private TableColumn<Presentazione, String> tipoPresentazione;

    //Table view rivista
    @FXML
    public TableView<Rivista> rivistaTableView;
    @FXML
    private TableColumn<Rivista, String> nomeRivista;
    @FXML
    private TableColumn<Rivista, String> dataRivista;
    @FXML
    private TableColumn<Rivista, String> responsabileRivista;
    @FXML
    private TableColumn<Rivista, String> argomentoRivista;

    //Table view serie
    @FXML
    public TableView<Serie> serieTableView;
    @FXML
    private TableColumn<Serie, Integer> codsSerie;
    @FXML
    private TableColumn<Serie, String> nomeSerie;
    @FXML
    private TableColumn<Serie, Integer> numlibriSerie;
    @FXML
    private TableColumn<Serie, Boolean> completataSerie;

    //Table view disponibileA
    @FXML
    public TableView<DisponibileA> disponibileATableView;
    @FXML
    private TableColumn<DisponibileA, Integer> codaDisponibileA;
    @FXML
    private TableColumn<DisponibileA, String> doiDisponibileA;

    //Table view disponibileL
    @FXML
    public TableView<DisponibileL> disponibileLTableView;
    @FXML
    private TableColumn<DisponibileL, Integer> codaDisponibileL;
    @FXML
    private TableColumn<DisponibileL, String> isbnDisponibileL;

    //Table view disponibileS
    @FXML
    private TableView<DisponibileS> disponibileSTableView;
    @FXML
    private TableColumn<DisponibileS, Integer> codaDisponibileS;
    @FXML
    private TableColumn<DisponibileS, Integer> codsDisponibileS;

    @FXML
    private MenuItem itemInsert1;
    @FXML
    private MenuItem itemInsert2;
    @FXML
    private MenuItem itemInsert3;
    @FXML
    private MenuItem itemInsert4;
    @FXML
    private MenuItem itemInsert5;
    @FXML
    private MenuItem itemInsert6;
    @FXML
    private MenuItem itemInsert7;
    @FXML
    private MenuItem itemInsert8;
    @FXML
    private MenuItem itemInsert9;
    @FXML
    private MenuItem itemInsert10;

    @FXML
    private MenuItem itemUpdate1;
    @FXML
    private MenuItem itemUpdate2;
    @FXML
    private MenuItem itemUpdate3;
    @FXML
    private MenuItem itemUpdate4;
    @FXML
    private MenuItem itemUpdate5;
    @FXML
    private MenuItem itemUpdate6;
    @FXML
    private MenuItem itemUpdate7;
    @FXML
    private MenuItem itemUpdate8;
    @FXML
    private MenuItem itemUpdate9;
    @FXML
    private MenuItem itemUpdate10;

    @FXML
    private MenuItem itemDelete1;
    @FXML
    private MenuItem itemDelete2;
    @FXML
    private MenuItem itemDelete3;
    @FXML
    private MenuItem itemDelete4;
    @FXML
    private MenuItem itemDelete5;
    @FXML
    private MenuItem itemDelete6;
    @FXML
    private MenuItem itemDelete7;
    @FXML
    private MenuItem itemDelete8;
    @FXML
    private MenuItem itemDelete9;
    @FXML
    private MenuItem itemDelete10;
    private HashMap<String, TableView> tableViewHashMap = new HashMap<>();
    private HashMap<String, ObservableList<String>> ricercaHashMap = new HashMap<>();
    private HashMap<String, DAO> implDaoHashMap = new HashMap<>();

    private HashMap<String, Class> objectHashMap = new HashMap<>();
    @FXML
    private ImageView imageLibriSfondo;
    @FXML
    private Button buttonViewAll;

    @FXML
    private Text txtRicercaInserimento;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo combo box in tableview audiolibro solamente da problemi, mettere in tutti gli altri casi
        ObservableList list = FXCollections.observableArrayList();
        list.add("Cartaceo");
        list.add("Digitale");
        list.add("AudioLibro");
        list.add("Cartaceo,Digitale");
        list.add("Cartaceo,AudioLibro");
        list.add("Digitale,AudioLibro");
        list.add("Cartaceo,Digitale,AudioLibro");
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
        //inizializzo le colonne della tabella libro
        isbnLibro.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        titoloLibro.setCellValueFactory(new PropertyValueFactory<>("titolo"));
//        titoloLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), list));
        titoloLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        genereLibro.setCellValueFactory(new PropertyValueFactory<>("genere"));
        genereLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        numpagineLibro.setCellValueFactory(new PropertyValueFactory<>("numpagine"));
        numpagineLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tipoLibro.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tipoLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        materiaLibro.setCellValueFactory(new PropertyValueFactory<>("materia"));
        materiaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        descrizioneLibro.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        descrizioneLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        fruizioneLibro.setCellValueFactory(new PropertyValueFactory<>("fruizione"));
//        fruizioneLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        fruizioneLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), list));
        editoreLibro.setCellValueFactory(new PropertyValueFactory<>("editore"));
        editoreLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        autoreLibro.setCellValueFactory(new PropertyValueFactory<>("autore"));
        autoreLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        datauscitaLibro.setCellValueFactory(new PropertyValueFactory<>("datauscita"));
        datauscitaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        linguaLibro.setCellValueFactory(new PropertyValueFactory<>("lingua"));
        linguaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        successivoLibro.setCellValueFactory(new PropertyValueFactory<>("successivo"));
        successivoLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        serieLibro.setCellValueFactory(new PropertyValueFactory<>("serie"));
        serieLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        presentazioneLibro.setCellValueFactory(new PropertyValueFactory<>("presentazione"));
        presentazioneLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //inizializzo le colonne della tabella articolo
        doiArticolo.setCellValueFactory(new PropertyValueFactory<>("doi"));
        doiArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        doiArticolo.setEditable(false);
        titoloArticolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        titoloArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        genereArticolo.setCellValueFactory(new PropertyValueFactory<>("genere"));
        genereArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        numpagineArticolo.setCellValueFactory(new PropertyValueFactory<>("numpagine"));
        numpagineArticolo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        datauscitaArticolo.setCellValueFactory(new PropertyValueFactory<>("datauscita"));
        datauscitaArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        descrizioneArticolo.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        descrizioneArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        fruizioneArticolo.setCellValueFactory(new PropertyValueFactory<>("fruizione"));
        fruizioneArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        editoreArticolo.setCellValueFactory(new PropertyValueFactory<>("editore"));
        editoreArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        autoreArticolo.setCellValueFactory(new PropertyValueFactory<>("autore"));
        autoreArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        linguaArticolo.setCellValueFactory(new PropertyValueFactory<>("lingua"));
        linguaArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        conferenzaArticolo.setCellValueFactory(new PropertyValueFactory<>("conferenza"));
        conferenzaArticolo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nomerArticolo.setCellValueFactory(new PropertyValueFactory<>("nomer"));
        nomerArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        nomerArticolo.setEditable(false);
        datarArticolo.setCellValueFactory(new PropertyValueFactory<>("datar"));
        datarArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        datarArticolo.setEditable(false);

        //inizializzo le colonne della tabella acquisto
        codaAcquisto.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaAcquisto.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaAcquisto.setEditable(false);
        nomeAcquisto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoAcquisto.setCellValueFactory(new PropertyValueFactory<>("tipoa"));
        tipoAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());
        urlAcquisto.setCellValueFactory(new PropertyValueFactory<>("url"));
        urlAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoAcquisto.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        indirizzoAcquisto.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella conferenza
        codcConferenza.setCellValueFactory(new PropertyValueFactory<>("codc"));
        codcConferenza.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codcConferenza.setEditable(false);
        nomeConferenza.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        strutturaConferenza.setCellValueFactory(new PropertyValueFactory<>("struttura"));
        strutturaConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoConferenza.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        indirizzoConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        dataiConferenza.setCellValueFactory(new PropertyValueFactory<>("datai"));
        dataiConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        datafConferenza.setCellValueFactory(new PropertyValueFactory<>("dataf"));
        datafConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        responsabileConferenza.setCellValueFactory(new PropertyValueFactory<>("responsabile"));
        responsabileConferenza.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella presentazione
        codpPresentazione.setCellValueFactory(new PropertyValueFactory<>("codp"));
        codpPresentazione.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codpPresentazione.setEditable(false);
        nomePresentazione.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomePresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoPresentazione.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        indirizzoPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        dataPresentazione.setCellValueFactory(new PropertyValueFactory<>("datapresentazione"));
        dataPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoPresentazione.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tipoPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella rivista
        nomeRivista.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        nomeRivista.setEditable(false);
        dataRivista.setCellValueFactory(new PropertyValueFactory<>("data"));
        responsabileRivista.setCellValueFactory(new PropertyValueFactory<>("responsabile"));
        responsabileRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        argomentoRivista.setCellValueFactory(new PropertyValueFactory<>("argomento"));
        argomentoRivista.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella serie
        codsSerie.setCellValueFactory(new PropertyValueFactory<>("cods"));
        codsSerie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codsSerie.setEditable(false);
        nomeSerie.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeSerie.setCellFactory(TextFieldTableCell.forTableColumn());
        numlibriSerie.setCellValueFactory(new PropertyValueFactory<>("numlibri"));
        completataSerie.setCellValueFactory(new PropertyValueFactory<>("completata"));
        completataSerie.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

        //inizializzo le colonne della tabella disponibileA
        codaDisponibileA.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaDisponibileA.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaDisponibileA.setEditable(false);
        doiDisponibileA.setCellValueFactory(new PropertyValueFactory<>("doi"));
        doiDisponibileA.setCellFactory(TextFieldTableCell.forTableColumn());
        doiDisponibileA.setEditable(false);

        //inizializzo le colonne della tabella disponibileL
        codaDisponibileL.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaDisponibileL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaDisponibileL.setEditable(false);
        isbnDisponibileL.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnDisponibileL.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnDisponibileL.setEditable(false);

        //inizializzo le colonne della tabella disponibileS
        codaDisponibileS.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaDisponibileS.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codaDisponibileS.setEditable(false);
        codsDisponibileS.setCellValueFactory(new PropertyValueFactory<>("cods"));
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
        comboBoxRicerca.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Materia", "Descrizione", "Fruizione", "Lingua", "Successivo", "Serie", "Presentazione"));
        comboBoxRicerca.getSelectionModel().selectFirst();

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

        objectHashMap.put("Libro", Libro.class);
        objectHashMap.put("Articolo", ArticoloScientifico.class);
        objectHashMap.put("Acquisto", Acquisto.class);
        objectHashMap.put("Conferenza", Conferenza.class);
        objectHashMap.put("Presentazione", Presentazione.class);
        objectHashMap.put("Rivista", Rivista.class);
        objectHashMap.put("Serie", Serie.class);
        objectHashMap.put("DisponibileA", DisponibileA.class);
        objectHashMap.put("DisponibileL", DisponibileL.class);
        objectHashMap.put("DisponibileS", DisponibileS.class);

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

        ricercaHashMap.put("Libro", (FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Tipo", "Materia", "Descrizione", "Fruizione", "Lingua", "Successivo", "Serie", "Presentazione")));
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
    }

    @FXML
    private void Select(ActionEvent event) throws SQLException {
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
        ArrayList<ArrayList<String>> objects = new ArrayList<>();
        objects = implDaoHashMap.get(scelta).getRicerca(modRicerca, titoloRicerche);
        tableViewHashMap.get(scelta).getItems().clear();
        tableViewHashMap.get(scelta).setVisible(true);
        idBarSearch.clear();
        if (objects.isEmpty()) {
            support.messageStage("Nessun match trovato");
            return;
        }
        for (ArrayList<String> object : objects) {
            try {
                tableViewHashMap.get(scelta).getItems().add(objectHashMap.get(scelta).getConstructor(ArrayList.class).newInstance(object));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void viewAll(ActionEvent event) {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
        try {
            tableViewHashMap.get(scelta).getItems().clear();
            ArrayList<ArrayList<String>> arrayList = implDaoHashMap.get(scelta).getAll();
            for (ArrayList<String> strings : arrayList) {
                tableViewHashMap.get(scelta).getItems().add(objectHashMap.get(scelta).getConstructor(ArrayList.class).newInstance(strings));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void logOff(ActionEvent event) {
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
        txtRicercaInserimento.setText("Cosa vuori Inserire ?");
        sceltaSetter(sceltaInsertView);
    }

    @FXML
    private void sceltaView() {
        sceltaInsertView = "view";
        txtRicercaInserimento.setText("Cosa vuori Visualizzare ?");
        sceltaSetter(sceltaInsertView);
    }

    private void sceltaSetter(String sceltaMode) {
        boolean sceltaBool;
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

        setVisibleFalseAllTableView();
        for (TableView tableView : tableViewHashMap.values()) {
            tableView.getItems().clear();
            tableView.setPrefHeight(dimTable);
        }

        if (sceltaMode.equals("insert")) {
            tableViewHashMap.get("Libro").getItems().add(new Libro());
            tableViewHashMap.get("Articolo").getItems().add(new ArticoloScientifico());
            tableViewHashMap.get("Acquisto").getItems().add(new Acquisto());
            tableViewHashMap.get("Conferenza").getItems().add(new Conferenza());
            tableViewHashMap.get("Presentazione").getItems().add(new Presentazione());
            tableViewHashMap.get("Rivista").getItems().add(new Rivista());
            tableViewHashMap.get("Serie").getItems().add(new Serie());
            tableViewHashMap.get("DisponibileA").getItems().add(new DisponibileA());
            tableViewHashMap.get("DisponibileL").getItems().add(new DisponibileL());
            tableViewHashMap.get("DisponibileS").getItems().add(new DisponibileS());
        }
        tableViewHashMap.get(scelta).setVisible(true);
        buttonView.setDisable(!sceltaBool);

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

        itemInsert1.setVisible(sceltaBool);
        itemInsert2.setVisible(sceltaBool);
        itemInsert3.setVisible(sceltaBool);
        itemInsert4.setVisible(sceltaBool);
        itemInsert5.setVisible(sceltaBool);
        itemInsert6.setVisible(sceltaBool);
        itemInsert7.setVisible(sceltaBool);
        itemInsert8.setVisible(sceltaBool);
        itemInsert9.setVisible(sceltaBool);
        itemInsert10.setVisible(sceltaBool);

        itemUpdate1.setVisible(!sceltaBool);
        itemUpdate2.setVisible(!sceltaBool);
        itemUpdate3.setVisible(!sceltaBool);
        itemUpdate4.setVisible(!sceltaBool);
        itemUpdate5.setVisible(!sceltaBool);
        itemUpdate6.setVisible(!sceltaBool);
        itemUpdate7.setVisible(!sceltaBool);
        itemUpdate8.setVisible(!sceltaBool);
        itemUpdate9.setVisible(!sceltaBool);
        itemUpdate10.setVisible(!sceltaBool);

        itemDelete1.setVisible(!sceltaBool);
        itemDelete2.setVisible(!sceltaBool);
        itemDelete3.setVisible(!sceltaBool);
        itemDelete4.setVisible(!sceltaBool);
        itemDelete5.setVisible(!sceltaBool);
        itemDelete6.setVisible(!sceltaBool);
        itemDelete7.setVisible(!sceltaBool);
        itemDelete8.setVisible(!sceltaBool);
        itemDelete9.setVisible(!sceltaBool);
        itemDelete10.setVisible(!sceltaBool);

        buttonViewAll.setVisible(!sceltaBool);
    }

    @FXML
    private void selezioneSceltaTableView(ActionEvent event) {
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
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
        Object objDelete = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
        tableViewHashMap.get(scelta).getItems().remove(objDelete);
        ArrayList<String> arrayList = null;
        try {
            arrayList = (ArrayList<String>) objDelete.getClass().getMethod("ObjToArrayList").invoke(objDelete);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            implDaoHashMap.get(scelta).delete(arrayList);
            support.messageStage("Delete effettuato");
        } catch (SQLException e) {
            support.messageStage("Errore nell'eliminazione");
            e.printStackTrace();
        }
    }

    @FXML
    private void insertDao() {
        Object objInsert = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
        ArrayList<String> arrayList = null;
        try {
            arrayList = (ArrayList<String>) objInsert.getClass().getMethod("ObjToArrayList").invoke(objInsert);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            implDaoHashMap.get(scelta).insert(arrayList);
            support.messageStage("Insert effettuato");
        } catch (SQLException e) {
            support.messageStage("Errore nell'inserimento");
            e.printStackTrace();
        }
    }

    @FXML
    private void updateDao() {
        Object objUpdate = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
        ArrayList<String> arrayList = null;
        try {
            arrayList = (ArrayList<String>) objUpdate.getClass().getMethod("ObjToArrayList").invoke(objUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            implDaoHashMap.get(scelta).update(arrayList);
            support.messageStage("Update effettuato");
        } catch (SQLException e) {
            support.messageStage("Errore nell'aggiornamento");
        }
    }

    @FXML
    private void onEditChangedString(TableColumn.CellEditEvent stringCellEditEvent) {
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        String tipoColumn = stringCellEditEvent.getTableColumn().getId();
        String valoreColumnString = (String) stringCellEditEvent.getNewValue();
        String nomeMetodo = "set" + tipoColumn.substring(0, 1).toUpperCase() + tipoColumn.substring(1, tipoColumn.indexOf(scelta));
        try {
            Object test = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
            test.getClass().getMethod(nomeMetodo, String.class).invoke(test, valoreColumnString);
        } catch (Exception e) {
            System.out.println("Errore" + e.getMessage());
        }
    }

    @FXML
    private void onEditChangedInt(TableColumn.CellEditEvent intCellEditEvent) {
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        String tipoColumn = intCellEditEvent.getTableColumn().getId();
        int valoreColumnInt = (int) intCellEditEvent.getNewValue();
        String nomeMetodo = "set" + tipoColumn.substring(0, 1).toUpperCase() + tipoColumn.substring(1, tipoColumn.indexOf(scelta));
        try {
            Object test = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
            test.getClass().getMethod(nomeMetodo, int.class).invoke(test, valoreColumnInt);
        } catch (Exception e) {
            System.out.println("Errore" + e.getMessage());
        }
    }

    @FXML
    private void onEditChangedBool(TableColumn.CellEditEvent boolCellEditEvent) {
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        String tipoColumn = boolCellEditEvent.getTableColumn().getId();
        Boolean valoreColumnBoolean = (Boolean) boolCellEditEvent.getNewValue();
        String nomeMetodo = "set" + tipoColumn.substring(0, 1).toUpperCase() + tipoColumn.substring(1, tipoColumn.indexOf(scelta));
        try {
            Object test = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
            test.getClass().getMethod(nomeMetodo, Boolean.class).invoke(test, valoreColumnBoolean);
        } catch (Exception e) {
            System.out.println("Errore" + e.getMessage());
        }
    }
}