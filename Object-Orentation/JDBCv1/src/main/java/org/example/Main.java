package org.example;
import java.sql.*;


public class Main {

    public static void main(String[] args){
        try{
            //classe singleton per ottenre la connessione
            //ci sara' solamente una singola instanza di connection
            Connection conn = Connessione.getConnection();
            Statement st = conn.createStatement();
            /*weewe*/
            /*cviao*/
            /*ciaogiulio*/
            /*fabry40*/

            /* Pericolo di SQL Injection
            String sqlps = "Select * from $table";
            sqlps = sqlps.replace("$table","cinema");
            PreparedStatement ps = conn.prepareStatement(sqlps);
            ResultSet rs = ps.executeQuery();
            */

            //ottenimento del risultato della query
            ResultSet rs = st.executeQuery("Select * from Film");
            //ottenimento dei metadati della query
            ResultSetMetaData rsm = rs.getMetaData();

            //controllo se le colonne del risultato della query possono avere valore null
            for(int i = 1; i< rsm.getColumnCount();i++){
                System.out.println(rsm.getColumnName(i));
                if (rsm.isNullable(i) == 1) {
                    System.out.println("E' nullabile");
                }
                System.out.println(rsm.getColumnTypeName(i));
            }

            //visualizzazione della seconda colonna (nome del film) del risultato della query
            while(rs.next()){

                System.out.println(rs.getString(2));
            }rs.close();


            //chiusura della connessione
            rs.close();
            st.close();
            conn.close();

        }catch(SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}