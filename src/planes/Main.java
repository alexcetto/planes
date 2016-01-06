package planes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		GlobalConnector gc = new GlobalConnector();
		//Connection co = gc.getCo();
		
		User usr = new User(gc);
		
	}
}