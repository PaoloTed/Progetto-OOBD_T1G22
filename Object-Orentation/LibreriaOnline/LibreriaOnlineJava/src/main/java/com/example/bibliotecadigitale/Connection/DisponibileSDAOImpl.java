package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileSDAO;
import com.example.bibliotecadigitale.Model.DisponibileS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileSDAOImpl implements DisponibileSDAO {
    @Override
    public DisponibileS get(String cod) throws SQLException {
        DisponibileS disponibileS;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM disponibile_s WHERE cod = '" + cod + "';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileS = new DisponibileS();
            while (rs.next()) {
                disponibileS.setCodA(rs.getString(1));
                disponibileS.setCodS(rs.getString(2));
                rs.close();
            }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
            return disponibileS;
        }

        public ArrayList<DisponibileS> getRicerca (String tipoRicerca, String parolaChiave){
            ArrayList<DisponibileS> disponibileSFinded = new ArrayList<>();
            try {
                Connessione connessione = new Connessione();
                String query = "SELECT cod FROM disponibile_s WHERE LOWER(" + tipoRicerca + ") LIKE LOWER('%" + parolaChiave + "%');";
                ResultSet rs = connessione.executeSearch(query);
                DisponibileS disponibileS;
                while (rs.next()) {
                    disponibileS = get(rs.getString(1));
                    disponibileSFinded.add(disponibileS);
                }
                rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return disponibileSFinded;
        }

        @Override
        public List<DisponibileS> getAll () throws SQLException {
            return null;
        }

        @Override
        public void save (DisponibileS disponibileS) throws SQLException {

        }

        @Override
        public void update (DisponibileS disponibileS) throws SQLException {

        }

        @Override
        public void delete (DisponibileS disponibileS) throws SQLException {

        }
    }
