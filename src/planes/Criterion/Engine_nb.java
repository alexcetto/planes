package planes.Criterion;

public class Engine_nb extends Criteria {
	
	private int nb;
	
	public Engine_nb(int nb){
		this.nb = nb;
	}

	@Override
	public int evaluate(Criterion userCriterion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return Integer.toString(nb);
	}
	
	
}
