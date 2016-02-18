package planes.Civil;

import planes.Criterion.*;

public class Light extends Civil {

	public Light(Manufacturer manufacturer, Model model, AircraftType aircraftType, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, aircraftType, engine, nb, capacity, weight, speed, price);
	}
	
	public String getStatement(){
		return "WHERE `type-acft`=2";
	}

}
