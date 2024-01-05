package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.DisponibileS;

import java.sql.SQLException;

public interface DisponibileSDAO extends DAO<DisponibileS>{
    DisponibileS get(int coda, int cods) throws SQLException;
}
