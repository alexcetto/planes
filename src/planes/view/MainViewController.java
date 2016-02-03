package planes.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import planes.Civil.Airliner;
import planes.Civil.Civil;
import planes.Main;
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
    @FXML
    private Button logoutButton;
    @FXML
    private Label sessionLabel;
    @FXML
    private TableView<Airliner> tableProducts;
    @FXML
    private Button addProducts;
    private ObservableList<Airliner> data = FXCollections.observableArrayList();

    public void initialize() {
        data.add(new Airliner("model", 5, 2012, 1000, 1000, 4, "3", "Airbus"));

    }

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        sessionLabel.setText(sessionID);
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });

        tableProducts.setEditable(true);

        TableColumn <Airliner,String> model = new TableColumn("Model");
        model.setMinWidth(100);
        model.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn mfr = new TableColumn("Manufacturer");
        mfr.setMinWidth(100);
        mfr.setCellValueFactory(
                new PropertyValueFactory<>("constructor"));

        TableColumn engine = new TableColumn("Engine");
        engine.setMinWidth(200);
        engine.setCellValueFactory(
                new PropertyValueFactory<>("engine"));

        TableColumn number_plane = new TableColumn("Number");
        engine.setMinWidth(200);
        engine.setCellValueFactory(
                new PropertyValueFactory<>("counter"));



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
                        data.add(new Airliner(rs.getString("model"), rs.getInt("no-seats"), 2012, rs.getInt("speed"), 1000, rs.getInt("no-eng"), ""+rs.getInt("type-eng"), rs.getString("mfr")));
                    }
                }else{
                    System.out.println("Requête échouée !");
                }
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }

        });

        tableProducts.setItems(data);
        tableProducts.getColumns().addAll(model, mfr, engine, number_plane);
    }
}