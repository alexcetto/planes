package planes.Civil;

import planes.Plane;

public abstract class Civil extends Plane{

	public Civil(String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor) {
		super(name, passengers_nb, year, speed, autonomy, engine_nb, type_engine, constructor);
	}

}
