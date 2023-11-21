package com.example.bibliotecadigitale.Model;

public class Libro extends Pubblicazioni{

    private String ISBN;
    private String titolo;
    private String genere;
    private int numeroPagine;
    private String tipo;
    private String materia;
    private String descrizione;
    private String fruizione;
    private String editore;

    private String autore;

    private String datauscita;
    private String lingua;
    private String successivo;



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

    @Override
    public String getTitolo() {
        return titolo;
    }

    @Override
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String getGenere() {
        return genere;
    }

    @Override
    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
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

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String getFruizione() {
        return fruizione;
    }

    @Override
    public void setFruizione(String fruizione) {
        this.fruizione = fruizione;
    }

    @Override
    public String getEditore() {
        return editore;
    }

    @Override
    public void setEditore(String editore) {
        this.editore = editore;
    }

    @Override
    public String getAutore() {
        return autore;
    }

    @Override
    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDatauscita() {
        return datauscita;
    }

    public void setDatauscita(String datauscita) {
        this.datauscita = datauscita;
    }

    @Override
    public String getLingua() {
        return lingua;
    }

    @Override
    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getSuccessivo() {
        return successivo;
    }

    public void setSuccessivo(String successivo) {
        this.successivo = successivo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    private String serie;


}
