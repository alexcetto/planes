package planes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by alexandrecetto on 12/12/2015.
 */
public class User {
    String name;
    Byte mdp;
    boolean admin;

    public User(String s, Byte b){
    	name = s;
    	mdp = b;
    }
    
    public User(GlobalConnector gc){
    	Scanner sc = new Scanner(System.in);
		System.out.print("Login : ");
		String usrName = sc.next();
		String usrPass;
		PreparedStatement pstt;
		ResultSet rs;
		
		Connection co = gc.getCo();
		try {
			pstt = co.prepareStatement(
					"SELECT login, password, admin FROM users WHERE login='"+usrName+"'"
					);
			rs = pstt.executeQuery();
			if(rs.next()){
				System.out.print("User found in database\nPassword : ");
				if(sc.next().equals(rs.getString("password"))){
					admin = rs.getBoolean("admin");
					System.out.print("Welcome back ");
					if(admin) System.out.print("Admin ");
					System.out.println(usrName);
				}else{
					System.out.println("Error in authentification !");
				}
            }else{
            	System.out.print("New user - "+usrName+"\nPassword : ");
            	usrPass = sc.next();
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
		
		sc.close();
    }
}
