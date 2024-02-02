package fr.fms.entities;

import java.util.ArrayList;

public class User {
	private int idUser;
	private String login;
	private String password;
	private ArrayList<Article> cart;
	
	public User(int idUser, String login, String password) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.cart = new ArrayList<Article>();
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.cart = new ArrayList<Article>();
	}
	
	/**
	 * La méthode toString() permet d'afficher un User et ces informations
	 * @return String
	 */
	public String toString() {
		return "Id de l'utilisateur: " + getId() + " -  Login: " + getLogin() + " - Password: " + getPassword();
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return idUser;
	}
	
	public ArrayList<Article> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Article> cart) {
		this.cart = cart;
	}
}
