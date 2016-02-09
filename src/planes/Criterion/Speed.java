package planes.Criterion;

public class Speed extends Criteria {

	private int speed;
	
	public Speed(int speed){
		this.speed = speed;
	}
	
	@Override
	public int evaluate(Criterion userCriterion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		if(speed>0) return Integer.toString(speed);
		else return "-";
	}

}
