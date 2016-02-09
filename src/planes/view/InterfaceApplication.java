package planes.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import planes.User.GlobalConnector;

import java.io.IOException;

/**
 * Main application class for the view demo application
 */
public class InterfaceApplication extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new BorderPane());

        LoginManager loginManager = new LoginManager(scene);
        loginManager.showLoginScreen();

        stage.setScene(scene);
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.show();
    }
}