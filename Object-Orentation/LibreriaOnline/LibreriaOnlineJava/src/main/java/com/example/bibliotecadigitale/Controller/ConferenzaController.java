package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.ConferenzaDAOImpl;
import com.example.bibliotecadigitale.Model.Conferenza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;


public class ConferenzaController {
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

    public void showInfoConferenza(int codc) {
        ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
        Conferenza conferenza = null;
        try {
            conferenza = new Conferenza(conferenzaDAO.get(codc));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        textCodCId.setText(textCodCId.getText()+conferenza.getCodc());
        textNomeId.setText(textNomeId.getText()+conferenza.getNome());
        textDataIId.setText(textDataIId.getText()+conferenza.getDatai());
        textDataFId.setText(textDataFId.getText()+conferenza.getDataf());
        textIndizioId.setText(textIndizioId.getText()+conferenza.getIndirizzo());
        textResponsabileId.setText(textResponsabileId.getText()+conferenza.getResponsabile());
        textStrutturaId.setText(textStrutturaId.getText()+conferenza.getStruttura());
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage)  textCodCId.getScene().getWindow();
        stage.close();
        event.consume();
    }
}


