package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.LibroDAO;
import com.example.bibliotecadigitale.Model.Libro;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    private ArrayList<String> rsToArrayList(ResultSet rs, int numCampi) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 1; i <= numCampi; i++) {
            array.add(rs.getString(i));
        }
        return array;
    }

//    @Override
//    public ArrayList<Libro> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
//        ArrayList<Libro> libroFinded = new ArrayList<>();
//        String query;
//        if (tipoRicerca.equalsIgnoreCase("numpagine") || tipoRicerca.equalsIgnoreCase("serie")) {
//            query = "SELECT isbn FROM libro WHERE " + tipoRicerca + " = " + parolaChiave + ";";
//        } else {
//            query = "SELECT isbn FROM libro WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
//        }
//        ResultSet rs = connessione.executeSearch(query);
//        Libro libro;
//        while (rs.next()) {
//            libro = get(rs.getString(1));
//            libroFinded.add(libro);
//        }
//        rs.close();
//        return libroFinded;
//    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
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
            libro = rsToArrayList(rs, 15);
            libroFinded.add(libro);
        }
        rs.close();
        return libroFinded;
    }

    //    @Override
//    public Libro get(String isbn) throws SQLException {
//        Libro libro;
//        try {
//            String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
//            ResultSet rs = connessione.executeSearch(query);
//            libro = new Libro();
//            while (rs.next()) {
//                libro.setIsbn(rs.getString(1));
//                libro.setTitolo(rs.getString(2));
//                libro.setGenere(rs.getString(3));
//                libro.setNumpagine(rs.getInt(4));
//                libro.setTipo(rs.getString(5));
//                libro.setMateria(rs.getString(6));
//                libro.setDescrizione(rs.getString(7));
//                libro.setFruizione(rs.getString(8));
//                libro.setEditore(rs.getString(9));
//                libro.setAutore(rs.getString(10));
//                libro.setDatauscita(rs.getString(11));
//                libro.setLingua(rs.getString(12));
//                libro.setSuccessivo(rs.getString(13));
//                libro.setSerie(rs.getObject(14, Integer.class));
//                libro.setPresentazione(rs.getObject(15, Integer.class));
//            }
//            rs.close();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return libro;
//    }
    @Override
    public ArrayList<String> get(String isbn) throws SQLException {
        ArrayList<String> libro = new ArrayList<>();
        String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            libro = rsToArrayList(rs, 15);
        }
        rs.close();
        return libro;
    }


//    @Override
//    public List<Libro> getAll() throws SQLException {
//        ArrayList<Libro> libroFinded = new ArrayList<>();
//        try {
//            String query = "SELECT isbn FROM libro;";
//            ResultSet rs = connessione.executeSearch(query);
//            Libro libro;
//            while (rs.next()) {
//                libro = get(rs.getString(1));
//                libroFinded.add(libro);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return libroFinded;
//    }

    @Override
    public ArrayList<ArrayList<String>> getAll() {
        ArrayList<ArrayList<String>> libroFinded = new ArrayList<>();
        try {
            String query = "SELECT * FROM libro;";
            ResultSet rs = connessione.executeSearch(query);
            ArrayList<String> libro;
            while (rs.next()) {
                libro = rsToArrayList(rs, 15);
                libroFinded.add(libro);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libroFinded;
    }

//    @Override
//    public void insert(Libro libro) throws SQLException {
//        try {
//            String isbn = libro.getIsbn();
//            String titolo = libro.getTitolo();
//            String genere = libro.getGenere();
//            int numeroPagine = libro.getNumpagine();
//            String tipo = libro.getTipo();
//            String materia = libro.getMateria();
//            String descrizione = libro.getDescrizione();
//            String fruizione = libro.getFruizione();
//            String editore = libro.getEditore();
//            String autore = libro.getAutore();
//            String datauscita = libro.getDatauscita();
//            String lingua = libro.getLingua();
//            String successivo = libro.getSuccessivo();
//            Integer presentazione = libro.getPresentazione();
//            Integer serie = libro.getSerie();
//            String query = "INSERT INTO libro VALUES ('" + isbn + "','" + titolo + "','" + genere + "'," + numeroPagine + ",'" + tipo + "','" + materia + "','" + descrizione + "','" + fruizione + "','" + editore + "','" + autore + "','" + datauscita + "','" + lingua + "','" + successivo + "'," + serie + " , " + presentazione + ");";
//            connessione.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void insert(ArrayList<String> libro) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO libro VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        //Inserimento valori non nullable
        preparedStatement.setString(1, libro.get(0));//isbn
        preparedStatement.setString(2, libro.get(1));//titolo
        preparedStatement.setInt(4, Integer.parseInt(libro.get(3)));//numpagine
        preparedStatement.setString(5, libro.get(4));//tipo
        preparedStatement.setString(7, libro.get(6).replace("'", "''"));//descrizione
        preparedStatement.setString(8, libro.get(7));//fruizione
        preparedStatement.setString(9, libro.get(8));//editore
        preparedStatement.setString(10, libro.get(9));//autore
        preparedStatement.setString(12, libro.get(11));//lingua

        //Inserimento valori nullable
        if (libro.get(2) == null) {//genere
            preparedStatement.setNull(3, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setString(3, libro.get(2));
        }
        if (libro.get(5) == null) {//materia
            preparedStatement.setNull(6, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setString(6, libro.get(5));
        }
        if (libro.get(10) == null) {//datauscita
            preparedStatement.setNull(11, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setString(11, libro.get(10));
        }
        if (libro.get(12) == null) {//successivo
            preparedStatement.setNull(13, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setString(13, libro.get(12));
        }
        if (libro.get(13).equals("null")) {//serie
            preparedStatement.setNull(14, java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(14, Integer.parseInt(libro.get(13)));
        }
        if (libro.get(14).equals("null")) {//presentazione
            preparedStatement.setNull(15, java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(15, Integer.parseInt(libro.get(14)));
        }
        preparedStatement.executeUpdate();
    }

//    @Override
//    public void update(Libro libro) throws SQLException {
//        try {
//            String descrizione = libro.getDescrizione().replace("'", "''");
//            String successivo = "NULL";
//            String serie = "NULL";
//            String presentazione = "NULL";
//            String materia = "NULL";
//            if (libro.getSuccessivo() != null) {
//                successivo = "'" + libro.getSuccessivo() + "'";
//            }
//            if (libro.getSerie() != null) {
//                serie = libro.getSerie().toString();
//            }
//            if (libro.getPresentazione() != null) {
//                presentazione = libro.getPresentazione().toString();
//            }
//            if (libro.getMateria() != null) {
//                materia = "'" + libro.getMateria() + "'";
//            }
//            String query = "UPDATE libro SET titolo = '" + libro.getTitolo() + "', genere = '" + libro.getGenere() + "', numpagine = " + libro.getNumpagine() + " , tipo = '" + libro.getTipo() + "', materia = " + materia + ", descrizione = '" + descrizione + "', fruizione = '" + libro.getFruizione() + "', editore = '" + libro.getEditore() + "', autore = '" + libro.getAutore() + "', datauscita = '" + libro.getDatauscita() + "', lingua = '" + libro.getLingua() + "', successivo = " + successivo + ", serie = " + serie + ", presentazione = " + presentazione + " WHERE isbn = '" + libro.getIsbn() + "';";
//            connessione.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    //    @Override
//    public void delete(Libro libro) throws SQLException {
//        try {
//            String isbn = libro.getIsbn();
//            String query = "DELETE FROM libro WHERE ISBN ='" + isbn + "';";
//            connessione.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @FXML
    public void delete(ArrayList<String> libro) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM libro WHERE ISBN = ?;");
        preparedStatement.setString(1, libro.get(0));
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(ArrayList<String> libro) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE libro SET titolo = ?, genere = ?, numpagine = ?, tipo = ?, materia = ?, descrizione = ?, fruizione = ?, editore = ?, autore = ?, datauscita = ?, lingua = ?, successivo = ?, serie = ?, presentazione = ? WHERE isbn = ?;");
        //Inserimento valori non nullable
        preparedStatement.setString(15, libro.get(0));//isbn insert
        preparedStatement.setString(1, libro.get(1));//titolo
        preparedStatement.setString(2, libro.get(2));//genere
        preparedStatement.setInt(3, Integer.parseInt(libro.get(3)));//numpagine
        preparedStatement.setString(4, libro.get(4));//tipo
        preparedStatement.setString(6, libro.get(6).replace("'", "''"));//descrizione
        preparedStatement.setString(7, libro.get(7));//fruizione
        preparedStatement.setString(8, libro.get(8));//editore
        preparedStatement.setString(9, libro.get(9));//autore
        preparedStatement.setString(11, libro.get(11));//lingua

        //Inserimento valori nullable
        if (libro.get(5) == null) {//materia
            preparedStatement.setNull(5, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setString(5, libro.get(5));
        }
        if (libro.get(10) == null) {//datauscita
            preparedStatement.setNull(10, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setDate(10, Date.valueOf(libro.get(10)));
        }
        if (libro.get(12) == null) {//successivo
            preparedStatement.setNull(12, java.sql.Types.VARCHAR);
        } else {
            preparedStatement.setString(12, libro.get(12));
        }
        if (libro.get(13).equals("null")) {//serie
            preparedStatement.setNull(13, java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(13, Integer.parseInt(libro.get(13)));
        }
        if (libro.get(14).equals("null")) {//presentazione
            preparedStatement.setNull(14, java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(14, Integer.parseInt(libro.get(14)));
        }
        preparedStatement.executeUpdate();
    }
}
