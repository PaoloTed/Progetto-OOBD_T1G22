package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.LibroDAOImpl;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class LibroInformativaController {
    private SupportStage support = new SupportStage();
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
    private Button buttonAquistoId;
    @FXML
    private Button buttonSuccessivoId;
    @FXML
    private Button buttonSerieId;
    @FXML
    private Text textMessagioId;
    @FXML
    private Button buttonPresentazioneid;


    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }

    //todo  sistemata la grafica
    public void showInfoLibro(Libro libroPassato) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        libroMain = libroPassato;
        textTitleIId.setText(libroPassato.getTitolo());
        textIsbnId.setText(libroPassato.getISBN());
        textGenereId.setText(libroPassato.getGenere());
        textNumeroPagineId.setText(String.valueOf(libroPassato.getNumPagine()));
        textTIpoId.setText(libroPassato.getTipo());
        textDescrizioneId.setText(libroPassato.getDescrizione());
        textFruizioneId.setText(libroPassato.getFruizione());
        textDataUscitaId.setText(libroPassato.getDataUscita());
        textLinguianId.setText(libroPassato.getLingua());

        if (libroPassato.getTipo() != null) {
            textMateriaId.setVisible(true);
            textMateriaId.setText(libroPassato.getMateria());
        } else {
            textMateriaId.setVisible(false);
        }

        System.out.println(libroPassato.getSerie());
        if (libroPassato.getSerie() != 0) {
            buttonSerieId.setVisible(true);
            ArrayList<Integer> listaPreferiti = utenteDAO.searchPreferiti(Utente.getUtente().getEmail());
            if (listaPreferiti.contains(libroPassato.getSerie())) {
                buttonSerieId.disableProperty().setValue(true);
                textMessagioId.setText("Serie già presente nei preferiti");
            } else {
                buttonSerieId.disableProperty().setValue(false);
            }
            buttonSerieId.setText(""+libroPassato.getSerie());
            textMessagioId.setVisible(true);
        } else {
            buttonSerieId.setVisible(false);
            textMessagioId.setVisible(false);
        }
        textAutoreId.setText(libroPassato.getAutore());
        txtEditoreId.setText(libroPassato.getEditore());
        if (libroPassato.getSuccessivo() != null) {
            buttonSuccessivoId.setVisible(true);
            buttonSuccessivoId.disableProperty().setValue(false);
        } else {
            buttonSuccessivoId.setVisible(false);
            buttonSuccessivoId.disableProperty().setValue(true);
        }
        if (libroPassato.getPresentazione() != 0) {
            buttonPresentazioneid.setVisible(true);
            buttonPresentazioneid.disableProperty().setValue(false);
        } else {
            buttonPresentazioneid.setVisible(false);
            buttonPresentazioneid.disableProperty().setValue(true);
        }
    }

    public void setPreferito(ActionEvent event) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        utenteDAO.insertPreferiti(Utente.getUtente().getEmail(), libroMain.getSerie());
        textMessagioId.setText("Libro aggiunto ai preferiti");
        buttonSerieId.disableProperty().setValue(true);
    }


    public void goToPresentazione(ActionEvent event)
    {
        support.switchStage("presentazioneStage.fxml", libroMain.getPresentazione());
    }

    public void goToNext(ActionEvent event) throws SQLException {
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        Libro libroSuccessivo = libroDAO.get(libroMain.getSuccessivo());
        showInfoLibro(libroSuccessivo);
        libroMain = libroSuccessivo;
    }

    public void goToAquisto(ActionEvent event)
   {
     support.switchStageAquistiLibri("aquistoStage.fxml", libroMain.getISBN() );
   }
}
