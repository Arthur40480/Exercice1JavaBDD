package fr.fms.entities;

import java.util.ArrayList;

public class Order {
	private int idOrder;
	private ArrayList<Article> articleList;
	private double price;
	private User orderingUser;
	
	public Order(ArrayList<Article> articleList, double price, User orderingUser) {
		this.articleList = new ArrayList<Article>();
		this.price = price;
		this.orderingUser = orderingUser;
	}
	
	/**
	 * La méthode toString() permet d'afficher une Order et ces informations
	 * @return String
	 */
	public String toString() {
		return "Id de la commande: " + getIdOrder() + " -  Total: " + getPrice() + " - User: " + orderingUser.getLogin();
	}
	
	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public ArrayList<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(ArrayList<Article> articleList) {
		this.articleList = articleList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getOrderingUser() {
		return orderingUser;
	}

	public void setOrderingUser(User orderingUser) {
		this.orderingUser = orderingUser;
	}
}
