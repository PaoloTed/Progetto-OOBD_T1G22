package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Acquisto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AcquistoDAO extends DAO {
    ArrayList<String> get(int coda) throws SQLException;
}
