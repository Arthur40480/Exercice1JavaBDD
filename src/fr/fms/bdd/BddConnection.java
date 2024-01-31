package fr.fms.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/shop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "ArthurGibertTosse40230";

    public static Connection getConnection(){
    	try {
    		return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    	}catch(SQLException e) {
    		System.out.println("ERREUR : Connexion impossible");
    		e.printStackTrace();
    		return null;
    	}      
    }
}