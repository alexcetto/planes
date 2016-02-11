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
import planes.Civil.Airliner;
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
    @FXML private Button addProducts;
    @FXML private Button submitCriterion;
    @FXML private TextField modelInput;
    @FXML private TextField mfrInput;
    @FXML private ChoiceBox engine_nbInput;
    @FXML private TextField speedInput;
    @FXML private ChoiceBox<String> weightInput;

    
    private ObservableList<Plane> data = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public void initSessionID(final LoginManager loginManager, User user) {
        sessionLabel.setText(user.getName());
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });

        tableProducts.setEditable(true);

        TableColumn <Plane,Criteria> mfr = new TableColumn<>("Manufacturer");
        mfr.setMinWidth(100);
        mfr.setCellValueFactory(
                new PropertyValueFactory<>("manufacturer"));

        TableColumn <Plane,Criteria> model = new TableColumn<>("Model");
        model.setMinWidth(100);
        model.setCellValueFactory(
                new PropertyValueFactory<>("model"));

        TableColumn <Plane,Criteria> engine = new TableColumn<>("Engine");
        engine.setMinWidth(100);
        engine.setCellValueFactory(
                new PropertyValueFactory<>("engine"));
        
        TableColumn <Plane,Criteria> engine_nb = new TableColumn<>("Engine number");
        engine_nb.setMinWidth(100);
        engine_nb.setCellValueFactory(
                new PropertyValueFactory<>("engine_nb"));

        TableColumn <Plane,Criteria> capacity = new TableColumn<>("Capacity");
        capacity.setMinWidth(100);
        capacity.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));

        TableColumn <Plane,Criteria> weight = new TableColumn<>("Weight");
        weight.setMinWidth(100);
        weight.setCellValueFactory(
                new PropertyValueFactory<>("weight"));

        TableColumn <Plane,Criteria> speed = new TableColumn<>("Speed");
        speed.setMinWidth(100);
        speed.setCellValueFactory(
                new PropertyValueFactory<>("speed"));

        TableColumn <Plane,Criteria> price = new TableColumn<>("Price");
        price.setMinWidth(100);
        price.setCellValueFactory(
                new PropertyValueFactory<>("price"));


        addProducts.setOnAction((ActionEvent e) -> {
            GlobalConnector gc = new GlobalConnector();
            Connection co = gc.getCo();
            PreparedStatement pstt;
            ResultSet rs;

            try {
                pstt = co.prepareStatement(
                        "SELECT * FROM plane;"
                );
                rs = pstt.executeQuery();
                if(rs.next()){
                    while (rs.next()){
                        data.add(new Airliner(
                        		new Manufacturer(rs.getString("mfr")),
                        		new Model(rs.getString("model")),
                    			new Engine(rs.getInt("type-eng")),
                    			new Engine_nb(rs.getInt("no-eng")),
                    			new Capacity(rs.getInt("no-seats")),
                    			new Weight(rs.getString("ac-weight")),
                    			new Speed(rs.getInt("speed")),
                    			new Price(123456)
                    		));
                    }
                }else{
                    System.out.println("Requ�te �chou�e !");
                }
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }

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
        			new Engine(4),
        			new Engine_nb(Integer.parseInt(engine_nbInput.getSelectionModel().getSelectedItem().toString())),
        			new Capacity(500, 1000),
        			new Weight((String) weightInput.getValue()),
        			new Speed(Integer.parseInt(speedInput.getText())),
        			new Price(123456)
        		);

            try {
                pstt = co.prepareStatement( "SELECT * FROM plane " + userPlane.getStatement() + ";" );
                rs = pstt.executeQuery();
                if(rs.next()){
                	int i=0;
                    while (rs.next()){
                        Plane compared = new Plane(
                        		new Manufacturer(rs.getString("mfr")),
                        		new Model(rs.getString("model")),
                    			new Engine(rs.getInt("type-eng")),
                    			new Engine_nb(rs.getInt("no-eng")),
                    			new Capacity(rs.getInt("no-seats")),
                    			new Weight(rs.getString("ac-weight")),
                    			new Speed(rs.getInt("speed")),
                    			new Price(100000)
                    		);
                        int score = compared.evaluate(userPlane);
                        //		TRAITEMENT EN FONCTION DU RETURN
                        if(score < 50){
                        	data.add(compared);
                        	i++;
                        }
                    }
                    System.out.println(i + "r�sultats affich�s");
                }else{
                    System.out.println("Requ�te �chou�e !");
                }
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }

        });

        tableProducts.setItems(data);
        tableProducts.getColumns().addAll(model, mfr, engine, engine_nb, capacity, speed, weight, price);
        
    }
}