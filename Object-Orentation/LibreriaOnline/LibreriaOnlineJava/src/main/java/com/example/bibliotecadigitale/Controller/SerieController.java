package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.*;
import com.example.bibliotecadigitale.Model.*;
import com.example.bibliotecadigitale.SupportStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SerieController implements Initializable {

    public SupportStage support = new SupportStage();


    @FXML
    private Text codSTxtId;
    @FXML
    private Text nomeTxtId;
    @FXML
    private Text numLibTxtId;
    @FXML
    private Text completaTxtId;

    @FXML
    public TableView<Libro> libroTableView;

    @FXML
    TableColumn<Libro, String> isbnColumn;
    @FXML
    TableColumn<Libro, String> titoloColumn;
    @FXML
    TableColumn<Libro, String> autoreColumn;
    @FXML
    TableColumn<Libro, String> editoreColumn;
    @FXML
    TableColumn<Libro, String> genereColumn;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo le colonne della tabella libro

        isbnColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
        titoloColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("titolo"));
        autoreColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("autore"));
        editoreColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("editore"));
        genereColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("genere"));

    }

    public void showSerie(int CodS)
    {
        SerieDAOImpl serieDAO = new SerieDAOImpl();
        Serie serie = serieDAO.get(String.valueOf(CodS));
        codSTxtId.setText("codice serie: "+String.valueOf(serie.getCodS()));
        nomeTxtId.setText("Nome serie: "+serie.getNome());
        numLibTxtId.setText("numero libri: "+String.valueOf(serie.getNumLibri()));
        if (serie.getCompletata())
            completaTxtId.setText("stato serie:Completata");
        else
            completaTxtId.setText("No Terminata");

        LibroDAOImpl libroDAO = new LibroDAOImpl();
        ArrayList<Libro> libri = libroDAO.getRicerca("serie", String.valueOf(CodS));
        libroTableView.getItems().setAll(libri);




    }

    public void goToPaginaInformativaLibro(ActionEvent event)  {

    }


    public void close(ActionEvent event) {
        SupportStage support = new SupportStage();
        Stage stage = (Stage) codSTxtId.getScene().getWindow();
        stage.close();

    }


}
