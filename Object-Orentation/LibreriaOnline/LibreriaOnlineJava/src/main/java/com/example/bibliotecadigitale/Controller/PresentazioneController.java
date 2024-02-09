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

    public void showInfoPresentazione(int codP)
    {
        PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
        Presentazione presentazione = presentazioneDAO.get(codP);
        textCodPId.setText(textCodPId.getText()+""+presentazione.getCodP());
        textNomeId.setText(textNomeId.getText()+presentazione.getNome());
        textIndizioId.setText(textIndizioId.getText()+presentazione.getIndirizzo());
        textDataPresentazioneId.setText(textDataPresentazioneId.getText()+String.valueOf(presentazione.getDataPresentazione()));
        textTipoId.setText(textTipoId.getText()+presentazione.getTipo());
    }

    public void close(ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage) textCodPId.getScene().getWindow();
        stage.close();

    }













}
