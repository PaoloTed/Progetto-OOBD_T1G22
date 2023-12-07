package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PaginaInformativaLibroController {
    private SupportStage support = new SupportStage();
    private Libro libro;

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
    private Button buttonAutoreId;
    @FXML
    private Button buttonEditoreId;
    @FXML
    private Button buttonAquistoId;
    @FXML
    private Button buttonSuccessivoId;
    @FXML
    private Button buttonSerieId;
    @FXML
    private Text textMessagioId;


    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,900);
    }
    //todo  sistemata la grafica
    public void  showInfoLibro(Libro libroPassato) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        libro = libroPassato;
        textTitleIId.setText(libroPassato.getTitolo());
        textIsbnId.setText(libroPassato.getISBN());
        textGenereId.setText(libroPassato.getGenere());
        textNumeroPagineId.setText(String.valueOf(libroPassato.getNumeroPagine()));
        textTIpoId.setText(libroPassato.getTipo());
        textDescrizioneId.setText(libroPassato.getDescrizione());
        textFruizioneId.setText(libroPassato.getFruizione());
        textDataUscitaId.setText(libroPassato.getDatauscita());
        textLinguianId.setText(libroPassato.getLingua());

        if (!libroPassato.getTipo().isEmpty()){
            textMateriaId.setVisible(true);
            textMateriaId.setText(libroPassato.getMateria());
        }

        System.out.println(libroPassato.getSerie());

        if (!libroPassato.getSerie().isEmpty()){
            buttonSerieId.setVisible(true);
            ArrayList<String> listaPreferiti= utenteDAO.searchPreferiti(Utente.getUtente().getEmail());
            if(listaPreferiti.contains(libroPassato.getSerie()))
            {
                buttonSerieId.disableProperty().setValue(true);
                textMessagioId.setText("Serie già presente nei preferiti");
            }
            buttonSerieId.setText(libroPassato.getSerie());
            textMessagioId.setVisible(true);
        }
        buttonAutoreId.setText(libroPassato.getAutore());
        buttonEditoreId.setText(libroPassato.getEditore());

    }

    public void setPreferito(ActionEvent event) {
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        utenteDAO.insertPreferiti(Utente.getUtente().getEmail(), libro.getSerie());
        textMessagioId.setText("Libro aggiunto ai preferiti");
        buttonSerieId.disableProperty().setValue(true);
    }

}
