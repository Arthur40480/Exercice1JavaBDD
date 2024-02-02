package fr.fms.jdbc;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.fms.buisness.IShoppingImpl;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.CategoryDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
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
		Scanner scanner = new Scanner(System.in);
		CategoryDao categoryDao = new CategoryDao();
		IShoppingImpl iShoppingImpl = new IShoppingImpl();
		ArrayList<Category> categoryList = categoryDao.readAll();
		
		System.out.println("------------------- taper le numéro correspondant -----------------------");
		System.out.println("1: Ajouter un article - 2: Retirer un article - 3: Afficher le panier - 4: Payer la commande - 5: Sortir");
		
		int userChoice = validateInput(5);
		
		switch(userChoice) {
		case 1:
			System.out.println("------------------- Liste des articles -----------------------");
			System.out.println();
			displayAllArticles(categoryList);
			System.out.println("Veuillez indiquer la référence de l'article à ajouter:");
			int articleSelected;
			while(true) {
				try {
					articleSelected = scanner.nextInt();

				} catch(InputMismatchException e) {
					System.err.print("ERREUR : Saisie de mauvais caractère ");
					e.printStackTrace();
					scanner.next();
				}
			}
//			int refSelectedArticleToAdd = validateInput(categoryList.size()) - 1;
//			iShoppingImpl.addToCart(categoryList.get(refSelectedArticleToAdd), userCurrent.getCart());
//			displayMenu(userCurrent);
			break;
			
		case 2:
			iShoppingImpl.displayCart(userCurrent.getCart());
			if(userCurrent.getCart().size() == 0) {
				displayMenu(userCurrent);
				break;
			} else {
				System.out.println("Veuillez indiquer la référence de l'article à retirer:");
				int refSelectedArticleToRemove = validateInput(userCurrent.getCart().size()) - 1;
				iShoppingImpl.removeToCart(userCurrent.getCart(), userCurrent.getCart().get(refSelectedArticleToRemove));
				displayMenu(userCurrent);
				break;
			}

		case 3:
			iShoppingImpl.displayCart(userCurrent.getCart());
			displayMenu(userCurrent);
			break;
			
		case 4:
			if(userCurrent.getCart().size() == 0) {
				System.out.println("Impossible de finaliser votre commande, votre panier est vide !");
				System.out.println();
				displayMenu(userCurrent);
				break;
			}else {
				iShoppingImpl.placeOrder(userCurrent);
				displayMenu(userCurrent);
				break;
			}
		
		case 5:
			System.out.println("Au revoir et à bientôt sur DIGITAL DEPOT !");
			System.exit(0);
			break;
		
		}
	}
	
	/**
	 * Méthode qui nous permet d'afficher l'ensemble des articles disponible
	 * @param articleList représente la liste qui contient l'ensemble des articles
	 */
	public static void displayAllArticles(ArrayList<Category> categoryList) {
	    System.out.printf("%-20s %-20s %-30s %-20s %-10s\n", "Référence", "Catégorie", "Description", "Marque", "Prix");
	    System.out.println();
	    for(Category category : categoryList) {
	        for(Article article : category.getArticleList()) {
	            System.out.printf("%-20s %-20s %-30s %-20s %-10s\n", 
	                              article.getIdArticle(), category.getName(), 
	                              article.getDescription(), article.getBrand(), article.getPrice());
	        }
	        System.out.println();
	    }
	    System.out.println();
	
	}
}
