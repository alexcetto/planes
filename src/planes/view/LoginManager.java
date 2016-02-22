package planes.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
                    getClass().getResource("xml/login.fxml")
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
                    getClass().getResource("xml/mainview.fxml")
            );
            scene.setRoot((Parent) loader.load());
            MainViewController controller =
                    loader.<MainViewController>getController();
            controller.initSessionID(this, user);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showUserManageView(User user){
    	try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("xml/datamanageview.fxml")
            );
            scene.setRoot((Parent) loader.load());
            DataManageViewController controller =
                    loader.<DataManageViewController>getController();
            controller.manageUsers(this, user);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showDataManageView(User user){
    	try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("xml/datamanageview.fxml")
            );
            scene.setRoot((Parent) loader.load());
            DataManageViewController controller =
                    loader.<DataManageViewController>getController();
            controller.manageData(this, user);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showBasketView(User user){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("xml/basketview.fxml")
            );
            Parent root = (Parent) loader.load();
            UserBasketController controller =
                    loader.<UserBasketController>getController();
            controller.initBasket(user);

            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
