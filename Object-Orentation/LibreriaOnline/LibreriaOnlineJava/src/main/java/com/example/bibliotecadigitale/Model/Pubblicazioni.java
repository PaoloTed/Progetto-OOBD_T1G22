package com.example.bibliotecadigitale.Model;

public class Pubblicazioni
{
    private String titolo;
    private String genere;
    private int numPagine;
    private String dataUscita;
    private String descrizione;
    private String fruizione;
    private String editore;
    private String autore;
    private String lingua;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getNumpagine() {
        return numPagine;
    }

    public void setNumpagine(int numPagine) {
        this.numPagine = numPagine;
    }

    public String getDatauscita() {
        return dataUscita;
    }

    public void setDatauscita(String dataUscita) {
        this.dataUscita = dataUscita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFruizione() {
        return fruizione;
    }

    public void setFruizione(String fruizione) {
        this.fruizione = fruizione;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

}
