package fr.fms.jdbc;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;

public class Shop {
	public static void main(String[] args) {
		
		System.out.println("Bonjour et bienvenu chez DIGITAL DEPOT");
		userConnect();
	}
	
	// !!!!!!!!! EXERCICE 10 !!!!!!!!!
	/**
	 * Méthode permettant récupérer un login et un mot de passe que l'utilisateur saisie et
	 * de vérifier si un User existe avec ce login et ce mot de passe
	 */
	public static void userConnect() {
		UserDao daoUser = new UserDao();
		ArticleDao daoArticle = new ArticleDao();
		ArrayList<Article> articleList = new ArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		String login = "";
		String password = "";
		
		String loginRegex = "^[a-zA-Z0-9]+$";
		String passwordRegex = "^[a-zA-Z0-9!@#$%^&*]+$";
		
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
		
		if(daoUser.userExists(login, password)) {
			System.out.println("-------- Liste des articles disponible --------");
			articleList = daoArticle.readAll();
			for(Article article : articleList) {
				System.out.println(article);
			}
		}
		scanner.close();
	}
}
