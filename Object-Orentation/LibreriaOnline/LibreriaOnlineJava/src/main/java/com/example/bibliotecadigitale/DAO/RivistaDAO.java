package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Rivista;

public interface RivistaDAO extends DAO<Rivista> {
    Rivista get(String nome, String data);
}
