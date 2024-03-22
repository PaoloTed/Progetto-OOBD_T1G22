package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Presentazione implements ArrayListObject{
    private int codP;
    private String nome;
    private String indirizzo;
    private String data;
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

    public String getData() {
        return data;
    }

    public void setData(String dataPresentazione) {
        this.data = dataPresentazione;
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

    @Override
    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codP));
        arrayList.add(nome);
        arrayList.add(indirizzo);
        arrayList.add(data);
        arrayList.add(tipo);
        return arrayList;
    }

    public Presentazione (ArrayList<String> arrayList) {
        setCodp(Integer.parseInt(arrayList.get(0)));
        setNome(arrayList.get(1));
        setIndirizzo(arrayList.get(2));
        setData(arrayList.get(3));
        setTipo(arrayList.get(4));
    }
}
