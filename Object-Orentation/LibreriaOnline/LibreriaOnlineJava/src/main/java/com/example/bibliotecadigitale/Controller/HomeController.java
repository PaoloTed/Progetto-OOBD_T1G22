package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.LibroDAO;
import com.example.bibliotecadigitale.Model.Libro;
import com.example.bibliotecadigitale.SupportStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    private TextField idBarSearch;
    public SupportStage support = new SupportStage();
    @FXML
    public ListView<String> listViewLibri;

    @FXML
    void Select(ActionEvent event) {
        LibroDAO libroDAO = new LibroDAO();
        String scelta =idComboBox.getSelectionModel().getSelectedItem();
        System.out.println(scelta);
        ArrayList<String> isbn = libroDAO.search(scelta,idBarSearch.getText());
        Libro libro = new Libro();
        String titolo;
        String autore;
        String genere;
        String editore;
        String isbnAppoggio;
        for (int i = 0; i < isbn.size(); i++) {
            libro = libroDAO.findLibroFromCodev2(isbn.get(i));
            titolo = libro.getTitolo();
            autore = libro.getAutore();
            genere = libro.getGenere();
            editore = libro.getEditore();
            isbnAppoggio = libro.getISBN();
            listViewLibri.getItems().add(isbnAppoggio + " - " + titolo + " - " + autore + " - " + genere + " - " + editore);
        }
    }
    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStage.fxml",event);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
       idComboBox.setItems(FXCollections.observableArrayList("Titolo","Autore","Genere","Editore"));

    }
}