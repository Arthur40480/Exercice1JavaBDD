package fr.fms.jdbc;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.fms.buisness.IShoppingImpl;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class Shop {
	public static void main(String[] args) {
		
		System.out.println("-------- DIGITAL DEPOT --------");
		User userCurrent = userConnect();
		System.out.println("Bonjour et bienvenu " + userCurrent.getLogin() + " !");
		displayMenu(userCurrent);
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
	
	/**
	 * Méthode permettant de vérifier les entrées saisie par l'utilisateur
	 * @param maxChoice représente un int qui est le choix maximum que l'utilisateur peux faire
	 * @return userChoice représente le choix éffectuer par l'utilisateur, une fois vérifier
	 */
	public static int validateInput(int maxChoice) {
		Scanner scanner = new Scanner(System.in);
		int userChoice;
		while(true) {
			try {
				userChoice = scanner.nextInt();
				if(userChoice > 0 && userChoice < maxChoice + 1) {
					break;
				} else {
					System.out.println("Erreur : veuillez saisir numéro de choix valable (1 - " + maxChoice + ")");
				}
			} catch(InputMismatchException e) {
				System.out.println("Erreur : veuillez saisir numéro de choix valable (1 - " + maxChoice + ")");
				System.err.print("ERREUR : Saisie de mauvais caractère ");
				e.printStackTrace();
				scanner.next();
			}
		}
		
		return userChoice;
	}
	
	/**
	 * Méthode permettant d'afficher le menu principal
	 * @param userCurrent est l'utilisateur connecter
	 */
	public static void displayMenu(User userCurrent) {
		ArticleDao daoArticle = new ArticleDao();
		IShoppingImpl iShoppingImpl = new IShoppingImpl();
		ArrayList<Article> articleList;
		
		System.out.println("------------------- taper le numéro correspondant -----------------------");
		System.out.println("1: Ajouter un article - 2: Retirer un article - 3: Afficher notre panier - 4: Payer notre commande - 5: Sortir");
		
		int userChoice = validateInput(5);
		
		switch(userChoice) {
		case 1:
			System.out.println("------------------- Liste des articles -----------------------");
			articleList = daoArticle.readAll();
			displayAllArticles(articleList);
			System.out.println("Veuillez indiquer la référence de l'article à ajouter:");
			int refSelectedArticle = validateInput(articleList.size()) - 1;
			iShoppingImpl.addToCart(articleList.get(refSelectedArticle), userCurrent.getCart());
			displayMenu(userCurrent);
			break;
			
		case 2:
			System.out.println("Choix 2");
			break;
		case 3:
			System.out.println("Choix 3");
			break;
		case 4:
			System.out.println("Choix 4");
			break;
		case 5:
			System.out.println("Choix 5");
			break;
		
		}
	}
	
	/**
	 * Méthode qui nous permet d'afficher l'ensemble des articles disponible
	 * @param articleList représente la liste qui contient l'ensemble des articles
	 */
	public static void displayAllArticles(ArrayList<Article> articleList) {
		for(Article article : articleList) {
			System.out.println("Référence: " + article.getIdArticle() + "       " + article.getDescription() +
								"   Marque: " + article.getBrand() + "   Prix: " + article.getPrice() + "€");	
		}
		System.out.println();
	}
}
