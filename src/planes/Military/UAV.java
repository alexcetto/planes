package planes.Military;

public class UAV extends Military {

	public UAV(String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor) {
		super(name, passengers_nb, year, speed, autonomy, engine_nb, type_engine, constructor);
	}

	@Override
	public int evaluate(Class<?> Class, String name, int passagers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor) {
		return 0;
	}
}
