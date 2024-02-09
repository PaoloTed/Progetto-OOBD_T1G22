package com.example.bibliotecadigitale.Model;

public class Conferenza extends Piattaforma {

    private int codC;
    private String nome;
    private String struttura;
    private String indirizzo;
    private String dataI;
    private String dataF;

    private String Responsabile;

    public int getCodc() {
        return codC;
    }

    public void setCodc(int codC) {
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

    public String getDatai() {
        return dataI;
    }

    public void setDatai(String dataI) {
        this.dataI = dataI;
    }

    public void setDataf(String dataF) {
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

    public String getDataf() {
        return dataF;
    }


}
