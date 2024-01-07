package com.example.bibliotecadigitale.Model;

import java.util.Date;

public class Presentazione {

    private int codP;
    private String nome;
    private String indirizzo;
    private String dataPresentazione;
    private String tipo;

    public int getCodP() {
        return codP;
    }

    public void setCodP(int codP) {
        this.codP = codP;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDataPresentazione() {
        return dataPresentazione;
    }

    public void setDataPresentazione(String dataPresentazione) {
        this.dataPresentazione = dataPresentazione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
