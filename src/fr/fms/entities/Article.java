package fr.fms.entities;

public class Article {
	private int idArticle;
	private String description;
	private String brand;
	private double price;
	
	public Article(int idArticle, String description, String brand, double price) {
		this.idArticle = idArticle;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	
	public Article(String description, String brand, double price) {
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	
	/**
	 * La méthode toString() permet d'afficher un Article et ces informations
	 * @return String
	 */
	public String toString() {
		return "Article numéro: " + getIdArticle() + " - " + getDescription() + " - Marque: " + getBrand() + " - Prix: " + getPrice();
	}

	public int getIdArticle() {
		return idArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price < 0) {
			System.out.println("Le prix d'un article ne peut être inferieur à 0");
		} else {
			this.price = price;
		}
	}
}
