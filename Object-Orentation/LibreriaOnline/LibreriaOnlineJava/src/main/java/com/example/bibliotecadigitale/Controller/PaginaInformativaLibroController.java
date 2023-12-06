package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

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


    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,900);
    }
    //todo vanno messe i bottoni e sistemata la grafica
    public void  showInfoLibro(Libro libroPassato) {
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

        if (libroPassato.getTipo().isEmpty()){
            textMateriaId.setVisible(true);
            textMateriaId.setText(libroPassato.getMateria());
        }
        if (libroPassato.getSerie()==null){
            buttonSerieId.setVisible(true);
            buttonSerieId.setText(libroPassato.getSerie());
        }
        buttonAutoreId.setText(libroPassato.getAutore());
        buttonEditoreId.setText(libroPassato.getEditore());

    }

}
