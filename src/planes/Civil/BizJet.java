package planes.Civil;

import planes.Criterion.*;

public class BizJet extends Civil {

	public BizJet(Manufacturer manufacturer, Model model, Engine engine, Engine_nb nb, Capacity capacity, Weight weight, Speed speed,
			Price price) {
		super(manufacturer, model, engine, nb, capacity, weight, speed, price);
	}
	
	public String getStatement(){
		return "WHERE `type-acft`=3";
	}

}
