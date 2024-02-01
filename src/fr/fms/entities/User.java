package fr.fms.entities;

public class User {
	private int idUser;
	private String login;
	private String password;
	
	public User(int idUser, String login, String password) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
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
}
