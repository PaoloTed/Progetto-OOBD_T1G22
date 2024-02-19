package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Presentazione;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PresentazioneDAO extends DAO {
    ArrayList<String> get(int codP) throws SQLException;
}
