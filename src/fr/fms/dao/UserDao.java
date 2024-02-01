package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fr.fms.bdd.BddConnection;
import fr.fms.entities.User;

public class UserDao implements Dao<User>{
	private Connection connection = BddConnection.getInstance().getConnection();
	
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
	public User read(int idUser) {	// TODO -> ATTENTION DOUBLON !
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
	
	/** La méthode update nous permet de mettre à jour un objet de type User dans la bdd
	 * @param article représente l'objet de type User que l'on veux mettre à jour dans la bdd
	 * @return true si la mise à jour à bien été éffectuer
	 * @return false -> SINON retourne false
	 */
	@Override
	public boolean update(User user) {
		String request = "UPDATE T_Users SET Login=?, Password=? WHERE IdUser=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());
			
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
	
	/** La méthode delete nous permet de supprimer un objet de type User dans la bdd
	 * @param article représente l'objet de type User que l'on veux supprimer dans la bdd
	 * @return true si la suppression à bien été éffectuer
	 * @return false -> SINON retourne false
	 */
	@Override
	public boolean delete(User user) {
		String request = "DELETE FROM T_Users WHERE IdUser=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setInt(1, user.getId());
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Suppression ok");
				return true;
			} else {
				System.err.println("ERREUR : La suppression a échoué.");
				return false;
			}
		}catch(SQLException e) {
			System.err.print("ERREUR : Suppression impossible ");
			e.printStackTrace();
			return false;
		}
	}
	
	/** La méthode readAll nous permet de lire l'ensemble des objet de type User dans la bdd
	 * @return articleList est la liste de tous les Users présents dans la base de donnée
	 */
	@Override
	public ArrayList<User> readAll() {
		ArrayList<User> userList = new ArrayList<>();
		String request = "SELECT * FROM T_Users;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int userId = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				
				User user = new User(userId, login, password);
				userList.add(user);
			}
			return userList;
		}catch(SQLException e) {
			System.err.print("ERREUR : Lecture impossible ");
			e.printStackTrace();
			return null;
		}
	}
	
	/** La méthode userExists nous permet de vérifier si un user exist via un login et un password
	 * @param login qui représente le login que l'on vas vérifier dans la bdd
	 * @param password qui représente le password que l'on vas vérifier dans la bdd
	 * @return boolean true si le login et password sont trouvé, SINON false;
	 */
	public boolean userExists(String login, String password) {
		String request = "SELECT * FROM T_Users WHERE Login=? AND Password=?;";
		try(PreparedStatement ps = connection.prepareStatement(request)) {
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				System.out.println("Authentification réussie !");
				return true;
			} else {
				System.out.println("Accès refusé !");
				return false;
			}
		}catch(SQLException e) {
			System.err.print("ERREUR : Verification Impossible ");
			e.printStackTrace();
			return false;
		}
	}
}