package planes.Criterion;

import java.util.Arrays;
import java.util.List;

import planes.Plane;

public class Engine extends Criteria {
	
	private static List<String> types = Arrays.asList("-", "Reciprocating", "Turbo-prop", "Turbo-shaft", "Turbo-jet", "Turbo-fan", 
			"Ramjet", "2 Cycle", "4 Cycle", "Unknown", "Electric", "Rotary");
	
	private String type;

	/**
	 * @param type Type d'Engine tel que noté dans la BDD (converti en string équivoque)
	 */
	public Engine(int type) {
		if(type<0 || type>=types.size() || type==9)
			this.type = types.get(0);
		else
			this.type = types.get(type);
	}
	
	/**
	 * @param type Type d'Engine associé aux types de la BDD
	 */
	public Engine(String type){
		if(type!=null && types.contains(type))
			this.type = type;
		else
			this.type = types.get(0);
	}

	@Override
	public int evaluate(Plane userPlane) {
		if(type.equals("-") || userPlane.getEngine().toString().equals("-"))
			return -1;
		else {
			if(type.equals(userPlane.getEngine().toString()))
				return 0;
			else
				return Ponderation.ENGINE * 100;
		}
	}

	@Override
	public String toString() {
		return type;
	}
}
