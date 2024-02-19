package com.example.bibliotecadigitale.DAO;
import com.example.bibliotecadigitale.Model.Serie;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SerieDAO extends DAO {
    ArrayList<String> get(int cods) throws SQLException;
}
