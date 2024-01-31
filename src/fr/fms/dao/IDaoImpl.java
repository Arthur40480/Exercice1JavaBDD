package fr.fms.dao;

import java.sql.SQLException;
import java.sql.Statement;

import fr.fms.entities.Article;

public class IDaoImpl implements Dao {
	
	@Override
	/** La méthode create nous permet d'ajouter un objet de type Article dans la bdd
	 * @param article représente l'objet de type Article que l'on veux créer dans la bdd
	 */
	public void create(Article article) {
		try(Statement statement = connection.createStatement()) {
			String request = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice)"
							+ " VALUES ( '" + article.getDescription() + "', '" + article.getBrand() + "', '" + article.getPrice() + "');";
			// Méthode executeUpdate utilisé pour exécuter une instruction SQL qui modifie les données d'une bdd
			// Généralement utilisée pour des instructions SQL telles que INSERT, UPDATE, DELETE qui modifient les enregistrement d'une table
			// Elle prend en param un string et retourne un entier qui représente le nombre de lignes afféctées par l'éxecution de l'instruction
			int row = statement.executeUpdate(request); 
			if(row == 1) System.out.println("Insertion ok");
		}catch(SQLException e) {
			System.out.println("ERREUR : Insertion impossible");
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Article article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Article article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Article article) {
		// TODO Auto-generated method stub
		
	}

}
