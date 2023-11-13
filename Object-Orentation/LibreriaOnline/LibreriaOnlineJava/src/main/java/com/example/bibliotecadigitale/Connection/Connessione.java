package com.example.bibliotecadigitale.Connection;
import java.sql.*;

public class Connessione {
    public static String url ="jdbc:postgresql://bibliotecadigitaledb.ctxlqsbyivq2.eu-north-1.rds.amazonaws.com:5432/bibliotecadigitaledb";
    public static String className = "org.postgre.Driver";
    public static String user = "postgres";
    public static String password = "bibliotecadigitaledb";
    private static Connection con;

    static{
        try{
            Class.forName(className);
        }catch(ClassNotFoundException e1){
        }
        //connessione alla base di dati libreriaOnline
        try {
            con = DriverManager.getConnection(url,user,password);//forse segalare al prof di aggiungere il drive al proggetto
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        return con;
    }

    /*public  ResultSet query(String query){
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(query);
        return rs;
    }*/
}
