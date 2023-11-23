package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class PaginaInformativaLibroController {
    private Libro libro;
    public void goToHome(ActionEvent event) {
        SupportStage support = new SupportStage();
        support.switchStage("home.fxml",event);
    }

    public void  showInfoLibro(Libro libroPassato) {
        System.out.println(libro.getTitolo());
        libro = libroPassato;
    }

}
