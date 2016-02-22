package planes.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    private PasswordField password;
    @FXML
    private Button loginButton;

    public void initialize() {
    }

    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(event -> {
            User user1 = authorize();
            if (user1 != null) {
                loginManager.authenticated(user1);
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
    	if(userTest.getName()==null) return null;
    	return userTest;
    }
}
