package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Libreria extends Acquisto{

    private String indirizzo;

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public ArrayList<String> toArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(String.valueOf(getCoda()));
        arrayList.add(getNome());
        arrayList.add(getTipo());
        arrayList.add(getUrl());
        arrayList.add(indirizzo);
        return arrayList;
    }

    public static Libreria arrayListToObj(ArrayList<String> arrayList) {
        Libreria libreria = new Libreria();
        libreria.setCoda(Integer.parseInt(arrayList.get(0)));
        libreria.setNome(arrayList.get(1));
        libreria.setTipo(arrayList.get(2));
        libreria.setUrl(arrayList.get(3));
        libreria.setIndirizzo(arrayList.get(4));
        return libreria;
    }
}
