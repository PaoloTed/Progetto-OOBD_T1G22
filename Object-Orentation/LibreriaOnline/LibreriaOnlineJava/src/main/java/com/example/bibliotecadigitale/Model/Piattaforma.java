package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Piattaforma {
    private String responsabile;

    private ArrayList<ArticoloScientifico> articoli;

    public ArrayList<ArticoloScientifico> getArticoli() {
        return articoli;
    }

    public void setArticoli(ArrayList<ArticoloScientifico> articoli) {
        this.articoli = articoli;
    }
    public String getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(String responsabile) {
        this.responsabile = responsabile;
    }

}
