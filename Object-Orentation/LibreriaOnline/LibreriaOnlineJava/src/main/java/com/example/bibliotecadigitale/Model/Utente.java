package com.example.bibliotecadigitale.Model;

import java.util.ArrayList;

public class Utente {

    private String email;
    private String password;
    private String data;

    private static Utente istanza= null;

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
    public Utente(){}

    public ArrayList<String> objToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(email);
        arrayList.add(password);
        arrayList.add(data);
        return arrayList;
    }

    public Utente(ArrayList<String> arrayList) {
        setEmail(arrayList.get(0));
        setPassword(arrayList.get(1));
        setData(arrayList.get(2));
    }

}
