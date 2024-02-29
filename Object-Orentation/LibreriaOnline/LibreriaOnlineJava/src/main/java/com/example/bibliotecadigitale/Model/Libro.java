package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Libro extends Pubblicazioni implements ArrayListObject {

    private String ISBN;
    private String tipo;
    private String materia;

    private String successivo;
    private Libro libroSuccessivo;

    private Integer serie;

    private Serie serieAppartenenza;
    private Integer presentazione;

    private Presentazione presentazioneAppartenenza;

    private ArrayList<DisponibileL> disponibili;


    public Integer getPresentazione() {
        return presentazione;
    }

    public void setPresentazione(Integer presentazione) {
        this.presentazione = presentazione;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSuccessivo() {
        return successivo;
    }

    public void setSuccessivo(String successivo) {
        this.successivo = successivo;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Serie getSerieAppartenenza() {
        return serieAppartenenza;
    }

    public void setSerieAppartenenza(Serie serieAppartenenza) {
        this.serieAppartenenza = serieAppartenenza;
    }

    public Presentazione getPresentazioneAppartenenza() {
        return presentazioneAppartenenza;
    }

    public void setPresentazioneAppartenenza(Presentazione presentazioneAppartenenza) {
        this.presentazioneAppartenenza = presentazioneAppartenenza;
    }

    public Libro getLibroSuccessivo() {
        return libroSuccessivo;
    }

    public void setLibroSuccessivo(Libro libroSuccessivo) {
        this.libroSuccessivo = libroSuccessivo;
    }

    public ArrayList<DisponibileL> getDisponibili() {
        return disponibili;
    }

    public void setDisponibili(ArrayList<DisponibileL> disponibili) {
        this.disponibili = disponibili;
    }

    public Libro() {
        ISBN = null;
        tipo = null;
        materia = null;
        titolo = null;
        genere = null;
        numPagine = 0;
        descrizione = null;
        fruizione = null;
        editore = null;
        autore = null;
        dataUscita = null;
        lingua = null;
        successivo = null;
        serie = null;
        presentazione = null;
    }

    public Libro(ArrayList<String> arrayList) {
        ISBN = arrayList.get(0);
        titolo = arrayList.get(1);
        genere = arrayList.get(2);
        numPagine = Integer.parseInt(arrayList.get(3));
        tipo = arrayList.get(4);
        materia = arrayList.get(5);
        descrizione = arrayList.get(6);
        fruizione = arrayList.get(7);
        editore = arrayList.get(8);
        autore = arrayList.get(9);
        dataUscita = arrayList.get(10);
        lingua = arrayList.get(11);

        successivo = arrayList.get(12);
        if (arrayList.get(13) == null)
            serie = null;
        else
            serie = Integer.parseInt(arrayList.get(13));
        if (arrayList.get(14) == null)
            presentazione = null;
        else
            presentazione = Integer.parseInt(arrayList.get(14));
    }

    @Override
    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(ISBN);
        arrayList.add(titolo);
        arrayList.add(genere);
        arrayList.add(String.valueOf(numPagine));
        arrayList.add(tipo);
        arrayList.add(materia);
        arrayList.add(descrizione);
        arrayList.add(fruizione);
        arrayList.add(editore);
        arrayList.add(autore);
        arrayList.add(dataUscita);
        arrayList.add(lingua);
        arrayList.add(successivo);
        //Essendo serie e presentazione Integer,
        // effettuando il cast a String se il valore è null si avrà "null" e non null
        if (serie == null)
            arrayList.add(null);
        else
            arrayList.add(String.valueOf(serie));
        if (presentazione == null)
            arrayList.add(null);
        else
            arrayList.add(String.valueOf(presentazione));
        return arrayList;
    }
}
