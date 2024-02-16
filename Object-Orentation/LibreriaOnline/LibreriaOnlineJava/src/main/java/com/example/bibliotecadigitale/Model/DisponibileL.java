package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class DisponibileL {
    private int codA;
    private String isbn;

    public int getCoda() {
        return codA;
    }

    public void setCoda(int coda) {
        this.codA = coda;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codA));
        arrayList.add(isbn);
        return arrayList;
    }

    public static DisponibileL arrayListToObj(ArrayList<String> arrayList) {
        DisponibileL disponibileL = new DisponibileL();
        disponibileL.setCoda(Integer.parseInt(arrayList.get(0)));
        disponibileL.setIsbn(arrayList.get(1));
        return disponibileL;
    }
}
