package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;


public class ArticoloScientifico extends Pubblicazioni implements ArrayListObject{

    private String doi;
    private Integer conferenza;
    private String nomer;
    private String datar;

    private Piattaforma piattaforma;



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

    @Override
    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(doi);
        arrayList.add(this.getTitolo());
        arrayList.add(this.getGenere());
        arrayList.add(String.valueOf(getNumpagine()));
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

    public ArticoloScientifico() {}
    public ArticoloScientifico(ArrayList<String> arrayList) {
        setDoi(arrayList.get(0));
        setTitolo(arrayList.get(1));
        setGenere(arrayList.get(2));
        setNumpagine(Integer.parseInt(arrayList.get(3)));
        if (arrayList.get(4) == null || arrayList.get(4).isEmpty())
            setDatauscita(null);
        else{
            setDatauscita(arrayList.get(4));
        }
        setDescrizione(arrayList.get(5));
        setFruizione(arrayList.get(6));
        setEditore(arrayList.get(7));
        setAutore(arrayList.get(8));
        setLingua(arrayList.get(9));
        if (arrayList.get(10) == null || arrayList.get(10).isEmpty())
            setConferenza(null);
        else{
            setConferenza(Integer.valueOf((arrayList.get(10))));
        }
        if (arrayList.get(11) == null || arrayList.get(11).isEmpty())
            setNomer(null);
        else{
            setNomer(arrayList.get(11));
        }
        if (arrayList.get(12) == null || arrayList.get(12).isEmpty())
            setDatar(null);
        else{
            setDatar(arrayList.get(12));
        }
    }
}
