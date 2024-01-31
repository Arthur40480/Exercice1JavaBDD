package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.fms.entities.Article;

public class IDaoImpl implements Dao {
	
	
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
			System.out.println("ERREUR : Insertion impossible");
			e.printStackTrace();
		}
		
	}
	
	/** La méthode delete nous permet de supprimer un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux supprimer de la bdd
	 */
	@Override
	public void delete(Article article) {
		try(Statement statement = connection.createStatement()) {
			String request = "DELETE FROM T_Articles WHERE idArticle = '" + article.getIdArticle() +"'";
			int row = statement.executeUpdate(request);
			if(row >= 0) System.out.println("Supression ok");
		}catch(SQLException e) {
			System.out.println("ERREUR : Supression Impossible");
			e.printStackTrace();
		}
		
	}
	
	/** La méthode update nous permet de mettre à jour un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux mettre à jour dans la bdd
	 */
	@Override
	public void update(Article article) {
		String request = "UPDATE T_Articles SET description=?, brand=?, unitaryPrice=? WHERE idArticle=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());
			ps.setInt(4, article.getIdArticle());
			
			if(ps.executeUpdate() > 0) System.out.println("Mise à jour ok");

		}catch(SQLException e) {
			System.out.println("ERREUR : Mise à jour impossible");
			e.printStackTrace();
		}
		
	}
	
	/** La méthode read nous permet de lire un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux lire dans la bdd
	 */
	@Override
	public void read(Article article) {
		String request = "SELECT * FROM T_Articles WHERE idArticle=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setInt(1, article.getIdArticle());
			
			try(ResultSet resultSet = ps.executeQuery()) {
				if(resultSet.next()) {
					int id = resultSet.getInt("idArticle");
					String description = resultSet.getString("description");
					String brand = resultSet.getString("brand");
					double price = resultSet.getDouble("unitaryPrice");
					
					System.out.println("Article ID: " + id);
					System.out.println("Description: " + description);
					System.out.println("Brand: " + brand); 
					System.out.println("Price: " + price);
				}else {
					System.out.println("Article introuvable");
				}
			}
		}catch(SQLException e) {
			System.out.println("ERREUR : Lecture impossible");
			e.printStackTrace();
		}
		
	}

}
