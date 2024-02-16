package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Serie {

    private int codS;
    private String nome;
    private int numLibri;
    private boolean completata;

    public int getCods() {
        return codS;
    }

    public void setCods(int codS) {
        this.codS = codS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumlibri() {
        return numLibri;
    }

    public void setNumlibri(int numLibri) {
        this.numLibri = numLibri;
    }

    public boolean getCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codS));
        arrayList.add(nome);
        arrayList.add(String.valueOf(numLibri));
        arrayList.add(String.valueOf(completata));
        return arrayList;
    }

    public static Serie arrayListToObj(ArrayList<String> arrayList) {
        Serie serie = new Serie();
        serie.setCods(Integer.parseInt(arrayList.get(0)));
        serie.setNome(arrayList.get(1));
        serie.setNumlibri(Integer.parseInt(arrayList.get(2)));
        serie.setCompletata(Boolean.parseBoolean(arrayList.get(3)));
        return serie;
    }
}
