package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.LibroDAO;
import com.example.bibliotecadigitale.Model.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public ArrayList<Libro> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<Libro> libroFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("numpagine") || tipoRicerca.equalsIgnoreCase("serie")) {
            query = "SELECT isbn FROM libro WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT isbn FROM libro WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        Libro libro;
        while (rs.next()) {
            libro = get(rs.getString(1));
            libroFinded.add(libro);
        }
        rs.close();
        return libroFinded;
    }

    public ArrayList<ArrayList<String>> getRicercaT(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> libroFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("numpagine") || tipoRicerca.equalsIgnoreCase("serie")) {
            query = "SELECT * FROM libro WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT * FROM libro WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> libro;
        while (rs.next()) {
            libro = getT(rs.getString(1));
            libroFinded.add(libro);
        }
        rs.close();
        return libroFinded;
    }

    @Override
    public Libro get(String isbn) throws SQLException {
        Libro libro;
        try {
            String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
            ResultSet rs = connessione.executeSearch(query);
            libro = new Libro();
            while (rs.next()) {
                libro.setIsbn(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setGenere(rs.getString(3));
                libro.setNumpagine(rs.getInt(4));
                libro.setTipo(rs.getString(5));
                libro.setMateria(rs.getString(6));
                libro.setDescrizione(rs.getString(7));
                libro.setFruizione(rs.getString(8));
                libro.setEditore(rs.getString(9));
                libro.setAutore(rs.getString(10));
                libro.setDatauscita(rs.getString(11));
                libro.setLingua(rs.getString(12));
                libro.setSuccessivo(rs.getString(13));
                libro.setSerie(rs.getObject(14, Integer.class));
                libro.setPresentazione(rs.getObject(15, Integer.class));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }

    public ArrayList<String> getT(String isbn){
        ArrayList<String> libro = new ArrayList<>();
        try {
            String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                libro.add(rs.getString(1));
                libro.add(rs.getString(2));
                libro.add(rs.getString(3));
                libro.add(rs.getString(4));
                libro.add(rs.getString(5));
                libro.add(rs.getString(6));
                libro.add(rs.getString(7));
                libro.add(rs.getString(8));
                libro.add(rs.getString(9));
                libro.add(rs.getString(10));
                libro.add(rs.getString(11));
                libro.add(rs.getString(12));
                libro.add(rs.getString(13));
                libro.add(rs.getString(14));
                libro.add(rs.getString(15));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }


    @Override
    public List<Libro> getAll() throws SQLException {
        ArrayList<Libro> libroFinded = new ArrayList<>();
        try {
            String query = "SELECT isbn FROM libro;";
            ResultSet rs = connessione.executeSearch(query);
            Libro libro;
            while (rs.next()) {
                libro = get(rs.getString(1));
                libroFinded.add(libro);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libroFinded;
    }

    public ArrayList<ArrayList<String>> getAllT(){
        ArrayList<ArrayList<String>> libroFinded = new ArrayList<>();
        try {
            String query = "SELECT * FROM libro;";
            ResultSet rs = connessione.executeSearch(query);
            ArrayList<String> libro = new ArrayList<>();
            while (rs.next()) {
                libro.add(rs.getString(1));
                libro.add(rs.getString(2));
                libro.add(rs.getString(3));
                libro.add(rs.getString(4));
                libro.add(rs.getString(5));
                libro.add(rs.getString(6));
                libro.add(rs.getString(7));
                libro.add(rs.getString(8));
                libro.add(rs.getString(9));
                libro.add(rs.getString(10));
                libro.add(rs.getString(11));
                libro.add(rs.getString(12));
                libro.add(rs.getString(13));
                libro.add(rs.getString(14));
                libro.add(rs.getString(15));
                libroFinded.add(libro);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libroFinded;
    }

    @Override
    public void insert(Libro libro) throws SQLException {
        try {
            String isbn = libro.getIsbn();
            String titolo = libro.getTitolo();
            String genere = libro.getGenere();
            int numeroPagine = libro.getNumpagine();
            String tipo = libro.getTipo();
            String materia = libro.getMateria();
            String descrizione = libro.getDescrizione();
            String fruizione = libro.getFruizione();
            String editore = libro.getEditore();
            String autore = libro.getAutore();
            String datauscita = libro.getDatauscita();
            String lingua = libro.getLingua();
            String successivo = libro.getSuccessivo();
            Integer presentazione = libro.getPresentazione();
            Integer serie = libro.getSerie();
            String query = "INSERT INTO libro VALUES ('" + isbn + "','" + titolo + "','" + genere + "'," + numeroPagine + ",'" + tipo + "','" + materia + "','" + descrizione + "','" + fruizione + "','" + editore + "','" + autore + "','" + datauscita + "','" + lingua + "','" + successivo + "'," + serie + " , " + presentazione + ");";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    public void insertT(ArrayList<String> libro) throws SQLException {
//        try {
//            String isbn = libro.get(0);
//            String titolo = libro.get(1);
//            String genere = libro.get(2);
//            String numeroPagine = String.valueOf(libro.get(3));
//            String tipo = libro.get(4);
//            String materia = libro.get(5);
//            String descrizione = libro.get(6);
//            String fruizione = libro.get(7);
//            String editore = libro.get(8);
//            String autore = libro.get(9);
//            String datauscita = libro.get(10);
//            String lingua = libro.get(11);
//            String successivo = libro.get(12);
//            String serie = Integer.parseInt(libro.get(13));
//            Integer presentazione = Integer.parseInt(libro.get(14));
//
//            String query = "INSERT INTO libro VALUES ('" + isbn + "','" + titolo + "','" + genere + "'," + numeroPagine + ",'" + tipo + "','" + materia + "','" + descrizione + "','" + fruizione + "','" + editore + "','" + autore + "','" + datauscita + "','" + lingua + "','" + successivo + "'," + serie + " , " + presentazione + ");";
//            connessione.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void update(Libro libro) throws SQLException {
        try {
            String descrizione = libro.getDescrizione().replace("'", "''");
            String successivo = "NULL";
            String serie = "NULL";
            String presentazione = "NULL";
            String materia = "NULL";
            if (libro.getSuccessivo() != null) {
                successivo = "'" + libro.getSuccessivo() + "'";
            }
            if (libro.getSerie() != null) {
                serie = libro.getSerie().toString();
            }
            if (libro.getPresentazione() != null) {
                presentazione = libro.getPresentazione().toString();
            }
            if (libro.getMateria() != null) {
                materia = "'" + libro.getMateria() + "'";
            }
            String query = "UPDATE libro SET titolo = '" + libro.getTitolo() + "', genere = '" + libro.getGenere() + "', numpagine = " + libro.getNumpagine() + " , tipo = '" + libro.getTipo() + "', materia = " + materia + ", descrizione = '" + descrizione + "', fruizione = '" + libro.getFruizione() + "', editore = '" + libro.getEditore() + "', autore = '" + libro.getAutore() + "', datauscita = '" + libro.getDatauscita() + "', lingua = '" + libro.getLingua() + "', successivo = " + successivo + ", serie = " + serie + ", presentazione = " + presentazione + " WHERE isbn = '" + libro.getIsbn() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Libro libro) throws SQLException {
        try {
            String isbn = libro.getIsbn();
            String query = "DELETE FROM libro WHERE ISBN ='" + isbn + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void deleteT()
}
