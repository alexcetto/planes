package planes;

import java.util.Scanner;

import planes.User.*;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static int menuUser(){
		return 0;
	}
	
	public static int menuAdmin(){
		int choice = 0, choice2 = 0;
		
		boolean main = true, second = true;
		while(main){
			main = false;
			
			System.out.println("\n\n	---	Main Admin Menu	---\n");
			System.out.println("1 - Manage database");
			System.out.println("2 - Manage users");
			System.out.println("3 - Deconnection");
			System.out.println("4 - Quit");
			System.out.print("\nYour choice : ");
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
					System.out.println("\n\n	---	Database Admin Menu	---\n");
					break;
				case 2:
					System.out.println("\n\n	---	Users Admin Menu	---\n");
					break;
				case 3:
				case 4:
					return choice;
				default:
					main = true;	
			}
		}
		
		choice *= 10;
		while(second){
			second = false;
			System.out.println("1 - Add");
			System.out.println("2 - Change");
			System.out.println("3 - Delete");
			System.out.println("4 - Back");
			System.out.print("\nYour choice : ");
			choice2 = sc.nextInt();
			
			switch(choice2){
				case 1:
				case 2:
				case 3:
					choice += choice2;
					break;
				case 4:
					choice = menuAdmin();
					break;
				default:
					second = true;
			}
		}
		
		return choice;
	}

	public static void main(String[] args) {
		
		GlobalConnector gc = new GlobalConnector();
		boolean runMain = true, runUser = true;
		int choice;
		
		while(runMain){
			//User usr = new User(gc);
			User usr = new User("Kyanto", "password", true);
			if(usr!=null) runUser = true;
			
			while(runUser){
				if(usr.isAdmin()){
					choice = menuAdmin();
					switch(choice){
						case -1:
							System.out.println("Error!!");
						case 4:
							runMain = false;
						case 3:
							runUser = false;
							break;
						default:
					}
				}else{
					choice = menuUser();
					System.out.println("Sorry User but your process is in another time");
				}
			}	
		}
		
		sc.close();
		System.out.println("END");
	}
}