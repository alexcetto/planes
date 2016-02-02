package planes.Military;

import planes.Plane;

public abstract class Military extends Plane{

	public Military(String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor) {
		super(name, passengers_nb, year, speed, autonomy, engine_nb, type_engine, constructor);
	}

}
