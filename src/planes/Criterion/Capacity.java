package planes.Criterion;

import planes.Plane;

public class Capacity extends Criteria {

	private int cap;
	
	public Capacity(int value){
		this.cap = value;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Capacity userCapacity = (Capacity) userPlane.getCapacityCrit();
		if(cap==0 || userCapacity.getCap() == 0)
			return -1;
		else
			return (Math.abs(userCapacity.getCap()-cap)*100)/userCapacity.getCap();
	}
	
	public int getCap(){
		return cap;
	}

	@Override
	public String toString() {
		return Integer.toString(cap);
	}

}
