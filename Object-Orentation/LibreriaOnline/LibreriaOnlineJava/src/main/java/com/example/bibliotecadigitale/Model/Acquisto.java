package com.example.bibliotecadigitale.Model;

public class Acquisto
{
    private int codA;
    private String nome;
    private String tipo;
    private String url;

    public int getCodA() {
        return codA;
    }

    public void setCodA(int coda) {
        this.codA = coda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
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

}
