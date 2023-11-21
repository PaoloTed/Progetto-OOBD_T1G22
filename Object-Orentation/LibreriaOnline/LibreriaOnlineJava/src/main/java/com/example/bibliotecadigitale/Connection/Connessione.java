package com.example.bibliotecadigitale.Connection;
import java.sql.*;

public class Connessione {
    public static String url ="";
    public static String className = "";
    public static String user = "";
    public static String password = "";
    private static Connection con;

    static{
        try{
            Class.forName(className);
        }catch(ClassNotFoundException e1){
        }
        //connessione alla base di dati libreriaOnline
        try {
            con = DriverManager.getConnection(url,user,password);//forse segnalare al prof di aggiungere il drive al progetto
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

    public void executeUpdate(String query) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate(query);
    }
}
