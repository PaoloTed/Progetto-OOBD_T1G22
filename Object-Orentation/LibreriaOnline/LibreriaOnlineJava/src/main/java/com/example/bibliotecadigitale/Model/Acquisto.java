package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Acquisto
{
    private int coda;
    private String nome;
    private String tipo;
    private String url;

    private ArrayList<DisponibileS> disponibiliS;

    private ArrayList<DisponibileL> disponibiliL;

    private ArrayList<DisponibileA> disponibiliA;

    public int getCoda() {
        return coda;
    }

    public void setCoda(int coda) {
        this.coda = coda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setTipo(String tipoA) {
        this.tipo = tipoA;
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

    public String getTipo() {
        return tipo;
    }

    public ArrayList<DisponibileS> getDisponibiliS() {
        return disponibiliS;
    }

    public void setDisponibiliS(ArrayList<DisponibileS> disponibiliS) {
        this.disponibiliS = disponibiliS;
    }

    public ArrayList<DisponibileL> getDisponibiliL() {
        return disponibiliL;
    }

    public void setDisponibiliL(ArrayList<DisponibileL> disponibiliL) {
        this.disponibiliL = disponibiliL;
    }

    public ArrayList<DisponibileA> getDisponibiliA() {
        return disponibiliA;
    }

    public void setDisponibiliA(ArrayList<DisponibileA> disponibiliA) {
        this.disponibiliA = disponibiliA;
    }

    public Acquisto() {}
    public Acquisto(ArrayList<String> strings) {
        coda = Integer.parseInt(strings.get(0));
        if(strings.get(1) == null) {
            nome = null;
        } else {
            nome = strings.get(1);
        }
        tipo = strings.get(2);
        if(strings.get(3) == null || strings.get(3).equals("null")) {
            url = null;
        } else {
            url = strings.get(3);
        }
        if(strings.get(4) ==  null || strings.get(4).equals("null")) {
            indirizzo = null;
        } else {
            indirizzo = strings.get(4);
        }
    }

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        if(nome == null) {
            arrayList.add(null);
        } else {
            arrayList.add(String.valueOf(coda));
        }
        arrayList.add(String.valueOf(coda));
        arrayList.add(nome);
        arrayList.add(tipo);
        arrayList.add(url);
        arrayList.add(indirizzo);
        return arrayList;
    }
}
