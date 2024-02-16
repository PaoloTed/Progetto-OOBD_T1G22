package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.LibroDAOImpl;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
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
    private Text textDescrizioneId;
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
    private TextArea prova;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
    }


    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }

    public void showInfoLibro(Libro libroPassato) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        libroMain = libroPassato;
        textTitleIId.setText(textTitleIId.getText()+libroPassato.getTitolo());
        textIsbnId.setText(textIsbnId.getText()+libroPassato.getIsbn());
        textGenereId.setText(textGenereId.getText()+libroPassato.getGenere());
        textNumeroPagineId.setText(textNumeroPagineId.getText()+libroPassato.getNumpagine());
        textTIpoId.setText(textTIpoId.getText()+libroPassato.getTipo());
        prova.setText(libroPassato.getDescrizione());
        prova.setWrapText(true);
//        textDescrizioneId.setText(textDescrizioneId.getText()+"\n"+libroPassato.getDescrizione());
        textFruizioneId.setText(textFruizioneId.getText()+libroPassato.getFruizione());
        textDataUscitaId.setText(textDataUscitaId.getText()+libroPassato.getDatauscita());
        textLinguianId.setText(textLinguianId.getText()+libroPassato.getLingua());

        if (libroPassato.getMateria() != null) {
            textMateriaId.setVisible(true);
            textMateriaId.setText(textMateriaId.getText()+libroPassato.getMateria());
        } else {
            textMateriaId.setVisible(false);
        }

        if (libroPassato.getSerie() != null) {
            buttonSerieId.setVisible(true);
            ArrayList<Integer> listaPreferiti = utenteDAO.searchPreferiti(Utente.getUtente().getEmail());
            if (listaPreferiti.contains(libroPassato.getSerie())) {
                buttonSerieId.disableProperty().setValue(true);
                textMessagioId.setText("Serie già presente nei preferiti");
            } else {
                buttonSerieId.disableProperty().setValue(false);
            }
            buttonSerieId.setText("" + libroPassato.getSerie());
            textMessagioId.setVisible(true);
            if (libroPassato.getSuccessivo() != null) {
                buttonSuccessivoId.setVisible(true);
                buttonSuccessivoId.disableProperty().setValue(false);
            } else {
//                buttonSuccessivoId.setVisible(false);
                buttonSuccessivoId.disableProperty().setValue(true);
            }
        } else {
            buttonSerieId.setVisible(false);
            textMessagioId.setVisible(false);
        }
        textAutoreId.setText(textAutoreId.getText()+libroPassato.getAutore());
        txtEditoreId.setText(txtEditoreId.getText()+libroPassato.getEditore());

        if (libroPassato.getPresentazione() != null) {
            //buttonPresentazioneid.setVisible(true);
            buttonPresentazioneid.disableProperty().setValue(false);
        } else {
           // buttonPresentazioneid.setVisible(false);
            buttonPresentazioneid.disableProperty().setValue(true);
        }
    }

    public void setPreferito(ActionEvent event) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        utenteDAO.insertPreferiti(Utente.getUtente().getEmail() , libroMain.getSerie());
        textMessagioId.setText("Serie aggiunto ai preferiti");
        buttonSerieId.disableProperty().setValue(true);
        event.consume();
    }

    public void goToPresentazione(ActionEvent event) {
        if(libroMain.getPresentazione() == null){
            support.messageStage("Il libro non ha una presentazione");
            return;
        }
        support.switchStagePresentazione("presentazioneStage.fxml", libroMain.getPresentazione());
        event.consume();
    }

    public void goToNext(ActionEvent event) throws SQLException {
        textTitleIId.setText("Titolo:");
        textIsbnId.setText("Isbn:");
        textGenereId.setText("Genere:");
        textNumeroPagineId.setText("Numero pagine:");
        textTIpoId.setText("");
        textMateriaId.setText("Materia:");
//        textDescrizioneId.setText("");
        textFruizioneId.setText("Fruizione:");
        textDataUscitaId.setText("Data Uscita:");
        textLinguianId.setText("Lingua:");
        textAutoreId.setText("Autore:");
        txtEditoreId.setText("Editore:");
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        Libro libroSuccessivo = libroDAO.get(libroMain.getSuccessivo());
        showInfoLibro(libroSuccessivo);
        libroMain = libroSuccessivo;
        event.consume();
    }

    public void goToAcquisto(ActionEvent event) {
        support.switchStageAquistiLibri("acquistoStage.fxml", libroMain.getIsbn());
        event.consume();
    }
}