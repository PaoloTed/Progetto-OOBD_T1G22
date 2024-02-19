package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.DisponibileS;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DisponibileSDAO extends DAO{
    ArrayList<String> get(int coda, int cods) throws SQLException;
}
