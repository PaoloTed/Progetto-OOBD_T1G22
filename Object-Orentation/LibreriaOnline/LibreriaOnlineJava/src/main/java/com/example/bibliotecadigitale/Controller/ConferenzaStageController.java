package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.ConferenzaDAOImpl;
import com.example.bibliotecadigitale.Connection.PresentazioneDAOImpl;
import com.example.bibliotecadigitale.Model.Conferenza;
import com.example.bibliotecadigitale.Model.Presentazione;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.awt.*;


public class ConferenzaStageController {
    @FXML
    private Text textCodCId;
    @FXML
    private Text textNomeId;
    @FXML
    private Text textDataIId;
    @FXML
    private Text textDataFId;
    @FXML
    private Text textIndizioId;
    @FXML
    private Text textResponsabileId;
    @FXML
    private Text textStrutturaId;

    @FXML
    private Button buttonClose;


    public void showInfoConferenza(int codc)
    {
        ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
        Conferenza conferenza = conferenzaDAO.get(codc);
        textCodCId.setText(String.valueOf(conferenza.getCodC()));
        textNomeId.setText(conferenza.getNome());
        textDataIId.setText(String.valueOf(conferenza.getDataI()));
        textDataFId.setText(String.valueOf(conferenza.getDataF()));
        textIndizioId.setText(conferenza.getIndirizzo());
        textResponsabileId.setText(conferenza.getResponsabile());
        textStrutturaId.setText(conferenza.getStruttura());

    }


    public void close(ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage)  textCodCId.getScene().getWindow();
        stage.close();
    }
}


