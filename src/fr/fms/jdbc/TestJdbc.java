package fr.fms.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.*;
import fr.fms.bdd.*;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;

public class TestJdbc {
	public static void main(String[] args) {
		ArticleDao dao = new ArticleDao();
		UserDao daoUser = new UserDao();
		
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
			System.out.println();

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
				
		ArrayList<Article> articleList = dao.readAll();
		for(Article article : articleList) {
			System.out.println(article);
		}
		
		User bernard = new User("BernardB", "supermdp");
		daoUser.create(bernard);
		System.out.println(bernard.getUserId());
	}
}
