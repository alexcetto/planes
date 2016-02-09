package planes;

import planes.Criterion.*;

public abstract class Civil extends Plane {

	public Civil(Manufacturer manufacturer, Model model, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, engine, nb, capacity, weight, speed, price);
	}

}
