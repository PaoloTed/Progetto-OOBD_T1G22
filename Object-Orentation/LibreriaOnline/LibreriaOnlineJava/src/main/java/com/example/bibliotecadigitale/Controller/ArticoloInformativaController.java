package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.ArticoloScientifico;

import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ArticoloInformativaController {
    private SupportStage support = new SupportStage();
    private ArticoloScientifico ArticoloMain;

    @FXML
    private Text textTitleIId;
    @FXML
    private Text textDoiId;
    @FXML
    private Text textGenereId;
    @FXML
    private Text textNumeroPagineId;
    @FXML
    private Text textDescrizioneId;
    @FXML
    private Text textFruizioneId;
    @FXML
    private Text textDataUscitaId;
    @FXML
    private Text textLinguianId;
    @FXML
    private Text textAutoreId;
    @FXML
    private Text txtEditoreId;
    @FXML
    private Button buttonAquistoId;
    @FXML
    private Button buttonConferenzaId;
    @FXML
    private Text textMessagioId;
    @FXML
    private Button buttonRivistaId;

    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }

    public void showInfoArticolo(ArticoloScientifico articlopassato) {
        ArticoloMain = articlopassato;
        textTitleIId.setText(textTitleIId.getText()+articlopassato.getTitolo());
        textDoiId.setText(textDoiId.getText()+articlopassato.getDoi());
        textGenereId.setText(textGenereId.getText()+articlopassato.getGenere());
        textNumeroPagineId.setText(textNumeroPagineId.getText()+String.valueOf(articlopassato.getNumPagine()));
        textDescrizioneId.setText(textDescrizioneId.getText()+"\n"+articlopassato.getDescrizione());
        textFruizioneId.setText(textFruizioneId.getText()+articlopassato.getFruizione());
        textDataUscitaId.setText(textDataUscitaId.getText()+String.valueOf(articlopassato.getDataUscita()));
        textLinguianId.setText(textLinguianId.getText()+articlopassato.getLingua());
        textAutoreId.setText(textAutoreId.getText()+articlopassato.getAutore());
        txtEditoreId.setText(txtEditoreId.getText()+articlopassato.getEditore());
        if (articlopassato.getConferenza() != 0) {
            buttonConferenzaId.setVisible(true);
        } else {
            buttonConferenzaId.setVisible(false);
        }

        if ((articlopassato.getNomer() != null) && (articlopassato.getDatar() != null)) {
            textMessagioId.setVisible(true);
            buttonRivistaId.setVisible(true);

        } else {
            textMessagioId.setVisible(false);
            buttonRivistaId.setVisible(false);

        }
    }

    public void goToConferenza(ActionEvent event) {
        support.switchStage("conferenzaStage.fxml", ArticoloMain.getConferenza());
    }

    public void goToRivista(ActionEvent event) {
        support.switchStage("rivistaStage.fxml", ArticoloMain.getNomer(), ArticoloMain.getDatar());
    }

    public void goToAquisto(ActionEvent event) {
        support.switchStageAquistiArticoli("aquistoStage.fxml", ArticoloMain.getDoi());
    }
}
