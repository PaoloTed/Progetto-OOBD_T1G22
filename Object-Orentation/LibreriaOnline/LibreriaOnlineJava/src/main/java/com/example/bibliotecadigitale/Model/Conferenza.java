package com.example.bibliotecadigitale.Model;

import java.util.Date;

public class Conferenza extends Piattaforma {

    private int codC;
    private String nome;
    private String struttura;
    private String indirizzo;
    private String dataI;
    private String dataF;

    private String Responsabile;

    public int getCodC() {
        return codC;
    }

    public void setCodC(int codC) {
        this.codC = codC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStruttura() {
        return struttura;
    }

    public void setStruttura(String struttura) {
        this.struttura = struttura;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDataI() {
        return dataI;
    }

    public void setDataI(String dataI) {
        this.dataI = dataI;
    }

    public void setDataF(String dataF) {
        this.dataF = dataF;
    }

    @Override
    public String getResponsabile() {
        return Responsabile;
    }

    @Override
    public void setResponsabile(String responsabile) {
        Responsabile = responsabile;
    }

    public String getDataF() {
        return dataF;
    }


}
