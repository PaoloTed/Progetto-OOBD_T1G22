package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Presentazione {
    private int codP;
    private String nome;
    private String indirizzo;
    private String dataPresentazione;
    private String tipo;

    private ArrayList<Libro> libri;

    public int getCodp() {
        return codP;
    }

    public void setCodp(int codP) {
        this.codP = codP;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDatapresentazione() {
        return dataPresentazione;
    }

    public void setDatapresentazione(String dataPresentazione) {
        this.dataPresentazione = dataPresentazione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Libro> getLibri() {
        return libri;
    }

    public void setLibri(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public Presentazione(){}

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codP));
        arrayList.add(nome);
        arrayList.add(indirizzo);
        arrayList.add(dataPresentazione);
        arrayList.add(tipo);
        return arrayList;
    }

    public Presentazione (ArrayList<String> arrayList) {
        setCodp(Integer.parseInt(arrayList.get(0)));
        setNome(arrayList.get(1));
        setIndirizzo(arrayList.get(2));
        setDatapresentazione(arrayList.get(3));
        setTipo(arrayList.get(4));
    }
}
