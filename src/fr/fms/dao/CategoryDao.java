package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fr.fms.bdd.BddConnection;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

public class CategoryDao implements Dao<Category> {
	private Connection connection = BddConnection.getInstance().getConnection();

	/** La méthode create nous permet d'ajouter un objet de type Category dans la bdd
	 * @param user représente l'objet de type Category que l'on veux créer dans la bdd
	 */
	@Override
	public void create(Category category) {
		String request = "INSERT INTO T_Users (CatName, Description) VALUE (?,?);";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, category.getName());
			ps.setString(2, category.getDescription());
			
			if(ps.executeUpdate() == 1) System.out.println("Insertion ok");
		}catch(SQLException e) {
			System.err.print("ERREUR : Insertion impossible ");
			e.printStackTrace();
		}
		
	}

	/** La méthode read nous permet de lire un objet de type Category dans la bdd
	 * @param id représente l'id de l'user que l'on veux lire dans la bdd
	 * @return user qui est l'user que l'on souhaite lire
	 */
	@Override 
	public Category read(int idCategory) {
		ArrayList<Article> articleList = new ArrayList<Article>();
		String firstRequest = "SELECT * FROM T_Articles WHERE IdCategory=?;";
		try(PreparedStatement ps = connection.prepareStatement(firstRequest)) {
			ps.setInt(1, idCategory);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int idArticle = resultSet.getInt(1);
				String description = resultSet.getString(2);
				String brand = resultSet.getString(3);
				double price = resultSet.getDouble(4);
				
				Article article = new Article(idArticle, description, brand, price);
				articleList.add(article);
			}
			
		}catch(SQLException e) {
			System.err.print("ERREUR : Lecture impossible ");
			e.printStackTrace();
			return null;
		}
		
		String request = "SELECT * FROM T_Categories WHERE IdCategory=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setInt(1, idCategory);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				int categoryId = resultSet.getInt("IdCategory");
				String name = resultSet.getString("CatName");
				String description = resultSet.getString("Description");
				
				Category category = new Category(categoryId, name, description, articleList);
				return category;
			}else {
				throw new NoSuchElementException("Aucun utilisateur trouver");
			}
				
		}catch(SQLException e) {
			System.err.print("ERREUR : Lecture impossible ");
			e.printStackTrace();
			return null;
		}
	}

	/** La méthode update nous permet de mettre à jour un objet de type Category dans la bdd
	 * @param article représente l'objet de type Category que l'on veux mettre à jour dans la bdd
	 * @return true si la mise à jour à bien été éffectuer
	 * @return false -> SINON retourne false
	 */
	@Override
	public boolean update(Category category) {
		String request = "UPDATE T_Categories SET CatName=?, Description=? WHERE IdCategory=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, category.getName());
			ps.setString(2, category.getDescription());
			ps.setInt(3, category.getIdCategory());
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Mise à jour ok");
				return true;
			} else {
				System.err.println("ERREUR : La mise à jour a échoué.");
				return false;
			}
		}catch(SQLException e) {
			System.err.print("ERREUR : Mise à jour impossible ");
			e.printStackTrace();
			return false;
		}
	}

	/** La méthode delete nous permet de supprimer un objet de type Category dans la bdd
	 * @param article représente l'objet de type Category que l'on veux supprimer de la bdd
	 * @return true si la suppression à bien été éffectuer
	 * @return false -> SINON retourne false
	 */
	@Override
	public boolean delete(Category category) {
		String request = "DELETE FROM T_Categories WHERE idCategory=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setInt(1, category.getIdCategory());
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Suppression ok");
				return true;
			}else {
				System.err.println("ERREUR : La suppression a échoué.");
				return false;
			}
		}catch(SQLException e) {
			System.err.print("ERREUR : Suppression impossible ");
			e.printStackTrace();
			return false;
		}
	}

	/** La méthode readAll nous permet de lire l'ensemble des objet de type Article dans la bdd
	 * @return articleList est la liste de tous les Articles présents dans la base de donnée
	 */
	@Override
	public ArrayList<Category> readAll() {
		ArrayList<Article> articleList = new ArrayList<Article>();
		ArrayList<Category> categoryList = new ArrayList<Category>();
		String firstRequest = "SELECT * FROM T_Categories;";
		String secondRequest = "SELECT * FROM T_Articles WHERE IdCategory=?;";

		try(PreparedStatement ps = connection.prepareStatement(firstRequest)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int idCategory = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
					
				Category category = new Category(idCategory, name, description, articleList);
				
				try(PreparedStatement ps2 = connection.prepareStatement(secondRequest)) {	
					ps2.setInt(1, idCategory);
					ResultSet resultSet2 = ps2.executeQuery();
					while(resultSet2.next()) {
						int idArticle = resultSet2.getInt(1);
						String descriptionArticle = resultSet2.getString(2);
						String brandArticle = resultSet2.getString(3);
						double priceArticle = resultSet2.getDouble(4);
						
						Article article = new Article(idArticle, descriptionArticle, brandArticle, priceArticle);
						category.getArticleList().add(article);
						
					}
				categoryList.add(category);
				}
			}
			return categoryList;
		}catch(SQLException e) {
			System.err.print("ERREUR : Lecture impossible ");
			e.printStackTrace();
			return null;
		}
		
	}
}
