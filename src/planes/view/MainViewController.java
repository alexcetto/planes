package planes.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    @FXML private Button adminAddButton;
    @FXML private Button adminDeleteButton;
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
    @FXML private TextField maxPriceInput;
    @FXML private TextField minPriceInput;

    
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
                user.removeBasket();
                loginManager.logout();
            }
        });

        tableProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn <Plane,String> match = new TableColumn<>("%Match");
	        match.setMinWidth(60);
	        match.setMaxWidth(60);
	        match.setCellValueFactory(
	                new PropertyValueFactory<>("match"));

        TableColumn <Plane,String> mfr = new TableColumn<>("Manufacturer");
	        mfr.setMinWidth(150);
	        mfr.setCellValueFactory(
	                new PropertyValueFactory<>("manufacturer"));

        TableColumn <Plane,String> model = new TableColumn<>("Model");
	        model.setMinWidth(100);
	        model.setCellValueFactory(
	                new PropertyValueFactory<>("model"));
	        
        TableColumn <Plane,String> type_aircraft = new TableColumn<>("Type Aircraft");
	        type_aircraft.setMinWidth(100);
	        type_aircraft.setMaxWidth(100);
	        type_aircraft.setCellValueFactory(
                        new PropertyValueFactory<>("type_aircraft"));

        TableColumn <Plane,String> engine = new TableColumn<>("Engine");
	        engine.setMinWidth(100);
	        engine.setMaxWidth(100);
	        engine.setCellValueFactory(
	                new PropertyValueFactory<>("engine"));


        TableColumn <Plane,String> engine_nb = new TableColumn<>("Number");
	        engine_nb.setMinWidth(100);
	        engine_nb.setMaxWidth(100);
	        engine_nb.setCellValueFactory(
	                new PropertyValueFactory<>("engine_nb"));

        TableColumn <Plane,String> capacity = new TableColumn<>("Capacity");
	        capacity.setMinWidth(75);
	        capacity.setMaxWidth(75);
	        capacity.setCellValueFactory(
	                new PropertyValueFactory<>("capacity"));

        TableColumn <Plane,String> speed = new TableColumn<>("Speed");
	        speed.setMinWidth(50);
	        speed.setMaxWidth(50);
	        speed.setCellValueFactory(
	                new PropertyValueFactory<>("speed"));

        TableColumn <Plane,String> weight = new TableColumn<>("Weight");
	        weight.setMinWidth(50);
	        weight.setMaxWidth(50);
	        weight.setCellValueFactory(
	                new PropertyValueFactory<>("weight"));

        TableColumn <Plane,String> price = new TableColumn<>("Price");
	        price.setMinWidth(50);
	        price.setMaxWidth(50);
	        price.setCellValueFactory(
	                new PropertyValueFactory<>("price"));


        adminButtonUser.setOnAction((ActionEvent e) -> {
        	loginManager.showUserManageView(user);
        });
        
        adminButtonData.setOnAction((ActionEvent e) -> {
            if(tableProducts.isEditable()){
            	tableProducts.setEditable(false);
            	adminButtonData.setText("Manage Data");
            	adminAddButton.setVisible(false);
            	adminDeleteButton.setVisible(false);
            }else{
            	tableProducts.setEditable(true);
            	adminButtonData.setText("Stop Manage");
            	adminAddButton.setVisible(true);
            	adminDeleteButton.setVisible(true);
            }

            mfr.setCellFactory(TextFieldTableCell.forTableColumn());
            mfr.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            model.setCellFactory(TextFieldTableCell.forTableColumn());
            model.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            type_aircraft.setCellFactory(ChoiceBoxTableCell.forTableColumn(
            		"Glider",
                    "Balloon",
                    "Blimp/Dirigible",
                    "Fixed wing single engine",
                    "Fixed wing multi engine",
                    "Rotorcraft",
                    "Weight-shift-control",
                    "Powered Parachute",
                    "Gyroplane"
            		));
            type_aircraft.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            engine.setCellFactory(ChoiceBoxTableCell.forTableColumn(
            		"Reciprocating",
                    "Turbo-prop",
                    "Turbo-shaft",
                    "Turbo-jet",
                    "Turbo-fan",
                    "Ramjet",
                    "2 Cycle",
                    "4 Cycle",
                    "Electric",
                    "Rotary"
            		));
            engine.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            engine_nb.setCellFactory(ChoiceBoxTableCell.forTableColumn("1", "2","3","4","5","6","8","10","12"));
            engine_nb.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            capacity.setCellFactory(TextFieldTableCell.forTableColumn());
            capacity.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            speed.setCellFactory(TextFieldTableCell.forTableColumn());
            speed.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            weight.setCellFactory(ChoiceBoxTableCell.forTableColumn("CLASS 1","CLASS 2","CLASS 3","CLASS 4"));
            weight.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));

            price.setCellFactory(TextFieldTableCell.forTableColumn());
            price.setOnEditCommit(event -> edit(event.getTableColumn().getText(), model.getCellData(event.getRowValue()), event.getOldValue(), event.getNewValue()));
        });
        
        reserveButton.setOnAction((ActionEvent e) -> {
//        	FOREACH SELECTED PLANE -> GOTO USER PANIER
            //ArrayList<Plane> currentSelection = tableProducts.getSelectionModel().getSelectedItems().stream().collect(Collectors.toCollection(ArrayList::new));
            user.addPlaneToBasket(tableProducts.getSelectionModel().getSelectedItems());
        });

        
        purchasesButton.setOnAction((ActionEvent e) -> {
            loginManager.showBasketView(user);
            purchasesButton.setDisable(true);
        });
        
        submitCriterion.setOnAction((ActionEvent e) -> {
        	data.clear();
            GlobalConnector gc = new GlobalConnector();
            Connection co = gc.getCo();
            PreparedStatement pstt;
            ResultSet rs;

            int minPrice = 0, maxPrice = 100000000;
            
//            		RECUP IHM
//          String statement = typeAircraft
//          RELATION BDD ---> NOS CLASSES?

            if (minPriceInput.getText().equals("") || Integer.parseInt(minPriceInput.getText()) < 0)
                minPrice = 0;
            else if (Integer.parseInt(maxPriceInput.getText()) <= Integer.parseInt(minPriceInput.getText())
                    || Integer.parseInt(maxPriceInput.getText()) <= 0 || maxPriceInput.getText().equals(""))
                maxPrice = 100000000;
            else {
                minPrice = Integer.parseInt(minPriceInput.getText());
                maxPrice = Integer.parseInt(maxPriceInput.getText());
            }
            
            Plane userPlane = new Plane(
            		new Manufacturer(mfrInput.getText()),
            		new Model(modelInput.getText()),
                    new AircraftType(aircraftTypeInput.getValue()),
        			new Engine(engineInput.getValue()),
        			new Engine_nb(engine_nbInput.getValue()==null?0:engine_nbInput.getValue()),
        			//		RECUP IHM
        			new Capacity(0),
        			new Weight(weightInput.getValue()),
        			new Speed(speedInput.getText().equals("")?0:Integer.parseInt(speedInput.getText())),
        			new Price(minPrice, maxPrice)
        		);

            try {
                pstt = co.prepareStatement( "SELECT * FROM plane;" );
                rs = pstt.executeQuery();
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
                    compared.evaluate(userPlane);
                    //		TRAITEMENT EN FONCTION DU RETURN
                    if(compared.getMatch() > 50){
                    	data.add(compared);
                    	i++;
                    }
                }
                System.out.println(i + " résultats affichés.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        tableProducts.setItems(data);
        tableProducts.getColumns().addAll(match, mfr, model, type_aircraft, engine, engine_nb, capacity, speed, weight, price);
        
    }

    private void edit(String row, String model, String oldValue, String newValue) {
		System.out.println(row+"\n"+model+"\n"+oldValue+"\n"+newValue);
    	
	}

	public void resetButton(){
        purchasesButton.setDisable(false);
    }
}