package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.LibroDAO;
import com.example.bibliotecadigitale.Model.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    public ArrayList<String> search(String tipoRicerca, String parolaChiave) {
        ArrayList<String> isbn = new ArrayList<>();
        ResultSet rs = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM libro WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
            rs = connessione.executeSearch(query);
            while (rs.next()) {
               isbn.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isbn;
    }

    public  Libro findLibroFromCodev2(String s)
    {
        Libro libroFinded = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM libro WHERE isbn = '" + s + "';";
            ResultSet rs = connessione.executeSearch(query);
            libroFinded = new Libro();
            while (rs.next()) {
                libroFinded.setISBN(rs.getString(1));
                libroFinded.setTitolo(rs.getString(2));
                libroFinded.setGenere(rs.getString(3));
                libroFinded.setAutore(rs.getString(10));
                libroFinded.setEditore(rs.getString(9));
            }
            rs.close();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return libroFinded;
    }

    @Override
    public Libro get() throws SQLException {
        return null;
    }

    @Override
    public List<Libro> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Libro libro) throws SQLException {

    }

    @Override
    public void update(Libro libro) throws SQLException {

    }

    @Override
    public void delete(Libro libro) throws SQLException {

    }
}