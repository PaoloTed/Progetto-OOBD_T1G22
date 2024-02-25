package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Rivista extends Piattaforma{

    private String nome;
    private String data;

    private String argomento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }

    public Rivista(){}

    public Rivista(ArrayList<String> arrayList) {
        setNome(arrayList.get(0));
        setData(arrayList.get(1));
        setResponsabile(arrayList.get(2));
        setArgomento(arrayList.get(3));
    }

    public ArrayList<String> ObjToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(nome);
        arrayList.add(data);
        arrayList.add(argomento);
        arrayList.add(getResponsabile());
        return arrayList;
    }
}
