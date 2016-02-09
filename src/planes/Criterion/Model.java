package planes.Criterion;

public class Model extends Criteria {

	private String name;
	
	public Model(String name){
		this.name = name;
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
