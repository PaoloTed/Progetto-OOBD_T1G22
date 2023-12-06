package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PaginaInformativaLibroController {
    private SupportStage support = new SupportStage();
    private Libro libro;

    @FXML
    private Text textTitleIId;
    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,900);
    }

    public void  showInfoLibro(Libro libroPassato) {
        libro = libroPassato;
        textTitleIId.setText(libroPassato.getTitolo());
    }

}
