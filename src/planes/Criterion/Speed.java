package planes.Criterion;

import planes.Plane;

public class Speed extends Criteria {

	private int speed;
	
	public Speed(int speed){
		this.speed = speed;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Speed userSpeed = (Speed) userPlane.getSpeedCrit();
		if(speed==0 || userSpeed.getSpeed() == 0)
			return -1;
		else
			return Ponderation.SPEED * (Math.abs(userSpeed.getSpeed()-speed)*100)/userSpeed.getSpeed();
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public int getPoids(){
		return Ponderation.SPEED;
	}

	@Override
	public String toString() {
		if(speed>0) return Integer.toString(speed);
		else return "-";
	}

}
