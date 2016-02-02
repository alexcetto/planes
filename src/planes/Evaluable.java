package planes;

public interface Evaluable {
//	Planes attributes
//
//	protected String name;
//	protected int passagers_nb;
//	protected int year;
//	protected int speed;
//	protected int autonomy;
//	protected int engine_nb;
//	protected Engine engine;
//	protected Constructor constructor;

	public int evaluate(Class<?> Class, String name, int passagers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor);
}
