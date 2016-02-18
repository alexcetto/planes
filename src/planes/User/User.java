package planes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import planes.Plane;

/**
 * Created by alexandrecetto on 12/12/2015.
 */
public class User {
    private String name;
    private String mdp;
    private boolean admin;
    private ArrayList<Plane> panier;

    public User(String login, String password, boolean isAdmin){
    	name = login;
    	mdp = password;
    	admin = isAdmin;
    	panier = new ArrayList<>();
    }
    
    public User(GlobalConnector gc, String usrName, String usrPass){
		PreparedStatement pstt;
		ResultSet rs;
		name = usrName;
		mdp = usrPass;

		Connection co = gc.getCo();
		try {
			pstt = co.prepareStatement(
					"SELECT login, password, admin FROM users WHERE login='"+usrName+"'"
					);
			rs = pstt.executeQuery();
			if(rs.next()){
				if(usrPass.equals(rs.getString("password"))) admin = rs.getBoolean("admin");
				else{
					System.out.println("Identifiant trouvé, mauvais mot de passe !");
					name = null;
				}
            }else{
            	pstt = co.prepareStatement(
            			"INSERT INTO users VALUES('"+usrName+"','"+usrPass+"', 0)"
            			);
            	rs = pstt.executeQuery();
            	System.out.println("Création d'un nouvel utilisateur !");
            }
		} catch (SQLException e) {	e.printStackTrace(); }
    }
    
    public boolean isAdmin(){
    	return admin;
    }
    
    public String getName(){
    	return name;
    }
    
    public void add(Plane plane){
    	panier.add(plane);
    }
    
    public ArrayList<Plane> getPanier(){
    	return panier;
    }
}
