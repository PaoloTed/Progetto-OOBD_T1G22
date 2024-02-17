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

    public Libro(){}

    public Libro(ArrayList<String> arrayList) {
        ISBN = arrayList.get(0);
        setTitolo(arrayList.get(1));
        setGenere(arrayList.get(2));
        setNumpagine(Integer.parseInt(arrayList.get(3)));
        setTipo(arrayList.get(4));
        setMateria(arrayList.get(5));
        setDescrizione(arrayList.get(6));
        setFruizione(arrayList.get(7));
        setEditore(arrayList.get(8));
        setAutore(arrayList.get(9));
        setDatauscita(arrayList.get(10));
        if(arrayList.get(13) == null)
            setSerie(null);
        else{
            setSerie(Integer.parseInt(arrayList.get(13)));
        }
        if(arrayList.get(14) == null)
            setPresentazione(null);
        else {
            setPresentazione(Integer.parseInt(arrayList.get(14)));
        }
    }
}
