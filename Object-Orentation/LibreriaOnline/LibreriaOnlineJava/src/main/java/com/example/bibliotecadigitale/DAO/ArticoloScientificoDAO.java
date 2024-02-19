package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.ArticoloScientifico;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ArticoloScientificoDAO extends DAO{
    ArrayList<String> get(String doi) throws SQLException;
}
