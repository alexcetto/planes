package planes.Criterion;

import java.util.HashMap;
import java.util.Map.Entry;

import planes.Evaluable;
import planes.Plane;

public class Criterion extends Criteria implements Evaluable {

	protected HashMap<String, Criteria> criterion;
	
	public Criterion(Manufacturer manufacturer, Model model, Type type, Engine engine, Engine_nb nb,
												Capacity capacity, Weight weight, Speed speed, Price price){
		criterion = new HashMap<String, Criteria>();
		criterion.put("manufacturer", manufacturer);
		criterion.put("model", model);
		criterion.put("type", type);
		criterion.put("engine", engine);
		criterion.put("engine_nb", nb);
		criterion.put("capacity", capacity);
		criterion.put("weight", weight);
		criterion.put("speed", speed);
		criterion.put("price", price);
	}

	
	/* 
	 * @param userPlane		Mod�le de comparaison cr�� par l'user
	 * @return				Pourcentage d'�cart moyen � userPlane
	 * 
	 * Appelle evaluate() pour chaque crit�re dans criterion
	 * Si le crit�re renvoie -1, il est ignor� (manque d'information)
	 */
	@Override
	public int evaluate(Plane userPlane) {
		int totalScore=0, score, index=0;
		for(Entry<String, Criteria> c : criterion.entrySet()){
			score = c.getValue().evaluate(userPlane);
			if(score!=-1){
				totalScore += score;
				index++;
			}
		}
		return index!=0 ? totalScore/index : 100;
	}
	
	public String getModel(){
		return ""+criterion.get("model");
	}
	
	public String getManufacturer(){
		return ""+criterion.get("manufacturer");
	}

	public String getType(){ 
		return ""+criterion.get("type"); 
	}
	
	public String getEngine(){
		return ""+criterion.get("engine");
	}
	
	public String getPrice(){
		return ""+criterion.get("price");
	}
	
	public String getSpeed(){
		return ""+criterion.get("speed");
	}
	
	public String getEngine_nb(){
		return ""+criterion.get("engine_nb");
	}
	
	public String getCapacity(){
		return ""+criterion.get("capacity");
	}
	
	public String getWeight(){
		return ""+criterion.get("weight");
	}
	
	//		NOT STRING FORMAT
	
	public Criteria getPriceCrit(){
		return criterion.get("price");
	}
	
	public Criteria getSpeedCrit(){
		return criterion.get("speed");
	}
	
	public Criteria getEngine_nbCrit(){
		return criterion.get("engine_nb");
	}
	
	public Criteria getCapacityCrit(){
		return criterion.get("capacity");
	}
}
