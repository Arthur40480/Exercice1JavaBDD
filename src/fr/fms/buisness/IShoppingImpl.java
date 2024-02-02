package fr.fms.buisness;

import java.util.ArrayList;

import fr.fms.entities.Article;

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
		System.out.println("------ PANIER ------");
		System.out.println();
		
		if(cart.size() == 0) {
			System.out.println("Votre panier est vide !");
			System.out.println();
			System.out.println("Total: " + calculateTotal(cart) + "€");
		}else {
			for(Article article : cart) {
				System.out.println("Référence:" + article.getIdArticle() + "- " + article.getDescription() + "     " + article.getBrand() + "     " + article.getPrice() + "€");
			}
			System.out.println("Total: " + calculateTotal(cart) + "€");
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
		}else {
			System.out.println("L'article n'était pas dans le panier");
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
	
	@Override
	public void processPayment() {
		// TODO Vas générer une nouvelle commande !
		
	}

}
