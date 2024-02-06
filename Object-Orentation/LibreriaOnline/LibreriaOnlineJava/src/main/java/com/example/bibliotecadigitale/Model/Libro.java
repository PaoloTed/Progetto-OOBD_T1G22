package com.example.bibliotecadigitale.Model;

public class Libro extends Pubblicazioni{

    private String ISBN;
    private String tipo;
    private String materia;

    private String successivo;

    private Integer serie;
    private Integer presentazione;

    public void prova(Libro libro) {
        this.ISBN = libro.getISBN();
        this.tipo = libro.getTipo();
        this.materia = libro.getMateria();
        this.successivo = libro.getSuccessivo();
        this.serie = libro.getSerie();
        this.presentazione = libro.getPresentazione();
    }



    public Integer getPresentazione() {
        return presentazione;
    }

    public void setPresentazione(Integer presentazione) {
        this.presentazione = presentazione;
    }



    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
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

}
