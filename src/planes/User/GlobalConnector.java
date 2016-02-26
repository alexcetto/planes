package planes.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GlobalConnector {
	
	private static Connection conn = null;

	public Connection getCo(){
		if(conn != null)
			return conn;
		else{
			// Chargement du driver JDBC pour MySQL
	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	        } catch ( ClassNotFoundException e ) {
	            // Gérer les éventuelles erreurs ici.
	            System.out.println("e = " + e);
	        }
	        Connection conn = null;
	        String url = "jdbc:mysql://thedatabasewebsite.Com:3306/nameofthedatabase";
	        String utilisateur = "username";
	        String motDePasse = "password";
	        try {
	            conn = DriverManager.getConnection( url, utilisateur, motDePasse );
	            //if(conn != null) System.out.println("Connexion établie : "+conn);
	        } catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException in Connection: " + ex.getMessage());
	            System.out.println("SQLStatein Connection: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        }			
			return conn;
		}
	}
}
