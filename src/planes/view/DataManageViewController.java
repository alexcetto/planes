package planes.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import planes.User.GlobalConnector;
import planes.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManageViewController {

	@FXML BorderPane basePane;
	@FXML Button backToMain;
	@FXML Button removeSelectedButton;



	public void manageUsers(LoginManager lg, User user) {
		TableView<User> usersTable = new TableView<>();
		usersTable.setEditable(true);

		ObservableList<User> data = FXCollections.observableArrayList();

		backToMain.setOnAction((ActionEvent e) -> lg.authenticated(user));

		removeSelectedButton.setOnAction((ActionEvent e) -> {
			//ArrayList<Plane> currentSelection = tableProductsBasket.getSelectionModel().getSelectedItems().stream().collect(Collectors.toCollection(ArrayList::new));
			user.removeUser(usersTable.getSelectionModel().getSelectedItem());
			data.remove(usersTable.getSelectionModel().getSelectedItem());
		});

		TableColumn<User, String> login = new TableColumn<>("Login");
		login.setMinWidth(50);
		login.setMaxWidth(200);
		login.setCellValueFactory(new PropertyValueFactory<>("name"));
		login.setCellFactory(TextFieldTableCell.forTableColumn());
		login.setOnEditCommit(event -> user.modifyUserName(event.getOldValue(), event.getNewValue()));

		TableColumn<User, String> password = new TableColumn<>("Password");
		password.setMinWidth(50);
		password.setMaxWidth(200);
		password.setCellValueFactory(new PropertyValueFactory<>("password"));

		TableColumn<User, String> admin = new TableColumn<>("Admin");
		admin.setMinWidth(50);
		admin.setMaxWidth(200);
		admin.setCellValueFactory(new PropertyValueFactory<>("admin"));

		GlobalConnector gc = new GlobalConnector();
		Connection co = gc.getCo();
		PreparedStatement pstt;
		ResultSet rs;
		String statement = "";

		try {
			pstt = co.prepareStatement("SELECT * FROM users;");
			rs = pstt.executeQuery();
			if (rs.next()) {
				while (rs.next()) {
					data.add(new User(rs.getString("login"),
							rs.getString("password"),
							rs.getBoolean("admin"))
					);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		usersTable.setItems(data);
		usersTable.getColumns().addAll(login, password, admin);

		basePane.setCenter(usersTable);
	}



}