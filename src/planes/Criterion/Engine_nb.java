package planes.Criterion;

public class Engine_nb extends Criteria {
	
	private int nb;
	
	public Engine_nb(int nb){
		this.nb = nb;
	}

	@Override
	public int evaluate(Criterion userCriterion) {
		Engine_nb userEngine_nb = (Engine_nb) userCriterion.getEngine_nb();
		return Math.abs(this.nb - userEngine_nb.getEngine_nb())*100/userEngine_nb.getEngine_nb();
	}
	
	public int getEngine_nb(){
		return this.nb;
	}

	@Override
	public String toString() {
		return Integer.toString(nb);
	}
	
	
}
