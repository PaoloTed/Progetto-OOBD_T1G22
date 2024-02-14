package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.RivistaDAOImpl;
import com.example.bibliotecadigitale.Model.Rivista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RivistaController {

    @FXML
    private Text textDataId;
    @FXML
    private Text textNomeId;
    @FXML
    private Text textResponsabileId;
    @FXML
    private Text textArgomentoId;


    public void showInfoRivista(String nome, String data) {
        RivistaDAOImpl rivistaDAO = new RivistaDAOImpl();
        Rivista rivista = rivistaDAO.get(nome, data);
        textDataId.setText(String.valueOf(rivista.getData()));
        textNomeId.setText(rivista.getNome());
        textResponsabileId.setText(rivista.getResponsabile());
        textArgomentoId.setText(rivista.getArgomento());
    }
    public void close (ActionEvent event) {
        Stage stage = (Stage) textNomeId.getScene().getWindow();
        stage.close();
    }
}
