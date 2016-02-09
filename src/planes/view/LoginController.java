package planes.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import planes.User.GlobalConnector;
import planes.User.User;

/**
 * Controls the view screen
 */
public class LoginController {
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;

    public void initialize() {
    }

    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = authorize();
                if (user != null) {
                    loginManager.authenticated(user);
                }
            }
        });
    }
   
    /**
     * Check authorization credentials.
     * <p>
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private User authorize() {
    	// BASE DE DONNES CONNEXION TIMEOUT
    	GlobalConnector gc = new GlobalConnector();
    	User userTest = new User(gc, user.getText(), password.getText());
    	return userTest;
    }
}
