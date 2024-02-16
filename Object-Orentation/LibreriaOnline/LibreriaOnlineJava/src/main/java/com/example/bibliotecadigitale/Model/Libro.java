package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Libro extends Pubblicazioni{

    private String ISBN;
    private String tipo;
    private String materia;

    private String successivo;

    private Integer serie;
    private Integer presentazione;

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

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(ISBN);
        arrayList.add(getTitolo());
        arrayList.add(getGenere());
        arrayList.add(String.valueOf(getNumpagine()));
        arrayList.add(getTipo());
        arrayList.add(getMateria());
        arrayList.add(getDescrizione());
        arrayList.add(getFruizione());
        arrayList.add(getEditore());
        arrayList.add(getAutore());
        arrayList.add(getDatauscita());
        arrayList.add(getLingua());
        arrayList.add(successivo);
        arrayList.add(String.valueOf(serie));
        arrayList.add(String.valueOf(presentazione));
        return arrayList;
    }

    public static Libro arrayListToObj(ArrayList<String> arrayList) {
        Libro libro = new Libro();
        libro.setIsbn(arrayList.get(0));
        libro.setTitolo(arrayList.get(1));
        libro.setGenere(arrayList.get(2));
        libro.setNumpagine(Integer.parseInt(arrayList.get(3)));
        libro.setTipo(arrayList.get(4));
        libro.setMateria(arrayList.get(5));
        libro.setDescrizione(arrayList.get(6));
        libro.setFruizione(arrayList.get(7));
        libro.setEditore(arrayList.get(8));
        libro.setAutore(arrayList.get(9));
        libro.setDatauscita(arrayList.get(10));
        libro.setLingua(arrayList.get(11));
        libro.setSuccessivo(arrayList.get(12));
        libro.setSerie(Integer.parseInt(arrayList.get(13)));
        libro.setPresentazione(Integer.parseInt(arrayList.get(14)));
        return libro;
    }
}
