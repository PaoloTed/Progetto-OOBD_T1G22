package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.LibroDAOImpl;
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
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        String scelta =idComboBox.getSelectionModel().getSelectedItem();
        listViewLibri.getItems().clear();
        System.out.println(scelta);
        ArrayList<Libro> libri = libroDAO.getRicerca(scelta,idBarSearch.getText());
        String titolo;
        String autore;
        String genere;
        String editore;
        String isbnAppoggio;
        for (int i = 0; i < libri.size(); i++) {
            titolo = libri.get(i).getTitolo();
            autore = libri.get(i).getAutore();
            genere = libri.get(i).getGenere();
            editore = libri.get(i).getEditore();
            isbnAppoggio = libri.get(i).getISBN();
            listViewLibri.getItems().add(isbnAppoggio + " - " + titolo + " - " + autore + " - " + genere + " - " + editore);
        }
    }
    @FXML
    public void goToInfoUser(ActionEvent event) {
        support.switchStage("infoUserStage.fxml",event);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
       idComboBox.setItems(FXCollections.observableArrayList("Titolo","Autore","Genere","Editore"));
        idComboBox.getSelectionModel().selectFirst();

    }
}