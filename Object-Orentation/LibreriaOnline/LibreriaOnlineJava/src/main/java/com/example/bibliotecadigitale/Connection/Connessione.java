package com.example.bibliotecadigitale.Connection;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

public class Connessione {
    private static final String url;
    private static final String user;
    private static final String password;
    private static final Connection con;


    static {
        //connessione alla base di dati libreriaOnline
        try {
            //leggo le credenziali dal file credenzialiProgetto.txt
            //il file deve essere nella cartella documenti
            ArrayList<String> credenziali = leggiCredenziali();
            url = credenziali.get(0);

            //TODO:Da rivedere questa cosa con il try catch non so perche serve e perche se cattura un eccezzione, forse serve il driver da qualche parte ?
            try {
                Class.forName(credenziali.get(1));
            } catch (ClassNotFoundException e) {
            }
            user = credenziali.get(2);
            password = credenziali.get(3);
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public ResultSet executeSearch(String query) throws SQLException {
        Statement stat = con.createStatement();
        return stat.executeQuery(query);
    }

    public static ArrayList<String> leggiCredenziali() {
        try {
            String pathDocumenti = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File file = new File(pathDocumenti + "\\credenzialiProgetto.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> credenzialiAppoggio = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                credenzialiAppoggio.add(st);
            }
            return credenzialiAppoggio;
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file credenzialiProgetto.txt, inserire il file nella cartella documenti");
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String query) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate(query);
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumeroUtenti() {
        int numeroUtenti = 0;
        try {
            Connessione connessione = new Connessione();
            ResultSet rs = connessione.executeSearch("SELECT COUNT(*) FROM utente");
            while (rs.next()) {
                numeroUtenti = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numeroUtenti;
    }

    public int getNumeroOnline() {
        int numeroOnline = 0;
        try {
            Connessione connessione = new Connessione();
            ResultSet rs = connessione.executeSearch("SELECT count(*) FROM pg_stat_activity WHERE datname = 'bibliotecadigitaledb';");
            while (rs.next()) {
                numeroOnline = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numeroOnline;
    }

    public int getNumeroNotifiche(String email) {
        int numeroNotifiche = 0;
        try {
            Connessione connessione = new Connessione();
            String query = "select COUNT(*) from show_preferiti('" + email + "');";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                numeroNotifiche = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numeroNotifiche;
    }
}
