package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.DisponibileLDAOImpl;
import com.example.bibliotecadigitale.Connection.LibroDAOImpl;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.DisponibileL;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LibroInformativaController implements Initializable {

    @FXML
    private ImageView imageLibriSfondo;
    private final SupportStage support = new SupportStage();
    private Libro libroMain;

    @FXML
    private Text textTitleIId;
    @FXML
    private Text textIsbnId;
    @FXML
    private Text textGenereId;
    @FXML
    private Text textNumeroPagineId;
    @FXML
    private Text textTIpoId;
    @FXML
    private Text textMateriaId;
    @FXML
    private Text textFruizioneId;
    @FXML
    private Text textDataUscitaId;
    @FXML
    private Text textLinguianId;
    @FXML
    private Text textAutoreId;
    @FXML
    private Text txtEditoreId;
    @FXML
    private Button buttonSuccessivoId;
    @FXML
    private Button buttonSerieId;
    @FXML
    private Text textMessagioId;
    @FXML
    private Button buttonPresentazioneid;

    @FXML
    private Button buttonAquistoId;

    @FXML
    private TextArea prova;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
    }


    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }

    /**
     * Questo metodo mostra le informazioni del libro passato come parametro
     * Disabilitando o abilitando i bottoni rivista o acquisto in base alle informazioni del libro
     *
     * @param libroPassato è il libro che si vuole visualizzare le informazioni
     */
    public void showInfoLibro(Libro libroPassato) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        libroMain = libroPassato;
        textTitleIId.setText(textTitleIId.getText() + libroPassato.getTitolo());
        textIsbnId.setText(textIsbnId.getText() + libroPassato.getIsbn());
        textGenereId.setText(textGenereId.getText() + libroPassato.getGenere());
        textNumeroPagineId.setText(textNumeroPagineId.getText() + libroPassato.getNumpagine());
        textTIpoId.setText(textTIpoId.getText() + libroPassato.getTipo());
        prova.setText(libroPassato.getDescrizione());
        prova.setWrapText(true);
        textFruizioneId.setText(textFruizioneId.getText() + libroPassato.getFruizione());
        textDataUscitaId.setText(textDataUscitaId.getText() + libroPassato.getDatauscita());
        textLinguianId.setText(textLinguianId.getText() + libroPassato.getLingua());

        if (libroPassato.getMateria() != null) {
            textMateriaId.setVisible(true);
            textMateriaId.setText("Materia:" + libroPassato.getMateria());
        } else {
            textMateriaId.setVisible(false);
        }

        if (libroPassato.getSerie() != null) {
            buttonSerieId.setVisible(true);
            ArrayList<Integer> listaPreferiti = null;
            try {
                listaPreferiti = utenteDAO.searchPreferiti(Utente.getUtente().getEmail());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (listaPreferiti.contains(libroPassato.getSerie())) {
                buttonSerieId.disableProperty().setValue(true);
                textMessagioId.setText("Serie già presente nei preferiti");
            } else {
                buttonSerieId.disableProperty().setValue(false);
            }
            buttonSerieId.setText("★");
            textMessagioId.setVisible(true);
            if (libroPassato.getSuccessivo() != null) {
                buttonSuccessivoId.setVisible(true);
                buttonSuccessivoId.disableProperty().setValue(false);
            } else {
                buttonSuccessivoId.disableProperty().setValue(true);
            }
        } else {
            buttonSerieId.setVisible(false);
            textMessagioId.setVisible(false);
            buttonSuccessivoId.disableProperty().setValue(true);
        }
        textAutoreId.setText(textAutoreId.getText() + libroPassato.getAutore());
        txtEditoreId.setText(txtEditoreId.getText() + libroPassato.getEditore());

        if (libroPassato.getPresentazione() != null) {
            buttonPresentazioneid.disableProperty().setValue(false);
        } else {
            buttonPresentazioneid.disableProperty().setValue(true);
        }
        ArrayList<ArrayList<String>> disponibileLarrayString = new ArrayList<>();
        ArrayList<DisponibileL> disponibileLarray = new ArrayList<>();
        try {
            DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
            disponibileLarrayString = disponibileLDAO.getAcquisti(libroPassato.getIsbn());
            for (ArrayList<String> strings : disponibileLarrayString) {
                DisponibileL disponibileL = new DisponibileL(strings);
                disponibileLarray.add(disponibileL);
            }
            if (!disponibileLarray.isEmpty()) {
                buttonAquistoId.disableProperty().setValue(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo permette di aggiungere la serie ai preferiti
     *
     * @param event evento che viene invocato quando si preme il tasto preferito
     */
    public void setPreferito(ActionEvent event) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        try {
            utenteDAO.insertPreferiti(Utente.getUtente().getEmail(), libroMain.getSerie());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        textMessagioId.setText("Serie aggiunto ai preferiti");
        buttonSerieId.disableProperty().setValue(true);
        event.consume();
    }

    public void goToPresentazione(ActionEvent event) {
        if (libroMain.getPresentazione() == null) {
            support.messageStage("Il libro non ha una presentazione");
            return;
        }
        Stage stage = (Stage) buttonPresentazioneid.getScene().getWindow();
        stage.close();
        support.switchStagePresentazione("presentazioneStage.fxml", libroMain.getPresentazione());
        event.consume();
    }

    /**
     * Questo metodo permette di andare alla pagina informativa del libro successivo
     * a quello attuale
     *
     * @param event evento che viene invocato quando si preme il tasto successivo
     * @throws SQLException eccezione che viene lanciata quando si verifica un errore nel database
     */
    public void goToNext(ActionEvent event) throws SQLException {
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        ArrayList<String> libro = libroDAO.get(libroMain.getSuccessivo());
        Libro libroSuccessivo = new Libro(libro);
        libroMain.setLibroSuccessivo(libroSuccessivo);
        textTitleIId.setText("Titolo:");
        textIsbnId.setText("Isbn:");
        textGenereId.setText("Genere:");
        textNumeroPagineId.setText("Numero pagine:");
        textTIpoId.setText("");
        textMateriaId.setText("Materia:");
        textFruizioneId.setText("Fruizione:");
        textDataUscitaId.setText("Data Uscita:");
        textLinguianId.setText("Lingua:");
        textAutoreId.setText("Autore:");
        txtEditoreId.setText("Editore:");
        showInfoLibro(libroMain.getLibroSuccessivo());
        event.consume();
    }

    public void goToAcquisto(ActionEvent event) {
        Stage stage = (Stage) buttonAquistoId.getScene().getWindow();
        stage.close();
        support.switchStageAcquistiLibri("acquistoStage.fxml", libroMain.getIsbn());
        event.consume();
    }
}