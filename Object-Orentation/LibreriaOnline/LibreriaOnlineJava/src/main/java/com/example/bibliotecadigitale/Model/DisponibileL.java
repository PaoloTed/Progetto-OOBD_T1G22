package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class DisponibileL implements ArrayListObject{
    private int codA;
    private String isbn;

    private Acquisto acquisto;

    private Libro libro;

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

    public Acquisto getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(Acquisto acquisto) {
        this.acquisto = acquisto;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public DisponibileL(){}

    @Override
    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codA));
        arrayList.add(isbn);
        return arrayList;
    }

     public DisponibileL (ArrayList<String> arrayList) {
        codA = Integer.parseInt(arrayList.get(0));
        isbn =  arrayList.get(1);
    }
}
