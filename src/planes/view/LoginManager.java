package planes.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import planes.User.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages control flow for logins
 */
public class LoginManager {
    private Scene scene;

    public LoginManager(Scene scene) {
        this.scene = scene;
    }

    /**
     * Callback method invoked to notify that a user has been authenticated.
     * Will show the main application screen.
     */
    public void authenticated(User user) {
        showMainView(user);
    }

    /**
     * Callback method invoked to notify that a user has logged out of the main application.
     * Will show the view application screen.
     */
    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("login.fxml")
            );
            scene.setRoot((Parent) loader.load());

            LoginController controller =
                    loader.<LoginController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMainView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("mainview.fxml")
            );
            scene.setRoot((Parent) loader.load());
            MainViewController controller =
                    loader.<MainViewController>getController();
            controller.initSessionID(this, user);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
