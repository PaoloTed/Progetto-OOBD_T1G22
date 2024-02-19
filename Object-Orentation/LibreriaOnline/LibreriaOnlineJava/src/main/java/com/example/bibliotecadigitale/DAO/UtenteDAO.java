package com.example.bibliotecadigitale.DAO;
import com.example.bibliotecadigitale.Model.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UtenteDAO extends DAO {
    ArrayList<String> get(String emailUser) throws SQLException;
}
