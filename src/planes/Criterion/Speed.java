package planes.Criterion;

public class Speed extends Criteria {

	private int speed;
	
	public Speed(int speed){
		this.speed = speed;
	}
	
	@Override
	public int evaluate(Criterion userCriterion) {
		Speed userSpeed = (Speed) userCriterion.getSpeed();
		return Math.abs(this.speed - userSpeed.getSpeed())*100/userSpeed.getSpeed();
	}
	
	public int getSpeed(){
		return this.speed;
	}

	@Override
	public String toString() {
		if(speed>0) return Integer.toString(speed);
		else return "-";
	}

}
