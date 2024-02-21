package com.example.bibliotecadigitale.Controller;

import com.example.bibliotecadigitale.Connection.*;
import com.example.bibliotecadigitale.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AcquistoController implements Initializable {

    @FXML
    private ImageView imageLibriSfondo;
    @FXML
    public TableView<Acquisto> tableView;
    @FXML
    TableColumn<Acquisto, Integer> codaColumn;
    @FXML
    TableColumn<Acquisto, String> nomeColumn;
    @FXML
    TableColumn<Acquisto, String> urlColumn;
    @FXML
    TableColumn<Acquisto, String> indirizzoColumn;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo le colonne della tabella libro
        codaColumn.setCellValueFactory(new PropertyValueFactory<>("coda"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        indirizzoColumn.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
    }

    public void showInfoAcquisto(String ISBN) {
        DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();
        ArrayList<DisponibileL> disponibileLarray = new ArrayList<>();
        ArrayList<ArrayList<String>> disponibileLarrayString = new ArrayList<>();

        try {
            disponibileLarrayString = disponibileLDAO.getAcquisti(ISBN);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ArrayList<String> strings : disponibileLarrayString) {
            DisponibileL disponibileL = new DisponibileL(strings);
            disponibileLarray.add(disponibileL);
        }
        while (!disponibileLarray.isEmpty()) {
            AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
            Acquisto acquisto;
            try {
                acquisto = new Acquisto(acquistoDAO.get(disponibileLarray.get(0).getCoda()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tableView.getItems().add(acquisto);
            disponibileLarray.remove(0);
        }
    }

    public void showInfoArticolo(String doi) {
        DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
        ArrayList<DisponibileA> disponibileAarray = new ArrayList<>();
        ArrayList<ArrayList<String>> disponibileAarrayString = new ArrayList<>();

        try {
            disponibileAarrayString = disponibileADAO.getAcquisti(doi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ArrayList<String> strings : disponibileAarrayString) {
            DisponibileA disponibileA = new DisponibileA(strings);
            disponibileAarray.add(disponibileA);
        }
        while (!disponibileAarray.isEmpty()) {
            AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
            Acquisto acquisto;
            try {
                acquisto = new Acquisto(acquistoDAO.get(disponibileAarray.get(0).getCoda()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tableView.getItems().add(acquisto);
            disponibileAarray.remove(0);
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) tableView.getScene().getWindow();
        event.consume();
        stage.close();
    }
}
