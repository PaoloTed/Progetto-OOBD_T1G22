package com.example.bibliotecadigitale.Model;

import java.util.Date;

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
}
