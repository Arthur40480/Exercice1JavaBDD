package fr.fms.buisness;

import java.util.ArrayList;

import fr.fms.entities.Article;

public interface IShopping {
	public ArrayList<Article> addToCart(Article article, ArrayList<Article> cart);
	public void displayCart(ArrayList<Article> cart);
	public ArrayList<Article> removeToCart(ArrayList<Article> cart, Article article);
	public double calculateTotal(ArrayList<Article> cart);
	public void processPayment();
}
