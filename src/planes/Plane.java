package planes;

import planes.Criterion.*;

public class Plane implements Evaluable{	
	
	protected int match;
	protected Criterion criterion;
	
	public Plane(Manufacturer manufacturer, Model model, AircraftType aircraftType, Engine engine, Engine_nb nb,
			Capacity capacity, Weight weight, Speed speed, Price price){
		this.criterion = new Criterion(manufacturer, model, aircraftType, engine, nb, capacity, weight, speed, price);
	}
	
	public String getStatement(){
		return "";
	}
	
	public int evaluate(Plane userPlane){
		match = 100 - criterion.evaluate(userPlane);
		return match;
	}
	
	public Criteria getModel(){
		return criterion.getModel();
	}
	
	public Criteria getManufacturer(){
		return criterion.getManufacturer();
	}

	public Criteria getAicraftType() { return criterion.getAicraftType(); }
	
	public Criteria getEngine(){
		return criterion.getEngine();
	}
	
	public Criteria getPrice(){
		return criterion.getPrice();
	}
	
	public Criteria getSpeed(){
		return criterion.getSpeed();
	}
	
	public Criteria getEngine_nb(){
		return criterion.getEngine_nb();
	}
	
	public Criteria getCapacity(){
		return criterion.getCapacity();
	}
	
	public Criteria getWeight(){
		return criterion.getWeight();
	}
	
	public int getMatch(){
		return match;
	}

	@Override
	public String toString() {
		return "Plane " + getModel() + " " + getManufacturer();
	}
}
