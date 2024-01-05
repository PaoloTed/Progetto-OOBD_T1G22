package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Acquisto;

import java.sql.SQLException;

public interface AcquistoDAO extends DAO<Acquisto> {
    Acquisto get(int coda) throws SQLException;
}
