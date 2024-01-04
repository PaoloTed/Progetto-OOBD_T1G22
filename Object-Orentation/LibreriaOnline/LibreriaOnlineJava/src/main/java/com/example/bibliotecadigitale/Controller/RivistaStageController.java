package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.PresentazioneDAOImpl;
import com.example.bibliotecadigitale.Connection.RivistaDAOImpl;
import com.example.bibliotecadigitale.Model.Presentazione;
import com.example.bibliotecadigitale.Model.Rivista;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RivistaStageController {

    @FXML
    private Text textDataId;
    @FXML
    private Text textNomeId;
    @FXML
    private Text textResponsabileId;
    @FXML
    private Text textArgomentoId;
    @FXML
    private Button buttonClose;

    public void showInfoRivista(String data, String nome) {
        RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
        Rivista rivista = rivistaDAO.get(data, nome);
        textDataId.setText(String.valueOf(rivista.getData()));
        textNomeId.setText(rivista.getNome());
        textResponsabileId.setText(rivista.getResponsabile());
        textArgomentoId.setText(rivista.getArgomento());

    }

    public void close (ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage) textNomeId.getScene().getWindow();
        stage.close();

    }













}
