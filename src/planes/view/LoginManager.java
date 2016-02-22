package planes.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
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
    private MainViewController controllerMainView;

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
            controllerMainView =
                    loader.<MainViewController>getController();
            controllerMainView.initSessionID(this, user);
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


    public void showBasketView(User user){
        try {
            Stage basketStage = new Stage();
            basketStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("xml/basketview.fxml")
            );
            Parent root = (Parent) loader.load();
            UserBasketController controller =
                    loader.<UserBasketController>getController();
            controller.initBasket(user);

            Scene sc = new Scene(root);
            basketStage.setScene(sc);
            basketStage.show();
            basketStage.setOnCloseRequest(event -> controllerMainView.resetButton());
        } catch (Exception ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
