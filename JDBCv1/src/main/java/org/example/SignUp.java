package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;


public class SignUp {

    public static void main(String[] args){
        JSONObject jsonSignUp = new JSONObject();
        jsonSignUp.put("Username","wssss");
        jsonSignUp.put("Password","Pe");
        try{
            FileWriter fileJsonSignUp = new FileWriter("C:/Users/Paolo Tedesco/OneDrive - Assa Abloy Inc/Desktop/Universita/DataBase/EsJDBC/userSIgnUp.json");
            fileJsonSignUp.append(jsonSignUp.toJSONString());
            fileJsonSignUp.close();
        }catch(Exception e){}
        System.out.println("JSON file created: "+jsonSignUp);
    }
}
