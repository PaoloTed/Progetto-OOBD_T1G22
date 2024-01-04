package com.example.bibliotecadigitale.Model;

public  class Utente {

    private String email;
    private String password;
    private String data;
    private Boolean isAdmin=false;

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

    public void setAdmin() {
        this.isAdmin=true;
    }
    public void setNotAdmin() {
        this.isAdmin=false;
    }
    public Boolean getAdmin() {
        return isAdmin;
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
