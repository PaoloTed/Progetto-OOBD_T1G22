package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.ConferenzaDAOImpl;
import com.example.bibliotecadigitale.Model.Conferenza;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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

    @FXML
    private Button buttonClose;


    public void showInfoConferenza(int codc)
    {
        ConferenzaDAOImpl conferenzaDAO = new ConferenzaDAOImpl();
        Conferenza conferenza = conferenzaDAO.get(codc);
        textCodCId.setText(textCodCId.getText()+conferenza.getCodc());
        textNomeId.setText(textNomeId.getText()+conferenza.getNome());
        textDataIId.setText(textDataIId.getText()+conferenza.getDatai());
        textDataFId.setText(textDataFId.getText()+conferenza.getDataf());
        textIndizioId.setText(textIndizioId.getText()+conferenza.getIndirizzo());
        textResponsabileId.setText(textResponsabileId.getText()+conferenza.getResponsabile());
        textStrutturaId.setText(textStrutturaId.getText()+conferenza.getStruttura());

    }

    public void close(ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage)  textCodCId.getScene().getWindow();
        stage.close();
    }
}


