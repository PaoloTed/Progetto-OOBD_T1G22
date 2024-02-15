package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.ConferenzaDAO;
import com.example.bibliotecadigitale.Model.Conferenza;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConferenzaDAOImpl implements ConferenzaDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public Conferenza get(int codC) {
        Conferenza conferenza = null;
        try {
            String query = "SELECT * FROM conferenza WHERE codC = '" + codC + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                conferenza = new Conferenza();
                conferenza.setCodc(Integer.parseInt(rs.getString("codC")));
                conferenza.setNome(rs.getString("nome"));
                conferenza.setDatai(rs.getString("dataI"));
                conferenza.setDataf(rs.getString("dataF"));
                conferenza.setIndirizzo(rs.getString("indirizzo"));
                conferenza.setResponsabile(rs.getString("responsabile"));
                conferenza.setStruttura(rs.getString("struttura"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conferenza;
    }

    @Override
    public ArrayList<Conferenza> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<Conferenza> conferenzaFinded = new ArrayList<>();
        String query;

        if (tipoRicerca.equalsIgnoreCase("codC")) {
            query = "SELECT codC FROM conferenza WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT codC FROM conferenza WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        Conferenza conferenza;
        while (rs.next()) {
            conferenza = get(rs.getInt(1));
            conferenzaFinded.add(conferenza);
        }
        rs.close();
        return conferenzaFinded;
    }

    @Override
    public List<Conferenza> getAll() throws SQLException {
        ArrayList<Conferenza> conferenzaFinded = new ArrayList<>();
        try {
            String query = "SELECT codC FROM conferenza;";
            ResultSet rs = connessione.executeSearch(query);
            Conferenza conferenza;
            while (rs.next()) {
                conferenza = get(rs.getInt(1));
                conferenzaFinded.add(conferenza);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conferenzaFinded;
    }

    @Override
    public void insert(Conferenza conferenza) throws SQLException {
        try {
            int codC = conferenza.getCodc();
            String nome = conferenza.getNome();
            String dataI = conferenza.getDatai();
            String dataF = conferenza.getDataf();
            String indirizzo = conferenza.getIndirizzo();
            String responsabile = conferenza.getResponsabile();
            String struttura = conferenza.getStruttura();
            String query = "INSERT INTO conferenza VALUES (" + codC + ", '" + nome + "', '" + dataI + "', '" + dataF + "', '" + indirizzo + "', '" + responsabile + "', '" + struttura + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Conferenza conferenza) throws SQLException {
        try {
            int codC = conferenza.getCodc();
            String nome = conferenza.getNome();
            String dataI = conferenza.getDatai();
            String dataF = conferenza.getDataf();
            String indirizzo = conferenza.getIndirizzo();
            String responsabile = conferenza.getResponsabile();
            String struttura = conferenza.getStruttura();
            String query = "UPDATE conferenza SET nome = '" + nome + "', dataI = '" + dataI + "', dataF = '" + dataF + "', indirizzo = '" + indirizzo + "', responsabile = '" + responsabile + "', struttura = '" + struttura + "' WHERE codC = " + codC + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Conferenza conferenza) throws SQLException {
        try {
            int codC = conferenza.getCodc();
            String query = "DELETE FROM conferenza WHERE codC = " + codC + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
