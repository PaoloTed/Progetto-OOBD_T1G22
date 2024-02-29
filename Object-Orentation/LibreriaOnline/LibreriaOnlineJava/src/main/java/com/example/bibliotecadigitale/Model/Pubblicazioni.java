package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Pubblicazioni
{
    protected String titolo;
    protected String genere;
    protected int numPagine;
    protected String dataUscita;
    protected String descrizione;
    protected String fruizione;
    protected String editore;
    protected String autore;
    protected String lingua;

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

//    public ArrayList<String> objToArrayList() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add(titolo);
//        arrayList.add(genere);
//        arrayList.add(String.valueOf(numPagine));
//        arrayList.add(dataUscita);
//        arrayList.add(descrizione);
//        arrayList.add(fruizione);
//        arrayList.add(editore);
//        arrayList.add(autore);
//        arrayList.add(lingua);
//        return arrayList;
//    }
//
//    public static Pubblicazioni arrayListToObj(ArrayList<String> arrayList) {
//        Pubblicazioni pubblicazioni = new Pubblicazioni();
//        pubblicazioni.setTitolo(arrayList.get(0));
//        pubblicazioni.setGenere(arrayList.get(1));
//        pubblicazioni.setNumpagine(Integer.parseInt(arrayList.get(2)));
//        pubblicazioni.setDatauscita(arrayList.get(3));
//        pubblicazioni.setDescrizione(arrayList.get(4));
//        pubblicazioni.setFruizione(arrayList.get(5));
//        pubblicazioni.setEditore(arrayList.get(6));
//        pubblicazioni.setAutore(arrayList.get(7));
//        pubblicazioni.setLingua(arrayList.get(8));
//        return pubblicazioni;
//    }

}
