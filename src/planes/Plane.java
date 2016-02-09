package planes;

import java.util.HashSet;

import planes.Others.*;

public abstract class Plane implements Evaluable{
	
	protected String name;
	protected int passagers_nb;
	protected int year;
	protected int speed;
	protected int autonomy;
	protected int engine_nb;
	protected Engine engine;
	protected Constructor constructor;
	
//	protected Constructor manufacturer;
//	protected String model;
//	protected Engine engine;
//	protected TYPE/CATEGORIE;
//	protected int engine_nb;
//	protected int seat_nb;
//	protected Weight weight;
//	protected Speed speed;
	
	public Plane(String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor){
		this.name = name;
		this.passagers_nb = passengers_nb;
		this.year = year;
		this.speed = speed;
		this.autonomy = autonomy;
		this.engine_nb = engine_nb;
		this.engine = new Engine(type_engine);
		this.constructor = new Constructor(constructor);
	}
	
	public int evaluate(HashSet<Class<?>> Class, String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor){
		int score=0;
		
		
		
		return score;
	}
}
