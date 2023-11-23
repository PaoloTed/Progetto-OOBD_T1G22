package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class PaginaInformativaLibroController {
    private SupportStage support = new SupportStage();
    private Libro libro;
    public void goToHome(ActionEvent event) {
        support.switchStage("homeStage.fxml",event,900,900);
    }

    public void  showInfoLibro(Libro libroPassato) {
        System.out.println(libroPassato.getTitolo());
        libro = libroPassato;
    }

}
