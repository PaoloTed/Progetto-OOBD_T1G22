package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class DisponibileS {
    private int codA;

    private int codS;

    private Acquisto acquisto;

    private Serie serie;

    public int getCoda() {
        return codA;
    }

    public void setCoda(int codA) {
        this.codA = codA;
    }

    public int getCods() {
        return codS;
    }

    public void setCods(int codS) {
        this.codS = codS;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Acquisto getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(Acquisto acquisto) {
        this.acquisto = acquisto;
    }

    public DisponibileS(){}

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codA));
        arrayList.add(String.valueOf(codS));
        return arrayList;
    }

    public DisponibileS (ArrayList<String> arrayList) {
        setCoda(Integer.parseInt(arrayList.get(0)));
        setCods(Integer.parseInt(arrayList.get(1)));
    }
}
