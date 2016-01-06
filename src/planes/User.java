package planes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexandrecetto on 12/12/2015.
 */
public class User {
    String name;
    String mdp;
    boolean admin;

    public User(String login, String password, boolean isAdmin){
    	name = login;
    	mdp = password;
    	admin = isAdmin;
    }
    
    public User(GlobalConnector gc){
		String usrName, usrPass;
		PreparedStatement pstt;
		ResultSet rs;

		System.out.print("\n\nLogin : ");
		Connection co = gc.getCo();
		usrName = Main.sc.next();
		try {
			pstt = co.prepareStatement(
					"SELECT login, password, admin FROM users WHERE login='"+usrName+"'"
					);
			rs = pstt.executeQuery();
			if(rs.next()){
				System.out.print("User found in database\nPassword : ");
				if(Main.sc.next().equals(rs.getString("password"))){
					admin = rs.getBoolean("admin");
					System.out.print("Welcome back ");
					if(admin) System.out.print("Admin ");
					System.out.println(usrName);
				}else{
					System.out.println("Error in authentification !");
				}
            }else{
            	System.out.print("New user - "+usrName+"\nPassword : ");
            	usrPass = Main.sc.next();
            	pstt = co.prepareStatement(
            			"INSERT INTO users VALUES('"+usrName+"','"+usrPass+"')"
            			);
            	rs = pstt.executeQuery();
            	System.out.println("Welcome !");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean isAdmin(){
    	return admin;
    }
}
