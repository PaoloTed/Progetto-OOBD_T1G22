package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public  class Utente {

    private String email;
    private String password;
    private String data;

    private static Utente istanza= null;

    private Utente() {}

    public static Utente getUtente() {
        if(istanza==null) {
            istanza=new Utente();
        }
        return istanza;
    }

    public void exitUtente() {
        istanza=null;
    }
    public void setUtente(String email, String password,String data) {
        this.email = email;
        this.password = password;
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(email);
        arrayList.add(password);
        arrayList.add(data);
        return arrayList;
    }

    public static Utente arrayListToObj(ArrayList<String> arrayList) {
        Utente utente = new Utente();
        utente.setEmail(arrayList.get(0));
        utente.setPassword(arrayList.get(1));
        utente.setData(arrayList.get(2));
        return utente;
    }

}
