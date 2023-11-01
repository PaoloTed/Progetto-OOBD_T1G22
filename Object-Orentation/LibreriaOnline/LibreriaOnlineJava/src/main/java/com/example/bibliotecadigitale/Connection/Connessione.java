package com.example.bibliotecadigitale.Connection;
import java.sql.*;

public class Connessione {
    public static String url ="jdbc:postgresql://localhost:5432/libreriaOnline";
    public static String className = "org.postgre.Driver";
    public static String user = "paolo";
    public static String password = "pippo";
    private static Connection con;

    static{
        try{
            Class.forName(className);
        }catch(ClassNotFoundException e1){}
        //connessione alla base di dati
        try {
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        return con;
    }
}
