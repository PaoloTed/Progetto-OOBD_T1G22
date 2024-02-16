package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class DisponibileS {
    private int codA;
    private int codS;

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

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codA));
        arrayList.add(String.valueOf(codS));
        return arrayList;
    }

    public static DisponibileS arrayListToObj(ArrayList<String> arrayList) {
        DisponibileS disponibileS = new DisponibileS();
        disponibileS.setCoda(Integer.parseInt(arrayList.get(0)));
        disponibileS.setCods(Integer.parseInt(arrayList.get(1)));
        return disponibileS;
    }
}
