package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fr.fms.entities.User;

public class UserDao implements Dao<User>{
	
	/** La méthode create nous permet d'ajouter un objet de type User dans la bdd
	 * @param user représente l'objet de type User que l'on veux créer dans la bdd
	 */
	@Override
	public void create(User user) {
		String request = "INSERT INTO T_Users (Login, Password) VALUE (?,?);";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			
			if(ps.executeUpdate() == 1) System.out.println("Insertion ok");
		}catch(SQLException e) {
			System.err.print("ERREUR : Insertion impossible ");
			e.printStackTrace();
		}
		
	}
	
	/** La méthode read nous permet de lire un objet de type User dans la bdd
	 * @param idUser représente l'id de l'user que l'on veux lire dans la bdd
	 * @return user qui est l'user que l'on souhaite lire
	 */
	@Override
	public User read(int idUser) {
		String request = "SELECT * FROM T_Users WHERE IdUser=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setInt(1, idUser);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				int userId = resultSet.getInt("IdUser");
				String login = resultSet.getString("Login");
				String password = resultSet.getString("Password");
				
				User user = new User(userId, login, password);
				return user;
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
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<User> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
