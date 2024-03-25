package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;
import java.util.Objects;

public class Serie implements ArrayListObject {

    private int codS;
    private String nome;
    private int numLibri;

    private String completata;

    private ArrayList<Libro> libri;

    private ArrayList<Utente> utenti;

    private ArrayList<DisponibileS> disponibili;

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

    public String getCompletata() {
        return completata;

    }

    public void setCompletata(String completata) {
        this.completata = completata;
    }

    public ArrayList<Libro> getLibri() {
        return libri;
    }

    public void setLibri(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public ArrayList<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(ArrayList<Utente> utenti) {
        this.utenti = utenti;
    }

    public ArrayList<DisponibileS> getDisponibili() {
        return disponibili;
    }

    public void setDisponibili(ArrayList<DisponibileS> disponibili) {
        this.disponibili = disponibili;
    }

    public Serie() {
    }

    @Override
    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codS));
        arrayList.add(nome);
        arrayList.add(String.valueOf(numLibri));
        arrayList.add(completata);
        return arrayList;
    }

    public Serie(ArrayList<String> arrayList) {
        setCods(Integer.parseInt(arrayList.get(0)));
        setNome(arrayList.get(1));
        if (arrayList.get(2) == null || arrayList.get(2).isEmpty()) {
            setNumlibri(0);
        } else {
            setNumlibri(Integer.parseInt(arrayList.get(2)));
        }
        setCompletata(arrayList.get(3));
    }
}
