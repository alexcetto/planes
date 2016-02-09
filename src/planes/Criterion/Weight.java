package planes.Criterion;

public class Weight extends Criteria {

	private String weight;
	
	public Weight(String weight){
		this.weight = weight;
	}

	@Override
	public int evaluate(Criterion userCriterion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return weight;
	}
	
}
