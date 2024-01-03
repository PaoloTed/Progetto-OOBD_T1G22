package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.LibroDAO;
import com.example.bibliotecadigitale.Model.Libro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    public ArrayList<Libro> getRicerca(String tipoRicerca, String parolaChiave) {
        ArrayList<Libro> libroFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT isbn FROM libro WHERE LOWER(" + tipoRicerca + ") LIKE LOWER('%" + parolaChiave + "%');";
            ResultSet rs = connessione.executeSearch(query);
            Libro libro;
            while (rs.next()) {
                libro = get(rs.getString(1));
                libroFinded.add(libro);
            }
            rs.close();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return libroFinded;
    }

    @Override
    public Libro get(String isbn) throws SQLException {
        Libro libro;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
            ResultSet rs = connessione.executeSearch(query);
            libro = new Libro();
            while (rs.next()) {
                libro.setISBN(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setGenere(rs.getString(3));
                libro.setNumPagine(rs.getInt(4));
                libro.setTipo(rs.getString(5));
                libro.setMateria(rs.getString(6));
                libro.setDescrizione(rs.getString(7));
                libro.setFruizione(rs.getString(8));
                libro.setEditore(rs.getString(9));
                libro.setAutore(rs.getString(10));
                libro.setDataUscita(rs.getString(11));
                libro.setLingua(rs.getString(12));
                libro.setSuccessivo(rs.getString(13));
                libro.setSerie(rs.getString(14));
                libro.setPresentazione(rs.getString(15));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }


    @Override
    public List<Libro> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Libro libro) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String isbn = libro.getISBN();
            String titolo = libro.getTitolo();
            String genere = libro.getGenere();
            int numeroPagine = libro.getNumPagine();
            String tipo = libro.getTipo();
            String materia = libro.getMateria();
            String descrizione = libro.getDescrizione();
            String fruizione = libro.getFruizione();
            String editore = libro.getEditore();
            String autore = libro.getAutore();
            String datauscita = libro.getDataUscita();
            String lingua = libro.getLingua();
            String successivo = libro.getSuccessivo();
            String presentazione = libro.getPresentazione();
            String query = "INSERT INTO libro VALUES ('" + isbn + "','" + titolo + "','" + genere + "','" + numeroPagine + "','" + tipo + "','" + materia + "','" + descrizione + "','" + fruizione + "','" + editore + "','" + autore + "','" + datauscita + "','" + lingua + "','" + successivo + "','" + presentazione + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Libro libro) throws SQLException {

    }

    @Override
    public void delete(Libro libro) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String isbn = libro.getISBN();
            String query = "DELETE FROM libro WHERE ISBN ='" + isbn + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
