package fr.fms.bdd;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

// Utilisation du PATERN SINGLETON
// On garantit que la classe BddConnection n'aura qu'une seule instance
// - On évite les problèmes liés à la création de multiples instances
// - On contribue à optimiser l'utilisation de la mémoire et des ressources système
// - On simplifie la gestion des ressources

public class BddConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/shop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "ArthurGibertTosse40230";
    
    // On déclare l'instance unique:
    private static BddConnection instance = null;
    
    // On laisse le constructeur en privé et vie pour s'assurer que personne d'autre
    // en dehors de la classe ne puisse instancier directement un nouvel obj BddConnection
    private BddConnection() {
    	
    }
    
    /**
     * La méthode getInstance garantit qu'il n'y aura qu'une seule instance de notre classe BddConnection
     * @return instance qui est l'objet de type BddConnection
     */
    public static BddConnection getInstance() {
    	if(instance == null) {
    		instance = new BddConnection();
    	}
    	return instance;
    }
    
    /**
     * La méthode getConnection nous permet d'établir une connection à notre base de donnée
     * @return Connection qui est l'objet qui représente cette connexion établie
     */
    public Connection getConnection(){
    	try {
    		return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    	}catch(SQLException e) {
    		System.err.print("ERREUR : Connexion impossible ");
    		e.printStackTrace();
    		return null;
    	}      
    }
}