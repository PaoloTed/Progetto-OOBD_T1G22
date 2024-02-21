package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

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

    Acquisto() {}
    public Acquisto(ArrayList<String> strings) {
        codA = Integer.parseInt(strings.get(0));
        if(strings.get(1) == null) {
            nome = null;
        } else {
            nome = strings.get(1);
        }
        tipoA = strings.get(2);
        if(strings.get(3) == null) {
            url = null;
        } else {
            url = strings.get(3);
        }
        if(strings.get(4) == null) {
            indirizzo = null;
        } else {
            indirizzo = strings.get(4);
        }
    }
}
