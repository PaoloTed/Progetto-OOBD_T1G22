package com.example.bibliotecadigitale.Model;

public class Acquisto
{
    private int codA;
    private String nome;
    private String tipoA;
    private String url;

    public int getCoda() {
        return codA;
    }

    public void setCoda(int coda) {
        this.codA = coda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setTipoa(String tipoA) {
        this.tipoA = tipoA;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    private String indirizzo;

    public String getTipoa() {
        return tipoA;
    }
}
