package planes;

import java.util.Scanner;

/**
 * Created by alexandrecetto on 12/12/2015.
 */
public class User {
    String name;
    Byte mdp;

    public User(String s, Byte b){
    	name = s;
    	mdp = b;
    }
    
    public User(){
    	Scanner sc = new Scanner(System.in);
		System.out.print("Login : ");
		String usrName = sc.next();
		
		
		
		
		sc.close();
    }
}
