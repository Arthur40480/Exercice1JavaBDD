package fr.fms.jdbc;

import java.sql.*;
import java.util.ArrayList;

import fr.fms.entities.*;

public class TestJdbc {
	public static void main(String[] args) {
		ArrayList<Article> articleList = new ArrayList<>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");		// On enregistre la class auprès du driver manager -> on charge le pilote
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//On récupère une connection à partir d'une urli + id + password:
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String password = "ArthurGibertTosse40230";
		
		try(Connection connection = DriverManager.getConnection(url, login, password)) {	// Connection de java.sql
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
