package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Presentazione;

public interface PresentazioneDAO extends DAO<Presentazione> {
    Presentazione get(int codP);
}
