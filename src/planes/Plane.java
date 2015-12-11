package planes;

public abstract class Plane implements Evaluable{
	
	protected String name;
	protected int passagers_nb;
	protected int year;
	protected int speed;
	protected int autonomy;
	protected int engine_nb;
	protected Engine engine;
	protected Constructor constructor;
	
	public Plane(String n, int pass, int y, int s, int a, int eng, String t, String c){
		name = n;
		passagers_nb = pass;
		year = y;
		speed = s;
		autonomy = a;
		engine_nb = eng;
		engine = new Engine(t);
		constructor = new Constructor(c);
	}
}
