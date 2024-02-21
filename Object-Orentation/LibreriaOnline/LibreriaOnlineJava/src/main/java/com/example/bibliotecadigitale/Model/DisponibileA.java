package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class DisponibileA {
    private int codA;
    private String doi;

    public int getCoda() {
        return codA;
    }

    public void setCoda(int codA) {
        this.codA = codA;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public DisponibileA(){}

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codA));
        arrayList.add(doi);
        return arrayList;
    }
    public DisponibileA (ArrayList<String> arrayList) {
        setCoda(Integer.parseInt(arrayList.get(0)));
        setDoi(arrayList.get(1));
    }
}
