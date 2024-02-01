package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class IDaoImpl implements Dao<Article> {
	
	/** La méthode create nous permet d'ajouter un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux créer dans la bdd
	 */
	@Override
	public void create(Article article) {
		String request = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES (?,?,?);";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());
			
			if(ps.executeUpdate() == 1) System.out.println("Insertion ok");
		}catch(SQLException e) {
			System.err.print("ERREUR : Insertion impossible ");
			e.printStackTrace();
		}
		
	}
	
	/** La méthode read nous permet de lire un objet de type Article dans la bdd
	 * @param id représente l'id de l'article que l'on veux lire dans la bdd
	 * @return article qui est l'article que l'on souhaite lire
	 */
	@Override
	public Article read(int id) {
		String request = "SELECT * FROM T_Articles WHERE idArticle=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setInt(1, id);
			
			try(ResultSet resultSet = ps.executeQuery()) {
				if(resultSet.next()) {
					int idSearch = resultSet.getInt("idArticle");
					String description = resultSet.getString("description");
					String brand = resultSet.getString("brand");
					double price = resultSet.getDouble("unitaryPrice");
					
					Article article = new Article(idSearch, description, brand, price);
					return article;

				}else {
					System.out.println("Article introuvable");
					return null;
				}
			}
		}catch(SQLException e) {
			System.err.print("ERREUR : Lecture impossible ");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/** La méthode update nous permet de mettre à jour un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux mettre à jour dans la bdd
	 * @return true si la mise à jour à bien été éffectuer
	 * @return false -> SINON retourne false
	 */
	@Override
	public boolean update(Article article) {
		String request = "UPDATE T_Articles SET description=?, brand=?, unitaryPrice=? WHERE idArticle=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());
			ps.setInt(4, article.getIdArticle());
			
			if(ps.executeUpdate() > 0) System.out.println("Mise à jour ok");
			return true;

		}catch(SQLException e) {
			System.err.print("ERREUR : Mise à jour impossible ");
			e.printStackTrace();
			return false;
		}
		
	}
	
	/** La méthode delete nous permet de supprimer un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux supprimer de la bdd
	 * @return true si la supression à bien été éffectuer
	 * @return false -> SINON retourne false
	 */
	@Override
	public boolean delete(Article article) {
		try(Statement statement = connection.createStatement()) {
			String request = "DELETE FROM T_Articles WHERE idArticle = '" + article.getIdArticle() +"'";
			int row = statement.executeUpdate(request);
			if(row >= 0) System.out.println("Supression ok");
			return true;
		}catch(SQLException e) {
			System.err.print("ERREUR : Supression impossible ");
			e.printStackTrace();
			return false;
		}
		
	}
	
	/** La méthode readAll nous permet de lire l'ensemble des objet de type Article dans la bdd
	 * @return articleList est la liste de tous les Articles présents dans la base de donnée
	 */
	@Override
	public ArrayList<Article> readAll() {
		ArrayList<Article> articleList = new ArrayList<>();
		String request = "SELECT * FROM T_Articles;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int idSearch = resultSet.getInt(1);
				String description = resultSet.getString(2);
				String brand = resultSet.getString(3);
				double price = resultSet.getDouble(4);
					
				Article article = new Article(idSearch, description, brand, price);
				articleList.add(article);				
			}
			return articleList;
		}catch(SQLException e) {
			System.err.print("ERREUR : Lecture impossible ");
			e.printStackTrace();
			return null;
		}
		
	}
}
