package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class ArticoloScientifico extends Pubblicazioni {

    private String doi;
    private Integer conferenza;
    private String nomer;
    private String datar;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Integer getConferenza() {
        return conferenza;
    }

    public void setConferenza(Integer conferenza) {
        this.conferenza = conferenza;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getDatar() {
        return datar;
    }

    public void setDatar(String datar) {
        this.datar = datar;
    }

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(doi);
        arrayList.add(this.getTitolo());
        arrayList.add(this.getNumpagine()+"");
        arrayList.add(this.getDatauscita());
        arrayList.add(this.getDescrizione());
        arrayList.add(this.getFruizione());
        arrayList.add(this.getEditore());
        arrayList.add(this.getAutore());
        arrayList.add(this.getLingua());
        arrayList.add(String.valueOf(conferenza));
        arrayList.add(nomer);
        arrayList.add(datar);
        return arrayList;
    }

    public static ArticoloScientifico ArrayListToObj(ArrayList<String> arrayList) {
        ArticoloScientifico articoloScientifico = new ArticoloScientifico();
        articoloScientifico.setDoi(arrayList.get(0));
        articoloScientifico.setTitolo(arrayList.get(1));
        articoloScientifico.setNumpagine(Integer.parseInt(arrayList.get(2)));
        articoloScientifico.setDatauscita(arrayList.get(3));
        articoloScientifico.setDescrizione(arrayList.get(4));
        articoloScientifico.setFruizione(arrayList.get(5));
        articoloScientifico.setEditore(arrayList.get(6));
        articoloScientifico.setAutore(arrayList.get(7));
        articoloScientifico.setLingua(arrayList.get(8));
        articoloScientifico.setConferenza(Integer.valueOf(arrayList.get(9)));
        articoloScientifico.setNomer(arrayList.get(10));
        articoloScientifico.setDatar(arrayList.get(11));
        return articoloScientifico;
    }
}
