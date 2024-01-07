package com.example.bibliotecadigitale.Model;

public class Libro extends Pubblicazioni{

    private String ISBN;
    private String tipo;
    private String materia;

    private String successivo;

    private int serie;

    public void prova(Libro libro) {
        this.ISBN = libro.getISBN();
        this.tipo = libro.getTipo();
        this.materia = libro.getMateria();
        this.successivo = libro.getSuccessivo();
        this.serie = libro.getSerie();
        this.presentazione = libro.getPresentazione();
    }



    public String getPresentazione() {
        return presentazione;
    }

    public void setPresentazione(String presentazione) {
        this.presentazione = presentazione;
    }

    private String presentazione;

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

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

}
