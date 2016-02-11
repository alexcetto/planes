package planes.Military;

import planes.Criterion.*;

public class UAV extends Military {

	public UAV(Manufacturer manufacturer, Model model, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, engine, nb, capacity, weight, speed, price);
	}
	
	public String getStatement(){
		return "WHERE `type-acft`=5";
	}

}
