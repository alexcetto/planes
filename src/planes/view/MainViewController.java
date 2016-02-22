package planes.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
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
    @FXML private ChoiceBox<String> engine_nbInput;
    @FXML private TextField speedInput;
    @FXML private ChoiceBox<String> weightInput;
    @FXML private TextField capInput;
    @FXML private ChoiceBox<String> engineInput;
    @FXML private ChoiceBox<String> aircraftTypeInput;
    @FXML private TextField maxPriceInput;
    @FXML private TextField minPriceInput;
    
	public static final int MIN_MATCH = 50;
	public static final int MIN_PRICE = 0;
	public static final int MAX_PRICE = 100000000;

    
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
	        
        TableColumn <Plane,String> type = new TableColumn<>("Aircraft Type");
	        type.setMinWidth(100);
	        type.setMaxWidth(100);
	        type.setCellValueFactory(
	        		new PropertyValueFactory<>("type"));

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
            mfr.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            model.setCellFactory(TextFieldTableCell.forTableColumn());
            model.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            type.setCellFactory(ChoiceBoxTableCell.forTableColumn(
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
            type.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

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
            engine.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            engine_nb.setCellFactory(ChoiceBoxTableCell.forTableColumn("1", "2","3","4","5","6","8","10","12"));
            engine_nb.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            capacity.setCellFactory(TextFieldTableCell.forTableColumn());
            capacity.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            speed.setCellFactory(TextFieldTableCell.forTableColumn());
            speed.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            weight.setCellFactory(ChoiceBoxTableCell.forTableColumn("CLASS 1","CLASS 2","CLASS 3","CLASS 4"));
            weight.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));

            price.setCellFactory(TextFieldTableCell.forTableColumn());
            price.setOnEditCommit(event -> edit(event, model.getCellData(event.getRowValue())));
        });
        
        adminAddButton.setOnAction((ActionEvent e) -> {
        	
        });
        
        adminDeleteButton.setOnAction((ActionEvent e) -> {
        	data.remove(tableProducts.getSelectionModel().getSelectedItem());
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
            
            int minPrice, maxPrice, userSpeed, userCap, userNumber;
            try{
            	minPrice = Integer.parseInt(minPriceInput.getText());
            	if(minPrice<MIN_PRICE)
            		minPrice = MIN_PRICE;
                try{
                	maxPrice = Integer.parseInt(maxPriceInput.getText());
                	if(maxPrice>MAX_PRICE)
                		maxPrice = MAX_PRICE;
                	else if(maxPrice < minPrice)
                		maxPrice = minPrice;
                }catch(NumberFormatException error){
                	maxPrice = MAX_PRICE;
                }
            }catch(NumberFormatException error){
            	minPrice = -1;
            	maxPrice = -1;
            }
            
            try{
            	userSpeed = Integer.parseInt(speedInput.getText());
            	if(userSpeed<0)
            		userSpeed = 0;
            }catch(NumberFormatException error){
            	userSpeed = 0;
            }
            
            try{
            	userCap = Integer.parseInt(capInput.getText());
            	if(userCap<0)
            		userCap = 0;
            }catch(NumberFormatException error){
            	userCap = 0;
            }
            
            try{
            	userNumber = Integer.parseInt(engine_nbInput.getValue());
            }catch(NumberFormatException error){
            	userNumber = 0;
            }
            
            Plane userPlane = new Plane(
            		new Manufacturer(mfrInput.getText()),
            		new Model(modelInput.getText()),
                    new Type(aircraftTypeInput.getValue()),
        			new Engine(engineInput.getValue()),
        			new Engine_nb(userNumber),
        			new Capacity(userCap),
        			new Weight(weightInput.getValue()),
        			new Speed(userSpeed),
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
                            new Type(rs.getInt("type_aircraft")),
                			new Engine(rs.getInt("type_engine")),
                			new Engine_nb(rs.getInt("number_engine")),
                			new Capacity(rs.getInt("number_seats")),
                			new Weight(rs.getString("aircraft_weight")),
                			new Speed(rs.getInt("speed")),
                			new Price(rs.getInt("price"))
                		);
                    compared.evaluate(userPlane);
                    if(compared.getMatch() > MIN_MATCH){
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
        tableProducts.getColumns().addAll(match, mfr, model, type, engine, engine_nb, capacity, speed, weight, price);
        
    }

    private void edit(CellEditEvent<Plane,String> event, String model) {
		String row = event.getTableColumn().getText();
    	String value = event.getNewValue();
    	if(row.equals("Price") || row.equals("Capacity") || row.equals("Speed")){
			try{
				Integer.parseInt(value);				
			}catch(NumberFormatException e){
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Number required");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a number or your changes will not be saved");
				alert.showAndWait();
				return;
			}
		}
    	System.out.println("Pour le modèle "+model+", on change la valeur de "+row+" en "+value);
//		//				UPDATE BDD
//		//				COORDONNER COLUMN NAMES AND BDD NAMES !!!!
//		GlobalConnector gc = new GlobalConnector();
//		Connection co = gc.getCo();
//		PreparedStatement pstt;
//		ResultSet rs;
//
//		try {
//			pstt = co.prepareStatement("UPDATE plane SET `"+row+"`='"+ value + "' WHERE `model`='"+ model + "';");
//			rs = pstt.executeQuery();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		rs = null;
//		pstt = null;
//		gc = null;
//		//				FIN UPDATE BDD
    }

	public void resetButton(){
        purchasesButton.setDisable(false);
    }
}