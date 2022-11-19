package Oier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class pruebaMariaDB {
	//erabili singleton txantiloi diseinua
	private static pruebaMariaDB instantzia;
	public static pruebaMariaDB getInstance(){
		if(instantzia == null) {
			instantzia = new pruebaMariaDB();
		}
		return instantzia;
	}

	// init database konstanteak
	private static final String DATABASE_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mariadb://kasserver.synology.me:3307/ethazi";
	private static final String USERNAME = "gp1";
	private static final String PASSWORD = "ZBlrkPWaSdVs5F3l";
	private static final String MAX_POOL = "250";

	// konexioa
	private Connection konexioa;

	private Properties propietateak;


	private Properties getProperties() {
		if(propietateak == null) {
			propietateak = new Properties();
			propietateak.setProperty("user", USERNAME);
			propietateak.setProperty("password", PASSWORD);
			propietateak.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return propietateak;
	}

	// konektatu datubasera

	public Connection konektatu() {
		if (konexioa == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				konexioa =  DriverManager.getConnection(DATABASE_URL, getProperties());
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Konexioa ezin izan da burutu.");
				e.printStackTrace();
			}
		}
		return konexioa;
	}

	// datubasetik deskonektatu
	public void deskonektatu() {
		if(konexioa != null) {
			try {
				konexioa.close();
				konexioa = null;
			} catch (SQLException e) {
				System.err.println("Ezin izan da deskonektatu.");
				e.printStackTrace();
			}
		}
	}    

}