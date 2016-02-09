package planes.Criterion;

public class Engine extends Criteria {
	
	private String type;

	public Engine(int type) {
		switch(type){
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
	public String toString() {
		return type;
	}

	@Override
	public int evaluate(Criterion userCriterion) {
		// TODO Auto-generated method stub
		return 0;
	}
}
