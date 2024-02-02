package fr.fms.jdbc;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class Shop {
	public static void main(String[] args) {
		
		System.out.println("Bonjour et bienvenu chez DIGITAL DEPOT");
		User userCurrent = userConnect();
		System.out.println(userCurrent);
	}
	
	/**
	 * Méthode permettant récupérer un login et un mot de passe que l'utilisateur saisie et
	 * de vérifier si un User existe avec ce login et ce mot de passe
	 */
	public static User userConnect() {
		User currentUser = null;
		UserDao daoUser = new UserDao();
		
		Scanner scanner = new Scanner(System.in);
		
		String loginRegex = "^[a-zA-Z0-9]+$";
		String passwordRegex = "^[a-zA-Z0-9!@#$%^&*]+$";
		while(currentUser == null) {
			String login = "";
			String password = "";
			
			while(!login.matches(loginRegex)) {
				System.out.println("Veuillez saisir votre login:");
				login = scanner.next();
				if(!login.matches(loginRegex)) System.out.println("Caractère interdit pour le login !");
			}
			while(!password.matches(passwordRegex)) {
				System.out.println("Veuillez saisir votre mot de passe:");
				password = scanner.next();
				if(!password.matches(passwordRegex)) System.out.println("Caractère interdit pour le password !");
			}
			
			currentUser = daoUser.userExists(login, password);
		}
		return currentUser;
	}
}
