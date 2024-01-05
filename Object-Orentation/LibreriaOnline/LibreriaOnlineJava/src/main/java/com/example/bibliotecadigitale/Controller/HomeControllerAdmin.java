package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.*;
import com.example.bibliotecadigitale.Model.*;
import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeControllerAdmin implements Initializable {
    //todo se l'acquisto non ha una conferenza esce zero invece di null, risolvere
    private String scelta = "libro";
    @FXML
    private Button buttonCerca;
    @FXML
    private TextField idBarSearch;
    private SupportStage support = new SupportStage();
    @FXML
    private ComboBox<String> comboBoxTableView;
    @FXML
    private ComboBox<String> comboBoxRicerca;

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
    TableColumn<Libro, String> presentazioneLibro;

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
    TableColumn<Acquisto, String> codaAcquisto;
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
    TableColumn<Serie, String> codsSerie;
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


    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo le colonne della tabella libro
        isbnLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
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
        presentazioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("presentazione"));
        presentazioneLibro.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella articolo
        doiArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("doi"));
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
        datarArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("datar"));

        //inizializzo le colonne della tabella acquisto
        codaAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("codA"));
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
        nomeConfernza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("nome"));
        nomeConfernza.setCellFactory(TextFieldTableCell.forTableColumn());
        strutturaConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("struttura"));
        strutturaConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        indirizzoConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("indirizzo"));
        indirizzoConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        dataiConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("datai"));
        dataiConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        datafConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("dataf"));
        datafConferenza.setCellFactory(TextFieldTableCell.forTableColumn());
        responsabileConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("responsabile"));
        responsabileConferenza.setCellFactory(TextFieldTableCell.forTableColumn());


        //inizializzo le colonne della tabella presentazione
        codpPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, Integer>("codP"));
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
        dataRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("data"));
        dataRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        responsabileRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("responsabile"));
        responsabileRivista.setCellFactory(TextFieldTableCell.forTableColumn());
        argomentoRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("argomento"));
        argomentoRivista.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella serie
        codsSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("codS"));
        nomeSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("nome"));
        nomeSerie.setCellFactory(TextFieldTableCell.forTableColumn());
        numLibriSerie.setCellValueFactory(new PropertyValueFactory<Serie, Integer>("numLibri"));
        completataSerie.setCellValueFactory(new PropertyValueFactory<Serie, Boolean>("completata"));
        completataSerie.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));


        //inizializzo le colonne della tabella utente
        emailUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("email"));
        passwordUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("password"));
        passwordUtente.setCellFactory(TextFieldTableCell.forTableColumn());
        dataIscrizioneUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("dataIscrizione"));
        dataIscrizioneUtente.setCellFactory(TextFieldTableCell.forTableColumn());
        isAdminUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("isAdmin"));
        isAdminUtente.setCellFactory(TextFieldTableCell.forTableColumn());

        //inizializzo le colonne della tabella disponibileA
        codaDisponibileA.setCellValueFactory(new PropertyValueFactory<DisponibileA, Integer>("codA"));
        doiDisponibileA.setCellValueFactory(new PropertyValueFactory<DisponibileA, String>("doi"));

        //inizializzo le colonne della tabella disponibileL
        codaDisponibileL.setCellValueFactory(new PropertyValueFactory<DisponibileL, Integer>("codA"));
        isbnDisponibileL.setCellValueFactory(new PropertyValueFactory<DisponibileL, String>("isbn"));

        //inizializzo le colonne della tabella disponibileS
        codaDisponibileS.setCellValueFactory(new PropertyValueFactory<DisponibileS, Integer>("codA"));
        codsDisponibileS.setCellValueFactory(new PropertyValueFactory<DisponibileS, Integer>("codS"));

        //Imposto la ricerca su libro come default e nascondo la tabella articolo
        setVisibleFalseAllTableView();
        libroTableView.setVisible(true);
        comboBoxTableView.setItems(FXCollections.observableArrayList("Libro", "Articolo", "Acquisto", "Conferenza", "Presentazione", "Rivista", "Serie", "Utente", "DisponibileA", "DisponibileL", "DisponibileS"));
        comboBoxTableView.getSelectionModel().selectFirst();
        comboBoxRicerca.setItems(FXCollections.observableArrayList("Isbn", "Titolo", "Genere", "Autore", "Editore", "DataUscita", "NumPagine", "Materia", "Descrizione", "Fruizione", "Successivo", "Serie", "Presentazione", "Lingua"));
        comboBoxRicerca.getSelectionModel().selectFirst();
    }

    @FXML
    void Select(ActionEvent event) {
        if(!buttonCerca.isDisable()){
        }
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
        buttonCerca.setDisable(true);

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
//            case "Conferenza":
//                ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
//                ArrayList<Conferenza> conferenze = conferenzaDAO.getRicerca(modRicerca, titoloRicerche);
//                idBarSearch.clear();
//                conferenzaTableView.getItems().clear();
//                if (conferenze.isEmpty()) {
//                    support.messageStage("Nessun match trovato");
//                    return;
//                }
//                conferenzaTableView.getItems().addAll(conferenze);
//                break;
//            case "Presentazione":
//                PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
//                ArrayList<Presentazione> presentazioni = presentazioneDAO.getRicerca(modRicerca, titoloRicerche);
//                presentazioneTableView.getItems().clear();
//                idBarSearch.clear();
//                if (presentazioni.isEmpty()) {
//                    support.messageStage("Nessun match trovato");
//                    return;
//                }
//                presentazioneTableView.getItems().addAll(presentazioni);
//                break;
//            case "Rivista":
//                RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
//                ArrayList<Rivista> riviste = rivistaDAO.getRicerca(modRicerca, titoloRicerche);
//                rivistaTableView.getItems().clear();
//                idBarSearch.clear();
//                if (riviste.isEmpty()) {
//                    support.messageStage("Nessun match trovato");
//                    return;
//                }
//                rivistaTableView.getItems().addAll(riviste);
//                break;
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
        buttonCerca.setDisable(false);
    }

    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStage.fxml", event);
    }


    public void logOff(ActionEvent event) throws IOException {
        Utente.getUtente().exitUtente();
        support.switchStage("welcomeStage.fxml", event);
    }

    public void setVisibleFalseAllTableView() {
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
    }
    private void cellFactoryInitialize(){

    }
}