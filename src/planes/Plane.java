package planes;

public abstract class Plane implements Evaluable{
	
	// Test private/protected pour l'accès pour les classes filles ?
	private String name;
	private int passagers_nb;
	private int year;
	private int speed;
	private int autonomy;
	private Engine engines[];
}
