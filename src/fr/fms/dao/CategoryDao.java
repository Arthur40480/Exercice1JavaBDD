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

	@Override
	public boolean update(Category obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Category obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
