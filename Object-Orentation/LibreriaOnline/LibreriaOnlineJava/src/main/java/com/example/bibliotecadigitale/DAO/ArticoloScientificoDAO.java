package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.ArticoloScientifico;

import java.sql.SQLException;

public interface ArticoloScientificoDAO extends DAO<ArticoloScientifico> {
    ArticoloScientifico get(String doi) throws SQLException;
}
