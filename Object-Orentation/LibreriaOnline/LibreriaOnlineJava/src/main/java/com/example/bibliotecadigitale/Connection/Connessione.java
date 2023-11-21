package com.example.bibliotecadigitale.Connection;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

public class Connessione {
    public static String url = "";
    public static String className = "";
    public static String user = "";
    public static String password = "";
    private static Connection con;


    static {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e1) {
        }
        //connessione alla base di dati libreriaOnline
        try {
            try {
                //leggo le credenziali dal file credenzialiProgetto.txt
                //il file deve essere nella cartella documenti
                ArrayList<String> credenziali = leggiCredenziali();
                url = credenziali.get(0);
                className = credenziali.get(1);
                user = credenziali.get(2);
                password = credenziali.get(3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            con = DriverManager.getConnection(url, user, password);//forse segnalare al prof di aggiungere il drive al progetto
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public ResultSet executeSearch(String query) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);
        return rs;
    }

    public static ArrayList<String> leggiCredenziali() throws IOException {
        String pathDocumenti = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        File file = new File(pathDocumenti + "\\credenzialiProgetto.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> credenzialiAppoggio = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            credenzialiAppoggio.add(st);
        }
        return credenzialiAppoggio;

    }

    public void executeUpdate(String query) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate(query);
    }
}
