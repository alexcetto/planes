package planes;

import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

	int sizeX = 400;
	int sizeY = 300;
	
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	Group root = new Group();
        Scene scene = new Scene(root, sizeX, sizeY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX test Comparator entertainement");
        BorderPane bp = new BorderPane();
        VBox vb = new VBox(20);
        
        bp.setPrefSize(sizeX, sizeY);
        
        Text title = new Text("Planes Comparator");
        	title.setFont(Font.font ("Verdana", 20));
        Text version = new Text("v0.01");
        TextField login = new TextField();
        PasswordField password = new PasswordField();
	        login.setMaxWidth(150);
	        password.setMaxWidth(150);
        Button coButton = new Button("Connection");
        
        bp.setTop(title);
        bp.setCenter(login);
        vb.getChildren().add(login);
        vb.getChildren().add(password);
        vb.getChildren().add(coButton);
        bp.setCenter(vb);
        bp.setBottom(version);
        
        BorderPane.setAlignment(title, Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        
        root.getChildren().add(bp);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}

//public class Main {
//	
//	static Scanner sc = new Scanner(System.in);
//	
//	public static void menuUser(){
//		
//	}
//	
//	public static int menuAdmin(){
//		int choice = 0, choice2 = 0;
//		
//		boolean main = true, second = true;
//		while(main){
//			main = false;
//			
//			System.out.println("\n\n	---	Main Admin Menu	---\n");
//			System.out.println("1 - Manage database");
//			System.out.println("2 - Manage users");
//			System.out.println("3 - Deconnection");
//			System.out.println("4 - Quit");
//			System.out.print("\nYour choice : ");
//			choice = sc.nextInt();
//			
//			switch(choice){
//				case 1:
//					System.out.println("\n\n	---	Database Admin Menu	---\n");
//					break;
//				case 2:
//					System.out.println("\n\n	---	Users Admin Menu	---\n");
//					break;
//				case 3:
//				case 4:
//					return choice;
//				default:
//					main = true;	
//			}
//		}
//		
//		choice *= 10;
//		while(second){
//			second = false;
//			System.out.println("1 - Add");
//			System.out.println("2 - Change");
//			System.out.println("3 - Delete");
//			System.out.println("4 - Back");
//			System.out.print("\nYour choice : ");
//			choice2 = sc.nextInt();
//			
//			switch(choice2){
//				case 1:
//				case 2:
//				case 3:
//					choice += choice2;
//					break;
//				case 4:
//					choice = menuAdmin();
//					break;
//				default:
//					second = true;
//			}
//		}
//		
//		return choice;
//	}
//
//	public static void main(String[] args) {
//		
//		GlobalConnector gc = new GlobalConnector();
//		boolean runMain = true, runUser = true;
//		int choice;
//		
//		while(runMain){
//			//User usr = new User(gc);
//			User usr = new User("Kyanto", "password", true);
//			if(usr!=null) runUser = true;
//			
//			while(runUser){
//				if(usr.isAdmin()){
//					choice = menuAdmin();
//					switch(choice){
//						case -1:
//							System.out.println("Error!!");
//						case 4:
//							runMain = false;
//						case 3:
//							runUser = false;
//							break;
//						default:
//					}
//				}else{
//					
//					System.out.println("Sorry User but your process is in another time");
//				}
//			}	
//		}
//		
//		sc.close();
//		System.out.println("END");
//	}
//}