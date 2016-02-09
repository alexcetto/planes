package planes;

import planes.Criterion.*;

public abstract class Plane implements Evaluable{
	
	protected Criterion criterion;
	
	public Plane(Manufacturer manufacturer, Model model, Engine engine, Engine_nb nb,
			Capacity capacity, Weight weight, Speed speed, Price price){
		this.criterion = new Criterion(manufacturer, model, engine, nb, capacity, weight, speed, price);
	}
	
	public int evaluate(Criterion userCriterion){
		return criterion.evaluate(userCriterion);
	}
	
	public Criteria getModel(){
		return criterion.getModel();
	}
	
	public Criteria getManufacturer(){
		return criterion.getManufacturer();
	}
	
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
}
