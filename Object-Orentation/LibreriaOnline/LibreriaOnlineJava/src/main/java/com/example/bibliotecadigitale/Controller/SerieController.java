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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SerieController implements Initializable {

    private final SupportStage support = new SupportStage();


    @FXML
    private Text codSTxtId;
    @FXML
    private Text nomeTxtId;
    @FXML
    private Text numLibTxtId;
    @FXML
    private Text completaTxtId;
    @FXML
    private ImageView imageLibriSfondo;

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
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titoloColumn.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        autoreColumn.setCellValueFactory(new PropertyValueFactory<>("autore"));
        editoreColumn.setCellValueFactory(new PropertyValueFactory<>("editore"));
        genereColumn.setCellValueFactory(new PropertyValueFactory<>("genere"));
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
    }

    /**
     * Mostra le informazioni della serie selezionata
     *
     * @param CodS Codice della serie selezionata da mostrare le info
     */
    public void showSerie(int CodS) {
        SerieDAOImpl serieDAO = new SerieDAOImpl();
        Serie serie = null;
        try {
            serie = new Serie(serieDAO.get(CodS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        codSTxtId.setText("codice serie: " + serie.getCods());
        nomeTxtId.setText("Nome serie: " + serie.getNome());
        numLibTxtId.setText("numero libri: " + serie.getNumlibri());
        if (serie.getCompletata()) {
            completaTxtId.setText("stato serie:Completata");
        } else {
            completaTxtId.setText("No Terminata");
        }

        LibroDAOImpl libroDAO = new LibroDAOImpl();
        ArrayList<Libro> libri = new ArrayList<>();
        ArrayList<ArrayList<String>> libriStrings = new ArrayList<>();
        try {
            libriStrings = libroDAO.getRicerca("serie", String.valueOf(CodS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ArrayList<String> libroString : libriStrings) {
            try {
                libri.add(new Libro(libroDAO.get(libroString.get(0))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        serie.setLibri(libri);
        libroTableView.getItems().setAll(serie.getLibri());
    }

    public void goToPaginaInformativaLibro(ActionEvent event) {
        Libro libro = libroTableView.getSelectionModel().getSelectedItem();
        if (libro == null) {
            support.messageStage("Selezionare prima un libro");
            return;
        }
        Stage stage = (Stage) libroTableView.getScene().getWindow();
        stage.close();
        support.switchStageLibro("paginaInformativaLibro.fxml", libro);
        event.consume();
    }

    public void close(ActionEvent event) {
        support.switchStage("homeStage.fxml", event, 900, 800);
    }
}
