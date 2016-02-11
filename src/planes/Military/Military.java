package planes.Military;

import planes.Plane;
import planes.Criterion.*;

public class Military extends Plane {

	public Military(Manufacturer manufacturer, Model model, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, engine, nb, capacity, weight, speed, price);
	}
	
	@Override
	public String getStatement(){
		return "WHERE `type-acft`=5 OR `type-acft`=6 OR `type-acft`=7 OR `type-acft`=8";
	}

}
