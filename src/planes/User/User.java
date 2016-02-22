package planes.User;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import planes.Plane;

/**
 * Class User
 */
public class User {
    private String name;
    private String mdp;
    private boolean admin;
	private ObservableList<Plane> panier;


    public User(String login, String password, boolean isAdmin){
    	name = login;
    	mdp = password;
    	admin = isAdmin;
    	panier = FXCollections.observableArrayList();
    }
    
    public User(GlobalConnector gc, String usrName, String usrPass){
		PreparedStatement pstt;
		ResultSet rs;
		name = usrName;
		mdp = usrPass;
		panier = FXCollections.observableArrayList();

		Connection co = gc.getCo();
		try {
			pstt = co.prepareStatement(
					"SELECT login, password, admin FROM users WHERE login='"+usrName+"'"
					);
			rs = pstt.executeQuery();
			if(rs.next()){
				if(usrPass.equals(rs.getString("password"))) admin = rs.getBoolean("admin");
				else{
					System.out.println("Identifiant trouv�, mauvais mot de passe !");
					name = null;
				}
            }else{
            	pstt = co.prepareStatement(
            			"INSERT INTO users VALUES('"+usrName+"','"+usrPass+"', 0)"
            			);
            	rs = pstt.executeQuery();
            	System.out.println("Cr�ation d'un nouvel utilisateur !");
            }
		} catch (SQLException e) {	e.printStackTrace(); }
    }
    
    public boolean isAdmin(){
    	return admin;
    }
    
    public String getName(){
    	return name;
    }

	public String getPassword(){
		return "****";
	}

	public String getAdmin(){
		return admin?"Administrator":"User";
	}


	public void addPlaneToBasket(ObservableList<Plane> p){
		this.panier.addAll(p);
    }

	public void removePlaneToBasket(ObservableList<Plane> p){
		this.panier.removeAll(p);
	}
    
    public ObservableList<Plane> getBasket(){
    	return panier;
    }

	public void removeBasket(){
		panier.clear();
	}

	public void removeUser(User user){
		GlobalConnector gc = new GlobalConnector();
		Connection co = gc.getCo();
		PreparedStatement pstt;
		ResultSet rs;

		try {
			pstt = co.prepareStatement("DELETE FROM users WHERE `login`='"+ user.getName() + "';");
			rs = pstt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		rs = null;
		pstt = null;
		gc = null;
	}

	public void modifyUserName(String oldName, String newName){
		GlobalConnector gc = new GlobalConnector();
		Connection co = gc.getCo();
		PreparedStatement pstt;
		ResultSet rs;

		try {
			pstt = co.prepareStatement("UPDATE users SET `login`='"+ newName + "' WHERE `login`='"+ oldName + "';");
			rs = pstt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		rs = null;
		pstt = null;
		gc = null;
	}

}
