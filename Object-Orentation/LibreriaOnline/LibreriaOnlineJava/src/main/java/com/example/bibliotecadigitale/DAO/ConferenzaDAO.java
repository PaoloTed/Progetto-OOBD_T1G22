package com.example.bibliotecadigitale.DAO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ConferenzaDAO extends DAO {
    ArrayList<String> get(int codC)throws SQLException;
}
