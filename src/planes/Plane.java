package planes;

import planes.Criterion.*;

public class Plane implements Evaluable{	
	
	protected int match;
	protected Criterion criterion;
	
	public Plane(Manufacturer manufacturer, Model model, Type aircraftType, Engine engine, Engine_nb nb,
			Capacity capacity, Weight weight, Speed speed, Price price){
		this.criterion = new Criterion(manufacturer, model, aircraftType, engine, nb, capacity, weight, speed, price);
		match = 0;
	}
	
	public void set(Criteria crit){
		
	}
	
	public int evaluate(Plane userPlane){
		match = 100 - criterion.evaluate(userPlane);
		return match;
	}
	
	public String getModel(){
		return criterion.getModel();
	}
	
	public String getManufacturer(){
		return criterion.getManufacturer();
	}

	public String getType() { 
		return criterion.getType(); 
	}
	
	public String getEngine(){
		return criterion.getEngine();
	}
	
	public String getPrice(){
		return criterion.getPrice();
	}
	
	public String getSpeed(){
		return criterion.getSpeed();
	}
	
	public String getEngine_nb(){
		return criterion.getEngine_nb();
	}
	
	public String getCapacity(){
		return criterion.getCapacity();
	}
	
	public String getWeight(){
		return criterion.getWeight();
	}
	
	public int getMatch(){
		return match;
	}

	@Override
	public String toString() {
		return "Plane " + getModel() + " " + getManufacturer();
	}
	
	//		NOT STRING FORMAT
	
	public Criteria getPriceCrit(){
		return criterion.getPriceCrit();
	}
	
	public Criteria getSpeedCrit(){
		return criterion.getSpeedCrit();
	}
	
	public Criteria getEngine_nbCrit(){
		return criterion.getEngine_nbCrit();
	}
	
	public Criteria getCapacityCrit(){
		return criterion.getCapacityCrit();
	}
}
