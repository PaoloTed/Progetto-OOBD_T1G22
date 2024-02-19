package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DisponibileLDAO extends DAO{
    ArrayList<String> get(int coda, String isbn) throws SQLException;
}
