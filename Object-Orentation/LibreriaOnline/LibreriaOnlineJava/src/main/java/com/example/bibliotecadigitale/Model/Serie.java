package com.example.bibliotecadigitale.Model;

public class Serie {

    private String codS;
    private String nome;
    private int numLibri;
    private boolean completata;
    public String getCodS() {
        return codS;
    }

    public void setCodS(String codS) {
        this.codS = codS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumLibri() {
        return numLibri;
    }

    public void setNumLibri(int numLibri) {
        this.numLibri = numLibri;
    }

    public boolean isCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }



}
