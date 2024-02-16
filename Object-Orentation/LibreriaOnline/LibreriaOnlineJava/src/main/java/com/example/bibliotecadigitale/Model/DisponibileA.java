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
    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codA));
        arrayList.add(doi);
        return arrayList;
    }
    public static DisponibileA arrayListToObj(ArrayList<String> arrayList) {
        DisponibileA disponibileA = new DisponibileA();
        disponibileA.setCoda(Integer.parseInt(arrayList.get(0)));
        disponibileA.setDoi(arrayList.get(1));
        return disponibileA;
    }
}
