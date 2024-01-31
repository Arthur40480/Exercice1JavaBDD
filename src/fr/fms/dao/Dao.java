package fr.fms.dao;

import java.sql.Connection;

import fr.fms.entities.Article;
import fr.fms.bdd.*;


public interface Dao {
	public static Connection connection = BddConnection.getConnection();
	public void create(Article article);
	public void delete(Article article);
	public void update(Article article);
	public void read(Article article);
}
