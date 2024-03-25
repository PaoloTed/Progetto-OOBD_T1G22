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
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.lang.reflect.Method;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class HomeControllerAdmin implements Initializable {
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
    private TableColumn<Serie, String> completataSerie;

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

    private HashMap<String, Class> classHashMap = new HashMap<>();
    @FXML
    private ImageView imageLibriSfondo;
    @FXML
    private Button buttonViewAll;

    @FXML
    private Text txtRicercaInserimento;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String, ObservableList> lists = getLists();
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
        //inizializzo le colonne della tabella libro
        isbnLibro.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        titoloLibro.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        titoloLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        genereLibro.setCellValueFactory(new PropertyValueFactory<>("genere"));
        genereLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        numpagineLibro.setCellValueFactory(new PropertyValueFactory<>("numpagine"));
        numpagineLibro.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tipoLibro.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tipoLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("LibroTipo")));
        materiaLibro.setCellValueFactory(new PropertyValueFactory<>("materia"));
        materiaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        descrizioneLibro.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        descrizioneLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        fruizioneLibro.setCellValueFactory(new PropertyValueFactory<>("fruizione"));
        fruizioneLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("LibroFruizione")));
        editoreLibro.setCellValueFactory(new PropertyValueFactory<>("editore"));
        editoreLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        autoreLibro.setCellValueFactory(new PropertyValueFactory<>("autore"));
        autoreLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        datauscitaLibro.setCellValueFactory(new PropertyValueFactory<>("datauscita"));
        datauscitaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        linguaLibro.setCellValueFactory(new PropertyValueFactory<>("lingua"));
        linguaLibro.setCellFactory(TextFieldTableCell.forTableColumn());
        successivoLibro.setCellValueFactory(new PropertyValueFactory<>("successivo"));
        successivoLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("LibroIsbn")));
        serieLibro.setCellValueFactory(new PropertyValueFactory<>("serie"));
        serieLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("LibroSerie")));
        presentazioneLibro.setCellValueFactory(new PropertyValueFactory<>("presentazione"));
        presentazioneLibro.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("LibroPresentazione")));

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
        fruizioneArticolo.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("LibroFruizione")));
        editoreArticolo.setCellValueFactory(new PropertyValueFactory<>("editore"));
        editoreArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        autoreArticolo.setCellValueFactory(new PropertyValueFactory<>("autore"));
        autoreArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        linguaArticolo.setCellValueFactory(new PropertyValueFactory<>("lingua"));
        linguaArticolo.setCellFactory(TextFieldTableCell.forTableColumn());
        conferenzaArticolo.setCellValueFactory(new PropertyValueFactory<>("conferenza"));
        conferenzaArticolo.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("ArticoloConferenza")));
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
        tipoAcquisto.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tipoAcquisto.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("AcquistoTipo")));
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
        dataPresentazione.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataPresentazione.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoPresentazione.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tipoPresentazione.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("PresentazioneTipo")));

        //inizializzo le colonne della tabella rivista
        nomeRivista.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        dataRivista.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataRivista.setCellFactory(TextFieldTableCell.forTableColumn());
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
        numlibriSerie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        completataSerie.setCellValueFactory(new PropertyValueFactory<>("completata"));
        completataSerie.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("SerieCompletata")));
//        completataSerie.setCellFactory(ComboBoxTableCell.forTableColumn(new BooleanStringConverter(), lists.get("SerieCompletata")));

        //inizializzo le colonne della tabella disponibileA
        codaDisponibileA.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaDisponibileA.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("DisponibileACoda")));
        codaDisponibileA.setEditable(false);
        doiDisponibileA.setCellValueFactory(new PropertyValueFactory<>("doi"));
        doiDisponibileA.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("DisponibileADoi")));
        doiDisponibileA.setEditable(false);

        //inizializzo le colonne della tabella disponibileL
        codaDisponibileL.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaDisponibileL.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("DisponibileLCoda")));
        codaDisponibileL.setEditable(false);
        isbnDisponibileL.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnDisponibileL.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), lists.get("DisponibileLIsbn")));
        isbnDisponibileL.setEditable(false);

        //inizializzo le colonne della tabella disponibileS
        codaDisponibileS.setCellValueFactory(new PropertyValueFactory<>("coda"));
        codaDisponibileS.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("DisponibileSCoda")));
        codaDisponibileS.setEditable(false);
        codsDisponibileS.setCellValueFactory(new PropertyValueFactory<>("cods"));
        codsDisponibileS.setCellFactory(ComboBoxTableCell.forTableColumn(new IntegerStringConverter(), lists.get("DisponibileSCods")));
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

        classHashMap.put("Libro", Libro.class);
        classHashMap.put("Articolo", ArticoloScientifico.class);
        classHashMap.put("Acquisto", Acquisto.class);
        classHashMap.put("Conferenza", Conferenza.class);
        classHashMap.put("Presentazione", Presentazione.class);
        classHashMap.put("Rivista", Rivista.class);
        classHashMap.put("Serie", Serie.class);
        classHashMap.put("DisponibileA", DisponibileA.class);
        classHashMap.put("DisponibileL", DisponibileL.class);
        classHashMap.put("DisponibileS", DisponibileS.class);

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

    /**
     * Questo metodo carica le liste necessarie per i combobox
     * E.g ritorna una lista di tutti i libri presenti nel database
     * da usare nella combobox successivo
     *
     * @return ritorna una mappa con tutte le liste necessarie per i combobox
     */
    private HashMap<String, ObservableList> getLists() {
        HashMap<String, ObservableList> lists = new HashMap<>();
        ObservableList list = FXCollections.observableArrayList();
        //LIBRO
        //-Fruizione
        list = FXCollections.observableArrayList();
        list.add("Cartaceo");
        list.add("Digitale");
        list.add("AudioLibro");
        list.add("Cartaceo,Digitale");
        list.add("Cartaceo,AudioLibro");
        list.add("Digitale,AudioLibro");
        list.add("Cartaceo,Digitale,AudioLibro");
        lists.put("LibroFruizione", list);
        //-Tipo
        list = FXCollections.observableArrayList();
        list.add("Romanzo");
        list.add("Didattico");
        lists.put("LibroTipo", list);
        //-Serie
        list = FXCollections.observableArrayList();
        try {
            list.add(null);
            SerieDAOImpl serieDAO = new SerieDAOImpl();
            ArrayList<ArrayList<String>> arraySerie = serieDAO.getAll();
            for (ArrayList<String> strings : arraySerie) {
                if (strings.get(0) != null)
                    list.add(Integer.parseInt(strings.get(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("LibroSerie", list);
        }
        //-Presentazione
        list = FXCollections.observableArrayList();
        try {
            list.add(null);
            PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
            ArrayList<ArrayList<String>> arrayPresentazione = presentazioneDAO.getAll();
            for (ArrayList<String> strings : arrayPresentazione) {
                if (strings.get(0) != null)
                    list.add(Integer.parseInt(strings.get(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("LibroPresentazione", list);
        }
        //-ISBN
        list = FXCollections.observableArrayList();
        try {
            list.add(null);
            LibroDAOImpl libroDAO = new LibroDAOImpl();
            ArrayList<ArrayList<String>> arrayLibro = libroDAO.getAll();
            for (ArrayList<String> strings : arrayLibro) {
                if (strings.get(0) != null)
                    list.add(strings.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("LibroIsbn", list);
        }
        //Articolo
        //-Conferenza
        list = FXCollections.observableArrayList();
        try {
            list.add(null);
            ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
            ArrayList<ArrayList<String>> arrayConferenza = conferenzaDAO.getAll();
            for (ArrayList<String> strings : arrayConferenza) {
                if (strings.get(0) != null)
                    list.add(Integer.parseInt(strings.get(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("ArticoloConferenza", list);
        }
        //Acquisto
        //-Tipo
        list = FXCollections.observableArrayList();
        list.add("Libreria");
        list.add("Sito web");
        lists.put("AcquistoTipo", list);
        //Presentazione
        //-Tipo
        list = FXCollections.observableArrayList();
        list.add("Libreria");
        list.add("Sala");
        lists.put("PresentazioneTipo", list);
        //Serie
        //-Completata
        list = FXCollections.observableArrayList();
        list.add("true");
        list.add("false");
        lists.put("SerieCompletata", list);
        //DISPONIBILEA
        //-Coda
        list = FXCollections.observableArrayList();
        try {
            AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
            ArrayList<ArrayList<String>> arrayAcquisto = acquistoDAO.getAll();
            for (ArrayList<String> strings : arrayAcquisto) {
                if (strings.get(0) != null)
                    list.add(Integer.parseInt(strings.get(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("DisponibileACoda", list);
            lists.put("DisponibileLCoda", list);
            lists.put("DisponibileSCoda", list);
        }
        //-Doi
        list = FXCollections.observableArrayList();
        try {
            ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
            ArrayList<ArrayList<String>> arrayArticolo = articoloScientificoDAO.getAll();
            for (ArrayList<String> strings : arrayArticolo) {
                if (strings.get(0) != null)
                    list.add(strings.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("DisponibileADoi", list);
        }
        //DISPONIBILEL
        //-Coda
        //Gia Inserito
        //-Isbn
        list = FXCollections.observableArrayList();
        try {
            LibroDAOImpl libroDAO = new LibroDAOImpl();
            ArrayList<ArrayList<String>> arrayLibro = libroDAO.getAll();
            for (ArrayList<String> strings : arrayLibro) {
                if (strings.get(0) != null)
                    list.add(strings.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("DisponibileLIsbn", list);
        }
        //DISPONIBILES
        //-Coda
        //Gia Inserito
        //-Cods
        list = FXCollections.observableArrayList();
        try {
            SerieDAOImpl serieDAO = new SerieDAOImpl();
            ArrayList<ArrayList<String>> arraySerie = serieDAO.getAll();
            for (ArrayList<String> strings : arraySerie) {
                if (strings.get(0) != null)
                    list.add(Integer.parseInt(strings.get(0)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lists.put("DisponibileSCods", list);
        }
        return lists;
    }

    /**
     * Questo metodo permette di selezionare un tipo di ricerca
     * e di visualizzare i risultati
     * Rendenendo visibile la tabella corrispondente alla ricerca
     *
     * @param event evento del bottone cerca
     * @throws SQLException
     */
    @FXML
    private void Select(ActionEvent event) throws SQLException {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();// il tipo di ricerca
        TableView tableViewScelta = tableViewHashMap.get(scelta); // la tabella corrispondente alla ricerca selezionata
        Class classeScelta = classHashMap.get(scelta);// la classe corrispondente alla ricerca selezionata

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

        //Ricerca e visualizzazione risultati
        ArrayList<ArrayList<String>> listRisultati = new ArrayList<>();
        //Recupera i risultati della ricerca in base al tipo di ricerca
        //Verra utilizzato il metodo getRicerca dalla classe DAO corrispondente alla scelta
        DAO daoObj = implDaoHashMap.get(scelta);
        listRisultati = daoObj.getRicerca(modRicerca, titoloRicerche);
        tableViewScelta.getItems().clear();
        tableViewScelta.setVisible(true);
        idBarSearch.clear();
        if (listRisultati.isEmpty()) {
            support.messageStage("Nessun match trovato");
            return;
        }
        //Visualizza i risultati della ricerca nella tabella corrispondente
        //Richiamando il costruttore della classe corrispondente alla ricerca
        //E passando come parametro ogni risultato della ricerca
        for (ArrayList<String> risultato : listRisultati) {
            try {
                tableViewScelta.getItems().add(classeScelta.getConstructor(ArrayList.class).newInstance(risultato));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Questo metodo permette di visualizzare tutti gli elemti di una ricerca
     * Rendenendo visibile la tabella corrispondente alla ricerca
     *
     * @param event evento del bottone visualizza tutto
     */
    @FXML
    private void viewAll(ActionEvent event) {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        TableView tableViewScelta = tableViewHashMap.get(scelta);
        DAO daoObj = implDaoHashMap.get(scelta);
        Class classeScelta = classHashMap.get(scelta);
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
        try {
            tableViewScelta.getItems().clear();
            ArrayList<ArrayList<String>> listRisultati = daoObj.getAll();
            for (ArrayList<String> risultato : listRisultati) {
                tableViewScelta.getItems().add(classeScelta.getConstructor(ArrayList.class).newInstance(risultato));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void logOff(ActionEvent event) {
        Utente.getUtente().exitUtente();
        support.switchStage("welcomeStage.fxml", event, 500, 500);
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
        txtRicercaInserimento.setText("Cosa vuoi Inserire ?");
        sceltaSetter(sceltaInsertView);
    }

    @FXML
    private void sceltaView() {
        sceltaInsertView = "view";
        txtRicercaInserimento.setText("Cosa vuoi Visualizzare ?");
        sceltaSetter(sceltaInsertView);
    }

    /**
     * Questo metodo permette di selezionare se si vuole inserire o visualizzare
     * Rimpicciolendo le tabelle per insert
     * Ingrandendo le tabelle per view
     *
     * @param sceltaMode stringa che indica se si vuole inserire o visualizzare
     */
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

    /**
     * Questo metodo permette di selezionare un tipo di ricerca ossia
     * la tabella da visualizzare
     *
     * @param event
     */
    @FXML
    private void selezioneSceltaTableView(ActionEvent event) {
        scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        TableView tableViewScelta = tableViewHashMap.get(scelta);
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
        setVisibleFalseAllTableView();
        tableViewScelta.setVisible(true);
        comboBoxRicerca.setItems(ricercaHashMap.get(scelta));
        comboBoxRicerca.getSelectionModel().selectFirst();
    }

    /**
     * Questo metodo permette di eliminare un oggetto dalla tabella selezionata
     * e dal database
     */
    @FXML
    private void deleteDao() {
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        TableView tableViewScelta = tableViewHashMap.get(scelta);
        if (scelta == null) {
            support.messageStage("Selezionare prima un tipo di ricerca");
            return;
        }
        Object objDelete = tableViewScelta.getSelectionModel().getSelectedItem();
        tableViewScelta.getItems().remove(objDelete);
        ArrayList<String> arrayList = null;
        try {
            arrayList = (ArrayList<String>) objDelete.getClass().getMethod("ObjToArrayList").invoke(objDelete);
        } catch (Exception e) {
            System.out.println("Errore reperimento dati" + e.getMessage());
            e.printStackTrace();
        }
        try {
            DAO daoObj = implDaoHashMap.get(scelta);
            daoObj.delete(arrayList);
            support.messageStage("Delete effettuato");
        } catch (SQLException e) {
            support.messageStage("Errore nell'eliminazione");
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo permette di inserire un oggetto nella tabella selezionata
     * e nel database
     */
    @FXML
    private void insertDao() {
        TableView tableViewScelta = tableViewHashMap.get(scelta);
        Object objInsert = tableViewScelta.getSelectionModel().getSelectedItem();
        ArrayList<String> arraylistObj = null;
        try {
            arraylistObj = (ArrayList<String>) objInsert.getClass().getMethod("ObjToArrayList").invoke(objInsert);
        } catch (Exception e) {
            System.out.println("Errore reperimento dati" + e.getMessage());
            e.printStackTrace();
        }

        try {
            DAO daoObj = implDaoHashMap.get(scelta);
            daoObj.insert(arraylistObj);
            support.messageStage("Insert effettuato");
        } catch (SQLException e) {
            support.messageStageError(e.getMessage());

        } catch (IllegalArgumentException e) {
            support.messageStage("Errore nell'inserimento 2, inserisci tutti campi con ☑");
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo permette di aggiornare un oggetto nella tabella selezionata
     * e nel database
     */
    @FXML
    private void updateDao() {
        TableView tableViewScelta = tableViewHashMap.get(scelta);
        Object objUpdate = tableViewScelta.getSelectionModel().getSelectedItem();
        ArrayList<String> arrayList = null;
        try {
            Method methodObjtoArray = objUpdate.getClass().getMethod("ObjToArrayList");
            arrayList = (ArrayList<String>) methodObjtoArray.invoke(objUpdate);
        } catch (Exception e) {
            System.out.println("Errore reperimento dati" + e.getMessage());
            e.printStackTrace();
        }
        try {
            DAO daoObj = implDaoHashMap.get(scelta);
            daoObj.update(arrayList);
            support.messageStage("Update effettuato");
        } catch (SQLException e) {
            support.messageStageError("1 Errore nell'aggiornamento, inserisci tutti campi con ☑");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            support.messageStageError("2 Errore nell'aggiornamento, inserisci tutti campi con ☑");
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo viene invocato quando si modifica una cella String
     * e permette di modificare l'oggetto selezionato
     *
     * @param stringCellEditEvent evento di modifica di una cella
     */
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
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo viene invocato quando si modifica una cella Integer
     * e permette di modificare la selezionato
     *
     * @param intCellEditEvent evento di modifica di una cella
     */
    @FXML
    private void onEditChangedInteger(TableColumn.CellEditEvent intCellEditEvent) {
        String scelta = comboBoxTableView.getSelectionModel().getSelectedItem();
        String tipoColumn = intCellEditEvent.getTableColumn().getId();
        Integer valoreColumnInt = (Integer) intCellEditEvent.getNewValue();
        String nomeMetodo = "set" + tipoColumn.substring(0, 1).toUpperCase() + tipoColumn.substring(1, tipoColumn.indexOf(scelta));
        try {
            Object test = tableViewHashMap.get(scelta).getSelectionModel().getSelectedItem();
            test.getClass().getMethod(nomeMetodo, Integer.class).invoke(test, valoreColumnInt);
        } catch (Exception e) {
            System.out.println("Errore" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo viene invocato quando si modifica una cella int
     * e permette di modificare la selezionato
     *
     * @param intCellEditEvent evento di modifica di una cella
     */
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
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo viene invocato quando si modifica una cella boolean
     * e permette di modificare la selezionato
     *
     * @param boolCellEditEvent evento di modifica di una cella
     */
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
            e.printStackTrace();
        }
    }
}