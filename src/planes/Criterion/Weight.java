package planes.Criterion;

import planes.Plane;

public class Weight extends Criteria {

	private String weight;
	
	public Weight(String weight){
		this.weight = weight;
	}

	@Override
	public int evaluate(Plane userPlane) {
		return -1;
	}

	@Override
	public String toString() {
		return weight;
	}
	
}
