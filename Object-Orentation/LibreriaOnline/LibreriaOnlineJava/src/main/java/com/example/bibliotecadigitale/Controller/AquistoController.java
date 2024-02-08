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
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AquistoController implements Initializable {

    @FXML
    private ImageView imageLibriSfondo;
    @FXML
    public TableView<Acquisto> tableView;
    @FXML
    TableColumn<Acquisto, Integer> codAColumn;
    @FXML
    TableColumn<Acquisto, String> nomeColumn;
    @FXML
    TableColumn<Acquisto, String> urlColumn;
    @FXML
    TableColumn<Acquisto, String> IndirizzoColumn;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo le colonne della tabella libro
        codAColumn.setCellValueFactory(new PropertyValueFactory<Acquisto, Integer>("codA"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("nome"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("url"));
        IndirizzoColumn.setCellValueFactory(new PropertyValueFactory<Acquisto, String>("indirizzo"));
        imageLibriSfondo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/libri800x900.png"))));
    }

    public void showInfoAquisto(String ISBN) {
        DisponibileLDAOImpl disponibileLDAO = new DisponibileLDAOImpl();

        ArrayList<DisponibileL> DisponibileLarrey;
        try {
            DisponibileLarrey = disponibileLDAO.getAcquisti(ISBN);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (!DisponibileLarrey.isEmpty()) {
            AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
            Acquisto acquisto;
            try {
                acquisto = acquistoDAO.get(DisponibileLarrey.get(0).getCodA());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tableView.getItems().add(acquisto);
            DisponibileLarrey.remove(0);

        }
    }

    //non testao ops
    public void showInfoArtcolo(String doi) {
        DisponibileADAOImpl disponibileADAO = new DisponibileADAOImpl();
        ArrayList<DisponibileA> DisponibileLarrey;
        try {
            DisponibileLarrey = disponibileADAO.getAquisti(doi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (!DisponibileLarrey.isEmpty()) {
            AcquistoDAOImpl acquistoDAO = new AcquistoDAOImpl();
            Acquisto acquisto;
            try {
                acquisto = acquistoDAO.get(DisponibileLarrey.get(0).getCoda());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tableView.getItems().add(acquisto);
            DisponibileLarrey.remove(0);
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
    }
}
