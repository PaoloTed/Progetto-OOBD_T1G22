package com.example.bibliotecadigitale.Model;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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


}
