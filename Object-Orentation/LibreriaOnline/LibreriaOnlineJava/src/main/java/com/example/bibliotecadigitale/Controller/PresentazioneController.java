package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.PresentazioneDAOImpl;
import com.example.bibliotecadigitale.Model.Presentazione;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

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

    public void showInfoPresentazione(int codP) {
        PresentazioneDAOImpl presentazioneDAO = new PresentazioneDAOImpl();
        Presentazione presentazione = null;
        try {
            presentazione = new Presentazione(presentazioneDAO.get(codP));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        textCodPId.setText(textCodPId.getText()+presentazione.getCodp());
        textNomeId.setText(textNomeId.getText()+presentazione.getNome());
        textIndizioId.setText(textIndizioId.getText()+presentazione.getIndirizzo());
        textDataPresentazioneId.setText(textDataPresentazioneId.getText()+presentazione.getDatapresentazione());
        textTipoId.setText(textTipoId.getText()+presentazione.getTipo());
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) textCodPId.getScene().getWindow();
        stage.close();
        event.consume();
    }
}
