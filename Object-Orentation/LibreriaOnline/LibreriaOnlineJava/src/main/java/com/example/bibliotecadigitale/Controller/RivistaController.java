package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.RivistaDAOImpl;
import com.example.bibliotecadigitale.Model.Rivista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

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
        Rivista rivista;
        try {
            rivista = new Rivista(rivistaDAO.get(nome, data));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        textNomeId.setText(textNomeId.getText()+rivista.getNome());
        textDataId.setText(textDataId.getText()+String.valueOf(rivista.getData()));
        textResponsabileId.setText(textResponsabileId.getText()+rivista.getResponsabile());
        textArgomentoId.setText(textArgomentoId.getText()+rivista.getArgomento());
    }
    public void close (ActionEvent event) {
        Stage stage = (Stage) textNomeId.getScene().getWindow();
        stage.close();
        event.consume();
    }
}
