package fr.fms.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.*;
import fr.fms.bdd.*;
import fr.fms.dao.IDaoImpl;

public class TestJdbc {
	public static void main(String[] args) {
		ArrayList<Article> articleList = new ArrayList<>();
		IDaoImpl dao = new IDaoImpl();
		
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		String dbDriverClass = null;
		String dbUrl = null;
		String dbLogin = null;
		String dbPassword = null;
		
		try {
			fileInputStream = new FileInputStream("config.properties");
			properties.load(fileInputStream);
			
			dbDriverClass = properties.getProperty("db.driver.class");
			dbUrl = properties.getProperty("db.url");
			dbLogin = properties.getProperty("db.login");
			dbPassword = properties.getProperty("db.password");
			
			System.out.println("Driver class : " + properties.get("db.driver.class"));
			System.out.println("Url : " + properties.get("db.url"));
			System.out.println("Login : " + properties.get("db.login"));
			System.out.println("Password : " + properties.get("db.password"));

		}catch(IOException e) {
			System.err.println("ERREUR : Lecture du fichier impossible");
			e.printStackTrace();
		} finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

				
		try {
			Class.forName(dbDriverClass);		// On enregistre la class auprès du driver manager -> on charge le pilote
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
				
		try(Connection connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword)) {	// Connection de java.sql
			String stringSql = "SELECT * FROM T_Articles";		// Une fois connecté, réalisation d'un requête
			try(Statement statement = connection.createStatement()) {
				try(ResultSet resultSet = statement.executeQuery(stringSql)) {
					while(resultSet.next()) {
						int rsIdUser = resultSet.getInt(1); // Soit index 1 à n de la colonne
						String rsDescription = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						articleList.add(new Article(rsIdUser, rsDescription, rsBrand, rsPrice));
					}
				}
			}
			
			for(Article article : articleList) {
				System.out.println(article);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
