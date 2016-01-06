package planes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		GlobalConnector gc = new GlobalConnector();
		
		Connection co = gc.getCo();
	
		try {
			PreparedStatement stt = co.prepareStatement("SELECT mfr, model FROM planes WHERE mfr LIKE '%Airbus%'");
			ResultSet rs = stt.executeQuery();
			while ( rs.next() ) {
                String mfr = rs.getString( "mfr" );
                String model = rs.getString( "model" );
                System.out.println(mfr +", " + model);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}