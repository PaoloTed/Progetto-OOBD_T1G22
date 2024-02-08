package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.RivistaDAO;
import com.example.bibliotecadigitale.Model.Conferenza;
import com.example.bibliotecadigitale.Model.Rivista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RivistaDAOImpl  implements RivistaDAO {
    private final Connessione connessione = new Connessione();

    public Rivista get(String nome, String data) {
        Rivista rivista;
        try {
            String query = "SELECT * FROM rivista WHERE nome = '" + nome + "' AND data = '" + data + "';";
            ResultSet rs = connessione.executeSearch(query);
            rivista = new Rivista();
            while (rs.next()) {
                rivista = new Rivista();
                rivista.setNome(rs.getString(1));
                rivista.setData(rs.getString(2));
                rivista.setResponsabile(rs.getString(3));
                rivista.setArgomento(rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rivista;
    }

    public ArrayList<Rivista> getRicerca(String tipoRicerca, String parolaChiave) {
        ArrayList<Rivista> rivistaFinded = new ArrayList<>();
        String query;
        try {
            if(tipoRicerca.equalsIgnoreCase("nome")||tipoRicerca.equalsIgnoreCase("data")){
                query = "SELECT nome, data FROM rivista WHERE " + tipoRicerca + " = " + parolaChiave + ";";
            }else{
                query = "SELECT nome, data FROM rivista WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
            }
            ResultSet rs = connessione.executeSearch(query);
            Rivista rivista;
            while (rs.next()) {
                rivista = get(rs.getString(1), rs.getString(2));
                rivistaFinded.add(rivista);
            }
            rs.close();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return rivistaFinded;
    }

    @Override
    public Rivista get(String cod) throws SQLException {
        return null;
    }

    @Override
    public List<Rivista> getAll() throws SQLException {
        ArrayList<Rivista> rivistaFinded = new ArrayList<>();
        try {
            String query = "SELECT nome, data FROM rivista;";
            ResultSet rs = connessione.executeSearch(query);
            Rivista rivista;
            while (rs.next()) {
                rivista = get(rs.getString(1),rs.getString(2));
                rivistaFinded.add(rivista);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rivistaFinded;
    }

    @Override
    public void insert(Rivista rivista) throws SQLException {
        try {
            String nome = rivista.getNome();
            String data = rivista.getData();
            String responsabile = rivista.getResponsabile();
            String argomento = rivista.getArgomento();
            String query = "INSERT INTO rivista VALUES ('" + nome + "', '" + data + "', '" + responsabile + "', '" + argomento + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Rivista rivista) throws SQLException {
        try {
            String nome = rivista.getNome();
            String data = rivista.getData();
            String responsabile = rivista.getResponsabile();
            String argomento = rivista.getArgomento();
            String query = "UPDATE rivista SET nome = '" + nome + "', data = '" + data + "', responsabile = '" + responsabile + "', argomento = '" + argomento + "' WHERE nome = '" + nome + "' AND data = '" + data + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Rivista rivista) throws SQLException {
        try{
            String nome = rivista.getNome();
            String data = rivista.getData();
            String query = "DELETE FROM rivista WHERE nome = '" + nome + "' AND data = '" + data + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
