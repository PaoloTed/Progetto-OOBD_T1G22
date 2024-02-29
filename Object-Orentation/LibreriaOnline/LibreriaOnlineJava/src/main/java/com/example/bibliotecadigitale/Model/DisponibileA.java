package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class DisponibileA {
    private int coda;
    private String doi;

    private ArticoloScientifico articolo;

    private Acquisto acquisto;

    public int getCoda() {
        return coda;
    }

    public void setCoda(int codA) {
        this.coda = codA;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public ArticoloScientifico getArticolo() {
        return articolo;
    }

    public void setArticolo(ArticoloScientifico articolo) {
        this.articolo = articolo;
    }

    public Acquisto getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(Acquisto acquisto) {
        this.acquisto = acquisto;
    }

    public DisponibileA(){}

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(coda));
        arrayList.add(doi);
        return arrayList;
    }
    public DisponibileA (ArrayList<String> arrayList) {
        setCoda(Integer.parseInt(arrayList.get(0)));
        setDoi(arrayList.get(1));
    }
}
