package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.ArticoloScientifico;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ArticoloInformativaController implements Initializable {
    private final SupportStage support = new SupportStage();
    private ArticoloScientifico ArticoloMain;

    @FXML
    private Text textTitleId;
    @FXML
    private Text textDoiId;
    @FXML
    private Text textGenereId;
    @FXML
    private Text textNumeroPagineId;
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
    private Button buttonConferenzaId;
    @FXML
    private Text textMessaggioId;
    @FXML
    private Button buttonRivistaId;
    @FXML
    private ImageView imageLibriSfondo;
    @FXML
    private TextArea IdAreaDescrizione;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
    }

    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }

    /**
     * Questo metodo permette di visualizzare le informazioni di un articolo scientifico
     * disattivando o attivando i bottoni per visualizzare le informazioni di una conferenza o di una rivista
     * a seconda che l'articolo scientifico sia stato presentato in una conferenza o presente in una rivista
     *
     * @param articolopassato è l'articolo scientifico di cui si vuole visualizzare le informazioni
     */
    public void showInfoArticolo(ArticoloScientifico articolopassato) {
        ArticoloMain = articolopassato;
        textTitleId.setText(textTitleId.getText() + articolopassato.getTitolo());
        textDoiId.setText(textDoiId.getText() + articolopassato.getDoi());
        textGenereId.setText(textGenereId.getText() + articolopassato.getGenere());
        textNumeroPagineId.setText(textNumeroPagineId.getText() + (articolopassato.getNumpagine()));
        IdAreaDescrizione.setText(articolopassato.getDescrizione());
        textFruizioneId.setText(textFruizioneId.getText() + articolopassato.getFruizione());
        textDataUscitaId.setText(textDataUscitaId.getText() + (articolopassato.getDatauscita()));
        textLinguianId.setText(textLinguianId.getText() + articolopassato.getLingua());
        textAutoreId.setText(textAutoreId.getText() + articolopassato.getAutore());
        txtEditoreId.setText(txtEditoreId.getText() + articolopassato.getEditore());
        if (articolopassato.getConferenza() != null) {
            buttonConferenzaId.setVisible(true);
        } else {
            buttonConferenzaId.setVisible(false);
        }
        if ((articolopassato.getNomer() != null) && (articolopassato.getDatar() != null)) {
            textMessaggioId.setVisible(true);
            buttonRivistaId.setVisible(true);
        } else {
            textMessaggioId.setVisible(false);
            buttonRivistaId.setVisible(false);
        }
    }

    public void goToConferenza(ActionEvent event) {
        support.switchStageConferenza("conferenzaStage.fxml", ArticoloMain.getConferenza());
        event.consume();
    }

    public void goToRivista(ActionEvent event) {
        support.switchStageRivista("rivistaStage.fxml", ArticoloMain.getNomer(), ArticoloMain.getDatar());
        event.consume();
    }

    public void goToAcquisto(ActionEvent event) {
        Stage stage = (Stage) buttonRivistaId.getScene().getWindow();
        stage.close();
        support.switchStageAcquistiArticoli("acquistoStage.fxml", ArticoloMain.getDoi());
        event.consume();
    }
}
