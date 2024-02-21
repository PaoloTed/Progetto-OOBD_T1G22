package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

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

    public Conferenza(){}

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(codC));
        arrayList.add(nome);
        arrayList.add(struttura);
        arrayList.add(indirizzo);
        arrayList.add(dataI);
        arrayList.add(dataF);
        arrayList.add(Responsabile);
        return arrayList;
    }

     public Conferenza (ArrayList<String> arrayList) {
        setCodc(Integer.parseInt(arrayList.get(0)));
        setNome(arrayList.get(1));
        setStruttura(arrayList.get(2));
        setIndirizzo(arrayList.get(3));
        setDatai(arrayList.get(4));
        setDataf(arrayList.get(5));
        setResponsabile(arrayList.get(6));
    }
}
