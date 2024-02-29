package com.example.bibliotecadigitale.Model;
import java.util.ArrayList;

public class Serie {

    private int codS;
    private String nome;
    private int numLibri;

    private boolean completata;

    private ArrayList<Libro> libri;

    private ArrayList<Utente> utenti;

    private ArrayList<DisponibileS> disponibili;

    public int getCods() {return codS;}
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
    public Serie(){}

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codS));
        arrayList.add(nome);
        arrayList.add(String.valueOf(numLibri));
        arrayList.add(String.valueOf(completata));
        return arrayList;
    }

    public Serie (ArrayList<String> arrayList) {
        setCods(Integer.parseInt(arrayList.get(0)));
        setNome(arrayList.get(1));
        if (arrayList.get(2) == null) {
            setNumlibri(0);
        } else {
            setNumlibri(Integer.parseInt(arrayList.get(2)));
        }
        if (arrayList.get(3) == null) {
            setCompletata(false);
        } else {
            setCompletata(Boolean.parseBoolean(arrayList.get(3)));
        }
    }
}
