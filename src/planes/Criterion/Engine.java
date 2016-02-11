package planes.Criterion;

import planes.Plane;

public class Engine extends Criteria {
	
	private String type;
	private int type_nb;

	public Engine(int type) {
		type_nb = type;
		switch(type_nb){
		case 1:
			this.type = "Reciprocating";
			break;
		case 2:
			this.type = "Turbo-prop";
			break;
		case 3:
			this.type = "Turbo-shaft";
			break;
		case 4:
			this.type = "Turbo-jet";
			break;
		case 5:
			this.type = "Turbo-fan";
			break;
		case 6:
			this.type = "Ramjet";
			break;
		case 7:
			this.type = "2 Cycle";
			break;
		case 8:
			this.type = "4 Cycle";
			break;
		case 10:
			this.type = "Electric";
			break;
		case 11:
			this.type = "Rotary";
			break;
		default:
			this.type = "-";
		}
	}

	@Override
	public int evaluate(Plane userPlane) {
		if(type.equals(userPlane.getEngine().toString()))
			return 0;
		else
			return Ponderation.ENGINE * 100;
	}

	@Override
	public String toString() {
		return type;
	}
}
