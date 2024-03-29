package fr.fms.buisness;

import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.Order;
import fr.fms.entities.User;

public class IShoppingImpl implements IShopping {
	
	/**
	 * Méthode qui vas permettre d'ajouter un article au panier
	 * @param article est l'article à rajouter
	 * @param cart est le panier dans lequel on vas rajouter l'article
	 * @return cart On retourne le cart une fois l'article ajouter
	 */
	@Override
	public ArrayList<Article> addToCart(Article article, ArrayList<Article> cart) {
		cart.add(article);
		System.out.println("L'article a bien été ajouté au panier !");
		System.out.println();
		return cart;
	}
	
	/**
	 * Méthode qui permet d'afficher l'ensemble des articles dans le produit et le coût total
	 * @param cart est le panier que nous allons afficher
	 */
	@Override
	public void displayCart(ArrayList<Article> cart) {
		int idArticleInCart = 1;
		System.out.println("------ PANIER ------");
		System.out.println();
		
		if(cart.size() == 0) {
			System.out.println("Votre panier est vide !");
			System.out.println();
			System.out.println("Total: " + calculateTotal(cart) + "€");
			System.out.println();
		}else {
			for(Article article : cart) {
				System.out.println("Référence: " + idArticleInCart + "     " + article.getDescription() + "     " + article.getBrand() + "     " + article.getPrice() + "€");
				idArticleInCart++;
			}
			System.out.println("Total: " + calculateTotal(cart) + "€");
			System.out.println();
		}				
	}
	
	/**
	 * Méthode qui permet de retiré un article du panier
	 * @param cart est le panier sur lequel nous allons retiré un article
	 * @param article est l'article a retiré
	 * @return cart est le panier que l'on retourne une fois l'article retiré
	 */
	@Override
	public ArrayList<Article> removeToCart(ArrayList<Article> cart, Article article) {
		boolean removed = cart.remove(article);
		if(removed) {
			System.out.println("L'article à bien été retiré du panier !");
			System.out.println();
		}else {
			System.out.println("L'article n'était pas dans le panier");
			System.out.println();
		}
		return cart;		
	}
	
	/**
	 * Méthode qui permet de calculer le coût total des articles dans le panier
	 * @param cart est le panier pour lequel on vas calculer le coût total
	 * @return totalPrice est le coût total du panier que l'on retourne
	 */
	@Override
	public double calculateTotal(ArrayList<Article> cart) {
		double totalPrice = 0;
		for(Article article : cart) {
			totalPrice = totalPrice + article.getPrice();
		}
		return totalPrice;
	}
	
	/**
	 * Méthode qui permet de passer une commande à l'utilisateur, et de l'ajouter à la liste de commande de l'user
	 * @param user représente l'utilisateur qui veut passer commande
	 * @return order représente la commande
	 */
	@Override
	public Order placeOrder(User user) {
		Order order = new Order(user.getCart(), calculateTotal(user.getCart()), user);
		user.getOrderList().add(order);
		System.out.println("Commande effectuer ! Merci !");
		
		return order;		
	}

}
