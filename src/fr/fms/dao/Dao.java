package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.fms.bdd.*;

public interface Dao<T> {
	public void create(T obj);
	public T read(int id);
	public boolean update(T obj);
	public boolean delete(T obj);
	public ArrayList<T> readAll();
}
