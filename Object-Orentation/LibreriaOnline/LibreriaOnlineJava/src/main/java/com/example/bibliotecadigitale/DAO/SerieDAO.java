package com.example.bibliotecadigitale.DAO;
import com.example.bibliotecadigitale.Model.Serie;
public interface SerieDAO extends DAO<Serie> {
    Serie get(int cods);
}
