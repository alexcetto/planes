package planes.Military;

import planes.Criterion.*;

public class Bomber extends Military {

	public Bomber(Manufacturer manufacturer, Model model, AircraftType aircraftType, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, aircraftType, engine, nb, capacity, weight, speed, price);
	}
	
	public String getStatement(){
		return "WHERE `type-acft`=7";
	}

}
