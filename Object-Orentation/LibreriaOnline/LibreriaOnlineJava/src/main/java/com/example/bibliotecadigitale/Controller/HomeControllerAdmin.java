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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeControllerAdmin implements Initializable {
    private String scelta = "libro";
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
    TableColumn<Libro, String> numPagineLibro;
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
    TableColumn<Libro, String> serieLibro;
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
    TableColumn<ArticoloScientifico, String> numPagineArticolo;
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
    TableColumn<ArticoloScientifico, String> conferenzaArticolo;
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
    TableColumn<Conferenza, String> codcConferenza;
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
    TableColumn<Presentazione, String> codpPresentazione;
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
    TableColumn<Serie, String> indirizzoSerie;
    @FXML
    TableColumn<Serie, String> dataSerie;
    @FXML
    TableColumn<Serie, String> tipoSerie;

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
    TableColumn<DisponibileA, String> codaDisponibileA;
    @FXML
    TableColumn<DisponibileA, String> doiDisponibileA;

    //Table view disponibileL
    @FXML
    public TableView<DisponibileL> disponibileLTableView;
    @FXML
    TableColumn<DisponibileL, String> codaDisponibileL;
    @FXML
    TableColumn<DisponibileL, String> isbnDisponibileL;

    //Table view disponibileS
    @FXML
    public TableView<DisponibileS> disponibileSTableView;
    @FXML
    TableColumn<DisponibileS, String> codaDisponibileS;
    @FXML
    TableColumn<DisponibileS, String> codsDisponibileS;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo le colonne della tabella libro
        isbnLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
        titoloLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("titolo"));
        genereLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("genere"));
        numPagineLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("numPagine"));
        materiaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("materia"));
        descrizioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("descrizione"));
        fruizioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("fruizione"));
        editoreLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("editore"));
        autoreLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("autore"));
        dataUscitaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("dataUscita"));
        successivoLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("successivo"));
        serieLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("serie"));
        presentazioneLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("presentazione"));

        //inizializzo le colonne della tabella articolo
        doiArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("doi"));
        titoloArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("titolo"));
        genereArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("genere"));
        numPagineArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("numPagine"));
        dataUscitaArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("dataUscita"));
        descrizioneArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("descrizione"));
        fruizioneArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("fruizione"));
        editoreArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("editore"));
        autoreArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("autore"));
        linguaArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("lingua"));
        conferenzaArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("conferenza"));
        nomerArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("nomer"));
        datarArticolo.setCellValueFactory(new PropertyValueFactory<ArticoloScientifico, String>("datar"));

        //inizializzo le colonne della tabella acquisto
        codaAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("codA"));
        nomeAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("nome"));
        tipoAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("tipo"));
        urlAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("url"));
        indirizzoAcquisto.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("indirizzo"));

        //inizializzo le colonne della tabella conferenza
        codcConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("codC"));
        nomeConfernza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("nome"));
        strutturaConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("struttura"));
        indirizzoConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("indirizzo"));
        dataiConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("datai"));
        datafConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("dataf"));
        responsabileConferenza.setCellValueFactory(new PropertyValueFactory<Conferenza, String>("responsabile"));


        //inizializzo le colonne della tabella presentazione
        codpPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("codP"));
        nomePresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("nome"));
        indirizzoPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("indirizzo"));
        dataPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("data"));
        tipoPresentazione.setCellValueFactory(new PropertyValueFactory<Presentazione, String>("tipo"));

        //inizializzo le colonne della tabella rivista
        nomeRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("nome"));
        dataRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("data"));
        responsabileRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("responsabile"));
        argomentoRivista.setCellValueFactory(new PropertyValueFactory<Rivista, String>("argomento"));

        //inizializzo le colonne della tabella serie
        codsSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("codS"));
        nomeSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("nome"));
        indirizzoSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("indirizzo"));
        dataSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("data"));
        tipoSerie.setCellValueFactory(new PropertyValueFactory<Serie, String>("tipo"));

        //inizializzo le colonne della tabella utente
        emailUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("email"));
        passwordUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("password"));
        dataIscrizioneUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("dataIscrizione"));
        isAdminUtente.setCellValueFactory(new PropertyValueFactory<Utente, String>("isAdmin"));

        //inizializzo le colonne della tabella disponibileA
        codaDisponibileA.setCellValueFactory(new PropertyValueFactory<DisponibileA, String>("codA"));
        doiDisponibileA.setCellValueFactory(new PropertyValueFactory<DisponibileA, String>("doi"));

        //inizializzo le colonne della tabella disponibileL
        codaDisponibileL.setCellValueFactory(new PropertyValueFactory<DisponibileL, String>("codA"));
        isbnDisponibileL.setCellValueFactory(new PropertyValueFactory<DisponibileL, String>("isbn"));

        //inizializzo le colonne della tabella disponibileS
        codaDisponibileS.setCellValueFactory(new PropertyValueFactory<DisponibileS, String>("codA"));
        codsDisponibileS.setCellValueFactory(new PropertyValueFactory<DisponibileS, String>("codS"));

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
                if (libri.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }

                libroTableView.getItems().clear();
                libroTableView.getItems().addAll(libri);
                break;
            case "Articolo":
                ArticoloScientificoDAOImpl articoloScientificoDAO = new ArticoloScientificoDAOImpl();
                ArrayList<ArticoloScientifico> articoli = articoloScientificoDAO.getRicerca(modRicerca, titoloRicerche);
                if (articoli.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }

                articoloTableView.getItems().clear();
                articoloTableView.getItems().addAll(articoli);
                break;
            case "Acquisto":
                AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
                ArrayList<Acquisto> acquisti = acquistoDAO.getRicerca(modRicerca, titoloRicerche);
                if (acquisti.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }
                break;
//            case "Conferenza":
//                ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
//                ArrayList<Conferenza> conferenze = conferenzaDAO.getRicerca(modRicerca, titoloRicerche);
//                if (conferenze.isEmpty()) {
//                    support.messageStage("Nessun match trovato");
//                    idBarSearch.clear();
//                    return;
//                }
//                break;
//            case "Presentazione":
//                PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
//                ArrayList<Presentazione> presentazioni = presentazioneDAO.getRicerca(modRicerca, titoloRicerche);
//                if (presentazioni.isEmpty()) {
//                    support.messageStage("Nessun match trovato");
//                    idBarSearch.clear();
//                    return;
//                }
//                break;
//            case "Rivista":
//                RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
//                ArrayList<Rivista> riviste = rivistaDAO.getRicerca(modRicerca, titoloRicerche);
//                if (riviste.isEmpty()) {
//                    support.messageStage("Nessun match trovato");
//                    idBarSearch.clear();
//                    return;
//                }
//                break;
            case "Serie":
                SerieDAOImpl serieDAO = new SerieDAOImpl();
                ArrayList<Serie> serie = serieDAO.getRicerca(modRicerca, titoloRicerche);
                if (serie.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }
                break;
            case "Utente":
                UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
                ArrayList<Utente> utenti = utenteDAO.getRicerca(modRicerca, titoloRicerche);
                if (utenti.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }
                break;
            case "DisponibileA":
                DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
                ArrayList<DisponibileA> disponibileA = disponibileADAO.getRicerca(modRicerca, titoloRicerche);
                if (disponibileA.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }
                break;
            case "DisponibileL":
                DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
                ArrayList<DisponibileL> disponibileL = disponibileLDAO.getRicerca(modRicerca, titoloRicerche);
                if (disponibileL.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }
                break;
            case "DisponibileS":
                DisponibileSDAOImpl disponibileSDAO = new DisponibileSDAOImpl();
                ArrayList<DisponibileS> disponibileS = disponibileSDAO.getRicerca(modRicerca, titoloRicerche);
                if (disponibileS.isEmpty()) {
                    support.messageStage("Nessun match trovato");
                    idBarSearch.clear();
                    return;
                }
                break;
        }
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
                comboBoxRicerca.setItems(FXCollections.observableArrayList("Cods", "Nome", "Indirizzo", "Data", "Tipo"));
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
}