package fr.fms.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateConfigFile {
	public static void main(String[] args) {
		Properties properties = new Properties();
		FileOutputStream fileOutputStream = null;
		
		try {
            properties.setProperty("db.driver.class", "org.mariadb.jdbc.Driver");
            properties.setProperty("db.url", "jdbc:mariadb://localhost:3306/Shop");
            properties.setProperty("db.login", "root");
            properties.setProperty("db.password", "ArthurGibertTosse40230");
            
            fileOutputStream = new FileOutputStream("config.properties");
            properties.store(fileOutputStream, "Fichier de Configuration");
            System.out.println("Fichier de configuration créer avec succès");
            
		}catch(IOException e) {
			System.err.println("Une erreur s'st produite lors de la création du fichier de configuration");
			e.printStackTrace();
		}finally {
			if(fileOutputStream != null) {
				try {
					fileOutputStream.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
