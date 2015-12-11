package planes;

public abstract class Plane implements Evaluable{
	
	protected String name;
	protected int passagers_nb;
	protected int year;
	protected int speed;
	protected int autonomy;
	protected int engine_nb;
	protected Engine engines[];
	
	public Plane(String n, int pass, int y, int s, int a, int eng){
		name = n;
		passagers_nb = pass;
		year = y;
		speed = s;
		autonomy = a;
		engine_nb = eng;
		engines = new Engine[engine_nb];
	}
}
