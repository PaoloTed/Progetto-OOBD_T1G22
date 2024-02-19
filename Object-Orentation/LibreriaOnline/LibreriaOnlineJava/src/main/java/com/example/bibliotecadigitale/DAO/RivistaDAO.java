package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Rivista;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RivistaDAO extends DAO {
    ArrayList<String> get(String nome, String data) throws SQLException;
}
