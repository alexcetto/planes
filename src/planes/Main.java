package planes;

import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void menuUser(){
		
	}
	
	public static int menuAdmin(){
		
		int choice;
		
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
			default:
				return choice;	
		}
		
		choice *= 10;
		System.out.println("1 - Add");
		System.out.println("2 - Change");
		System.out.println("3 - Delete");
		System.out.println("4 - Back");
		System.out.print("\nYour choice : ");
		choice += sc.nextInt();
		
		if(choice==14 || choice==24) choice = menuAdmin();
		
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
						default:
							runUser = false;
					}
				}else{
					
					
				}
			}	
		}
		
		sc.close();
		System.out.println("END");
	}
}