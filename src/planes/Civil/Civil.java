package planes.Civil;

import planes.Plane;
import planes.Criterion.*;

public class Civil extends Plane {

	public Civil(Manufacturer manufacturer, Model model, AircraftType aircraftType, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, aircraftType, engine, nb, capacity, weight, speed, price);
	}
	
	@Override
	public String getStatement(){
		return "WHERE `type-acft`=1 OR `type-acft`=2 OR `type-acft`=3 OR `type-acft`=4";
	}

}
