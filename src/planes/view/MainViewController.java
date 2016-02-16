package planes.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import planes.Plane;
import planes.Criterion.*;
import planes.User.GlobalConnector;
import planes.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controls the main application screen
 */
public class MainViewController {
    @FXML private Button logoutButton;
    @FXML private Label sessionLabel;
    @FXML private TableView<Plane> tableProducts;
    @FXML private Button adminButton;
    @FXML private Button submitCriterion;
    @FXML private TextField modelInput;
    @FXML private TextField mfrInput;
    @FXML private ChoiceBox<Integer> engine_nbInput;
    @FXML private TextField speedInput;
    @FXML private ChoiceBox<String> weightInput;
    @FXML private ChoiceBox<String> engineInput;
    @FXML private ChoiceBox<String> aircraftTypeInput;

    
    private ObservableList<Plane> data = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public void initSessionID(final LoginManager loginManager, User user) {
		if(user.isAdmin())
			adminButton.setVisible(true);
        sessionLabel.setText(user.getName());
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });

        tableProducts.setEditable(true);

        TableColumn <Plane,Criteria> mfr = new TableColumn<>("Manufacturer");
        mfr.setMinWidth(150);
        mfr.setCellValueFactory(
                new PropertyValueFactory<>("manufacturer"));

        TableColumn <Plane,Criteria> model = new TableColumn<>("Model");
        model.setMinWidth(100);
        model.setCellValueFactory(
                new PropertyValueFactory<>("model"));

        TableColumn <Plane,Criteria> engine = new TableColumn<>("Engine");
        engine.setMinWidth(100);
        engine.setMaxWidth(100);
        engine.setCellValueFactory(
                new PropertyValueFactory<>("engine"));

        TableColumn <Plane,Criteria> type_aircraft = new TableColumn<>("Type Aircraft");
                engine.setMinWidth(100);
                engine.setMaxWidth(100);
                engine.setCellValueFactory(
                        new PropertyValueFactory<>("type_aircraft"));

        TableColumn <Plane,Criteria> engine_nb = new TableColumn<>("Number");
        engine_nb.setMinWidth(100);
        engine_nb.setMaxWidth(100);
        engine_nb.setCellValueFactory(
                new PropertyValueFactory<>("engine_nb"));

        TableColumn <Plane,Criteria> capacity = new TableColumn<>("Capacity");
        capacity.setMinWidth(75);
        capacity.setMaxWidth(75);
        capacity.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));

        TableColumn <Plane,Criteria> weight = new TableColumn<>("Weight");
        weight.setMinWidth(50);
        weight.setMaxWidth(50);
        weight.setCellValueFactory(
                new PropertyValueFactory<>("weight"));

        TableColumn <Plane,Criteria> speed = new TableColumn<>("Speed");
        speed.setMinWidth(50);
        speed.setMaxWidth(50);
        speed.setCellValueFactory(
                new PropertyValueFactory<>("speed"));

        TableColumn <Plane,Criteria> price = new TableColumn<>("Price");
        price.setMinWidth(50);
        price.setMaxWidth(50);
        price.setCellValueFactory(
                new PropertyValueFactory<>("price"));


        adminButton.setOnAction((ActionEvent e) -> {
            //			OUVERTURE D'UNE FENETRE DE MENU
        	//			GESTION BDD
        	//			GESTION USERS
        });
        
        submitCriterion.setOnAction((ActionEvent e) -> {
            GlobalConnector gc = new GlobalConnector();
            Connection co = gc.getCo();
            PreparedStatement pstt;
            ResultSet rs;
            
			//            RECUPERATION DES CHOIX DE TYPE AVION
			//            CREATION OBJETS CLASSES CHOISIES
            //			  A REFAIRE ! ATTENTION !
            
            Plane userPlane = new Plane(
            		new Manufacturer(mfrInput.getText()),
            		new Model(modelInput.getText()),
                    new AircraftType(aircraftTypeInput.getValue()),
        			new Engine(engineInput.getValue()),
        			new Engine_nb(engine_nbInput.getValue()==null?0:engine_nbInput.getValue()),
        			new Capacity(500, 1000),
        			new Weight(weightInput.getValue()),
        			new Speed(speedInput.getText().equals("")?0:Integer.parseInt(speedInput.getText())),
        			new Price(0, 1000000)
        		);

            try {
                pstt = co.prepareStatement( "SELECT * FROM plane " + userPlane.getStatement() + ";" );
                rs = pstt.executeQuery();
                if(rs.next()){
                	int i=0;
                    while (rs.next()){
                        Plane compared = new Plane(
                        		new Manufacturer(rs.getString("manufacturer")),
                        		new Model(rs.getString("model")),
                                new AircraftType(rs.getInt("type_aircraft")),
                    			new Engine(rs.getInt("type_engine")),
                    			new Engine_nb(rs.getInt("number_engine")),
                    			new Capacity(rs.getInt("number_seats")),
                    			new Weight(rs.getString("aircraft_weight")),
                    			new Speed(rs.getInt("speed")),
                    			new Price(rs.getInt("price"))
                    		);
                        int score = compared.evaluate(userPlane);
                        //		TRAITEMENT EN FONCTION DU RETURN
                        if(score > 50){
                        	data.add(compared);
                        	i++;
                        }
                    }
                    System.out.println(i + " résultats affichés.");
                }else{
                    System.out.println("Requ�te �chou�e !");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        tableProducts.setItems(data);
        tableProducts.getColumns().addAll(model, mfr, type_aircraft, engine, engine_nb, capacity, speed, weight, price);
        
    }
}