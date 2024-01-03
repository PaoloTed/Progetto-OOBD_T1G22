package com.example.bibliotecadigitale.Model;

public class ArticoloScientifico extends Pubblicazioni {

    private String doi;
    private int conferenza;
    private String nomer;
    private String datar;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public int getConferenza() {
        return conferenza;
    }

    public void setConferenza(int conferenza) {
        this.conferenza = conferenza;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getDatar() {
        return datar;
    }

    public void setDatar(String datar) {
        this.datar = datar;
    }
}
