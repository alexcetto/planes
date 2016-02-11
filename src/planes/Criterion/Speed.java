package planes.Criterion;

import planes.Plane;

public class Speed extends Criteria {

	private int speed;
	
	public Speed(int speed){
		this.speed = speed;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Speed userSpeed = (Speed) userPlane.getSpeed();
		
		return (Math.abs(userSpeed.getSpeed()-this.speed)*100)/userSpeed.getSpeed();
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
