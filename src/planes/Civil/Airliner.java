package planes.Civil;

/*public class Airliner extends Civil {

	public Airliner(String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor) {
		super(name, passengers_nb, year, speed, autonomy, engine_nb, type_engine, constructor);
	}

	@Override
	public int evaluate(Class<?> Class, String name, int passagers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor) {
		return 0;
	}

}
*/

import planes.Others.Constructor;
import planes.Others.Engine;

public class Airliner {

    protected String name;
    protected int passagers_nb;
    protected int year;
    protected int speed;
    protected int autonomy;
    protected int engine_nb;
    protected Engine engine;
    protected Constructor constructor;

    public Airliner(String name, int passengers_nb, int year, int speed, int autonomy, int engine_nb, String type_engine, String constructor){
        this.name = name;
        this.passagers_nb = passengers_nb;
        this.year = year;
        this.speed = speed;
        this.autonomy = autonomy;
        this.engine_nb = engine_nb;
        this.engine = new Engine(type_engine);
        this.constructor = new Constructor(constructor);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassagers_nb() {
        return passagers_nb;
    }

    public void setPassagers_nb(int passagers_nb) {
        this.passagers_nb = passagers_nb;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    public int getEngine_nb() {
        return engine_nb;
    }

    public void setEngine_nb(int engine_nb) {
        this.engine_nb = engine_nb;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }
}