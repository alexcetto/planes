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
    @FXML private Button adminButtonUser;
    @FXML private Button adminButtonData;
    @FXML private Button reserveButton;
    @FXML private Button purchasesButton;
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
		if(user.isAdmin()){
			adminButtonUser.setVisible(true);
			adminButtonData.setVisible(true);
		}else{
			reserveButton.setVisible(true);
			purchasesButton.setVisible(true);
		}
        sessionLabel.setText(user.getName());
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });

        tableProducts.setEditable(true);

        TableColumn <Plane,Criteria> match = new TableColumn<>("%Match");
        match.setMinWidth(60);
        match.setMaxWidth(60);
        match.setCellValueFactory(
                new PropertyValueFactory<>("match"));

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


        adminButtonUser.setOnAction((ActionEvent e) -> {
        	loginManager.showUserManageView();
        });
        
        adminButtonData.setOnAction((ActionEvent e) -> {
        	loginManager.showDataManageView();
        });
        
        reserveButton.setOnAction((ActionEvent e) -> {
//        	FOREACH SELECTED PLANE -> GOTO USER PANIER
//        	user.add(selectedPlanes)
        });
        
        purchasesButton.setOnAction((ActionEvent e) -> {
//        	NEW WINDOW --> DISPLAY USER PANIER
//        	DISPLAY(user.getPanier())
        });
        
        submitCriterion.setOnAction((ActionEvent e) -> {
        	data.clear();
            GlobalConnector gc = new GlobalConnector();
            Connection co = gc.getCo();
            PreparedStatement pstt;
            ResultSet rs;
            
//            		RECUP IHM
//          String statement = typeAircraft
//          RELATION BDD ---> NOS CLASSES?
            String statement = "";
            
            Plane userPlane = new Plane(
<<<<<<< HEAD
            		new Manufacturer(mfrInput.getText().toUpperCase()),
            		new Model(modelInput.getText().toUpperCase()),
=======
            		new Manufacturer(mfrInput.getText()),
            		new Model(modelInput.getText()),
                    new AircraftType(aircraftTypeInput.getValue()),
>>>>>>> 58ca43b96bec56222962fd39f69a598ea7b94bac
        			new Engine(engineInput.getValue()),
        			new Engine_nb(engine_nbInput.getValue()==null?0:engine_nbInput.getValue()),
        			//		RECUP IHM
        			new Capacity(0),
        			new Weight(weightInput.getValue()),
        			new Speed(speedInput.getText().equals("")?0:Integer.parseInt(speedInput.getText())),
<<<<<<< HEAD
        			//		RECUP IHM
        			new Price(0)
=======
        			new Price(0, 1000000)
>>>>>>> 58ca43b96bec56222962fd39f69a598ea7b94bac
        		);

            try {
                pstt = co.prepareStatement( "SELECT * FROM plane " + statement + ";" );
                rs = pstt.executeQuery();
                if(rs.next()){
                	int i=0;
                    while (rs.next()){
                        Plane compared = new Plane(
                        		new Manufacturer(rs.getString("manufacturer")),
                        		new Model(rs.getString("model")),
<<<<<<< HEAD
=======
                                new AircraftType(rs.getInt("type_aircraft")),
>>>>>>> 58ca43b96bec56222962fd39f69a598ea7b94bac
                    			new Engine(rs.getInt("type_engine")),
                    			new Engine_nb(rs.getInt("number_engine")),
                    			new Capacity(rs.getInt("number_seats")),
                    			new Weight(rs.getString("aircraft_weight")),
                    			new Speed(rs.getInt("speed")),
                    			new Price(rs.getInt("price"))
                    		);
                        compared.evaluate(userPlane);
                        //		TRAITEMENT EN FONCTION DU RETURN
                        if(compared.getMatch() > 50){
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
<<<<<<< HEAD
        tableProducts.getColumns().addAll(match, model, mfr, engine, engine_nb, capacity, speed, weight, price);
=======
        tableProducts.getColumns().addAll(model, mfr, type_aircraft, engine, engine_nb, capacity, speed, weight, price);
>>>>>>> 58ca43b96bec56222962fd39f69a598ea7b94bac
        
    }
}