package com.example.bibliotecadigitale.Model;

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

}
