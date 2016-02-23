package planes.User;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import planes.Plane;
import planes.Criterion.Capacity;
import planes.Criterion.Engine;
import planes.Criterion.Engine_nb;
import planes.Criterion.Manufacturer;
import planes.Criterion.Model;
import planes.Criterion.Price;
import planes.Criterion.Speed;
import planes.Criterion.Type;
import planes.Criterion.Weight;

/**
 * Class User
 */
public class User {
    private String name;
    private String mdp;
    private boolean admin;
	private ObservableList<Plane> panier;

//	public static final int MAX_PANIER = 10;

    public User(String login, String password, boolean isAdmin){
    	name = login;
    	mdp = password;
    	admin = isAdmin;
    	panier = FXCollections.observableArrayList();
    }
    
    @SuppressWarnings("resource")
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
			if(rs.next()){		//	Utilisateur trouvé
				if(usrPass.equals(rs.getString("password"))){	//	Bon mot de passe
					admin = rs.getBoolean("admin");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Connexion");
					alert.setHeaderText(null);
					alert.setContentText("Welcome back "+usrName+"... connecting...");
					alert.show();
					
					//		Recupération du panier
					pstt = co.prepareStatement(
							"SELECT model FROM panier WHERE login ='"+usrName+"'"
							);
					rs = pstt.executeQuery();
					while(rs.next()){
						PreparedStatement pstt2;
						ResultSet rs2;
						
						pstt2 = co.prepareStatement(
								"SELECT * FROM plane WHERE model='"+rs.getString("model")+"'"
								);
						rs2 = pstt2.executeQuery();
						if(rs2.next()){
							panier.add(
									new Plane(
		                    		new Manufacturer(rs2.getString("manufacturer")),
		                    		new Model(rs2.getString("model")),
		                            new Type(rs2.getInt("type_aircraft")),
		                			new Engine(rs2.getInt("type_engine")),
		                			new Engine_nb(rs2.getInt("number_engine")),
		                			new Capacity(rs2.getInt("number_seats")),
		                			new Weight(rs2.getString("aircraft_weight")),
		                			new Speed(rs2.getInt("speed")),
		                			new Price(rs2.getInt("price"))
		                		));
						}
					}
				}
				else{			//	Mauvais mot de passe
					System.out.println("Identifiant trouvï¿½, mauvais mot de passe !");
					name = null;
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Wrong password");
					alert.setHeaderText(null);
					alert.setContentText("Wrong password entered !");
					alert.show();
				}
            }else{				//	Nouvel utilisateur
            	pstt = co.prepareStatement(
            			"INSERT INTO users VALUES('"+usrName+"','"+usrPass+"', 0)"
            			);
            	rs = pstt.executeQuery();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New user");
				alert.setHeaderText(null);
				alert.setContentText("Creation of a new user... connecting...");
				alert.show();
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
		GlobalConnector gc = new GlobalConnector();
		PreparedStatement pstt;
		ResultSet rs;
		Connection co = gc.getCo();
		
		for(Plane plane : p){
			try{
				pstt = co.prepareStatement(
		    			"INSERT INTO panier VALUES ('"+this.name+"','"+plane.getModel()+"')"
		    			);
		    	rs = pstt.executeQuery();
				this.panier.add(plane);
			}catch(SQLException e){
				System.out.println("Not inserted");
			}
		}
    }

	public void removePlaneToBasket(ObservableList<Plane> p){
		GlobalConnector gc = new GlobalConnector();
		PreparedStatement pstt;
		ResultSet rs;
		Connection co = gc.getCo();
		
		for(Plane plane : p){
			try{
				pstt = co.prepareStatement(
		    			"DELETE FROM panier WHERE `login`='"+ this.name + "' AND `model`='"+ plane.getModel() +"'"
		    			);
		    	rs = pstt.executeQuery();
				this.panier.remove(plane);
			}catch(SQLException e){
				System.out.println("Not deleted");
			}
		}
	}
    
    public ObservableList<Plane> getBasket(){
    	return panier;
    }

	public void removeBasket(){
		panier.clear();
	}

	public void removeUser(User user){
		if(user!=null){
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
	
	public void modifyAdmin(String id, String oldStatus, String newStatus){
		if(!name.equals(id)){
			GlobalConnector gc = new GlobalConnector();
			Connection co = gc.getCo();
			PreparedStatement pstt;
			ResultSet rs;
			int isAdmin;
			
			if(newStatus.equals("User"))
				isAdmin = 0;
			else
				isAdmin = 1;
			
			try {
				pstt = co.prepareStatement("UPDATE users SET `admin`='"+ isAdmin + "' WHERE `login`='"+ id + "';");
				rs = pstt.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}
			rs = null;
			pstt = null;
			gc = null;
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Can not change your status");
			alert.setHeaderText(null);
			alert.setContentText("You can not change your status");
			alert.showAndWait();
		}
	}
}
