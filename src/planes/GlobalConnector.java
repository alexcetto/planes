package planes;

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
	        String url = "jdbc:mysql://mysql5-public.web4all.fr:3306/133348_planes";
	        String utilisateur = "133348_planes";
	        String motDePasse = "I like to fly !";
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
	
	/*
    public GlobalConnector(){

        // Chargement du driver JDBC pour MySQL
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch ( ClassNotFoundException e ) {
            // Gérer les éventuelles erreurs ici
            System.out.println("e = " + e);
        }
        Connection conn = null;
        String url = "jdbc:mysql://mysql5-public.web4all.fr:3306/133348_planes";
        String utilisateur = "133348_planes";
        String motDePasse = "I like to fly !";
        try {
            conn = DriverManager.getConnection( url, utilisateur, motDePasse );
            if(conn != null) System.out.println("Connexion établie : "+conn);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException in Connection: " + ex.getMessage());
            System.out.println("SQLStatein Connection: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            // UTILISER PREPAREDSTATEMENT POUR PLUS DE SECU
            rs = stmt.executeQuery("SELECT mfr, model FROM plane WHERE mfr LIKE '%Airbus%'");

            while ( rs.next() ) {
                String mfr = rs.getString( "mfr" );
                String model = rs.getString( "model" );
                System.out.println(mfr +", " + model);
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException in Statement: " + ex.getMessage());
            System.out.println("SQLState in Statement: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    System.out.println("Explosion : " + sqlEx);
                }

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println("Explosion : " + sqlEx);
                }

                stmt = null;
            }
        }
    }*/
}
