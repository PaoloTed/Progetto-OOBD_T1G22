package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.PresentazioneDAOImpl;
import com.example.bibliotecadigitale.Connection.UtenteDAOImpl;
import com.example.bibliotecadigitale.DAO.PresentazioneDAO;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.Model.Presentazione;
import com.example.bibliotecadigitale.Model.Utente;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class PresentazioneStageController {

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
        textCodPId.setText(presentazione.getCodP());
        textNomeId.setText(presentazione.getNome());
        textDataPresentazioneId.setText(String.valueOf(presentazione.getDataPresentazione()));
        textTipoId.setText(presentazione.getTipo());
    }

    public void close(ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage) textCodPId.getScene().getWindow();
        stage.close();

    }













}
