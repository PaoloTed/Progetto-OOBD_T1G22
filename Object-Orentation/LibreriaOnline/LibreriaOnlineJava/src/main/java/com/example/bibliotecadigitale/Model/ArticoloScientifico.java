package com.example.bibliotecadigitale.Model;

public class ArticoloScientifico extends Pubblicazioni {

    private String doi;
    private Integer conferenza;
    private String nomer;
    private String datar;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Integer getConferenza() {
        return conferenza;
    }

    public void setConferenza(Integer conferenza) {
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
