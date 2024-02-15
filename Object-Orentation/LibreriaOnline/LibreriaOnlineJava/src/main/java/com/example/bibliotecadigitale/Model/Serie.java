package com.example.bibliotecadigitale.Model;

public class Serie {

    private int codS;
    private String nome;
    private int numLibri;
    private boolean completata;

    public int getCods() {
        return codS;
    }

    public void setCods(int codS) {
        this.codS = codS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumlibri() {
        return numLibri;
    }

    public void setNumlibri(int numLibri) {
        this.numLibri = numLibri;
    }

    public boolean getCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }
}
