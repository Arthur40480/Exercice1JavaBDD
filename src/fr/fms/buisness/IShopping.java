package fr.fms.buisness;

public interface IShopping<Article> {
	public void addToCart(Article article);
	public void displayCart();
	public void removeToCart(Article article);
	public void processPayment();
}
