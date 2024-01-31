package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.PresentazioneDAOImpl;
import com.example.bibliotecadigitale.Model.Presentazione;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PresentazioneController {

    @FXML
    private Text textCodPId;
    @FXML
    private Text textNomeId;
    @FXML
    private Text textDataPresentazioneId;
    @FXML
    private Text textIndizioId;
    @FXML
    private Text textTipoId;

    public void showInfoPresentazione(String codP)
    {
        PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
        Presentazione presentazione = presentazioneDAO.get(codP);
        textCodPId.setText(""+presentazione.getCodP());
        textNomeId.setText(presentazione.getNome());
        textIndizioId.setText(presentazione.getIndirizzo());
        textDataPresentazioneId.setText(String.valueOf(presentazione.getDataPresentazione()));
        textTipoId.setText(presentazione.getTipo());
    }

    public void close(ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage) textCodPId.getScene().getWindow();
        stage.close();

    }













}
