package fr.fms.entities;

import java.util.ArrayList;

public class Category {
	private int idCategory;
	private String name;
	private String description;
	private ArrayList<Article> articleList;
	
	public Category(int idCategory, String name, String description, ArrayList<Article> articleList) {
		this.idCategory = idCategory;
		this.name = name;
		this.description = description;
		this.articleList = new ArrayList<Article>();
	}
	
	public Category(int idCategory, String name, String description) {
		this.idCategory = idCategory;
		this.name = name;
		this.description = description;
	}
	
	public Category(String name, String description, ArrayList<Article> articleList) {
		this.name = name;
		this.description = description;
		this.articleList = new ArrayList<Article>();
	}
	
	/**
	 * La méthode toString() permet d'afficher une Category et ces informations
	 * @return String
	 */
	public String toString() {
		return "Id de la categorie: " + getIdCategory() + " -  Nom: " + getName() + " - Description: " + getDescription();
	}
	
	/**
	 * La méthode permet de récupérer un article dans la liste d'article via sont id
	 * @param idArticle 
	 * @return article représente l'article qui à été trouver via l'idArticle, SINON return null
	 */
	public Article findArticleById(int idArticle) {
		for(Article article : getArticleList()) {
			if(article.getIdArticle() == idArticle) {
				return article;
			} else {
				System.out.println("L'article est introuvable dans cette catégorie");
				
			}
		}
		return null;
	}
	
	public int getIdCategory() {
		return idCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(ArrayList<Article> articleList) {
		this.articleList = articleList;
	}
}
