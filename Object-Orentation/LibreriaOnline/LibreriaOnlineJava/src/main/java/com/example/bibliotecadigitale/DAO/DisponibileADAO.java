package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.DisponibileA;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DisponibileADAO extends DAO<DisponibileA>{
    DisponibileA get(int coda,String doi) throws SQLException;
}
