package planes.Criterion;

public class Manufacturer extends Criteria {

	private String name;

	public Manufacturer(String n){
		name = n;
	}
	
	@Override
	public int evaluate(Criterion userCriterion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return name;
	}
}
