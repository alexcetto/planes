package planes.Criterion;

import planes.Plane;

public class Capacity extends Criteria {

	private int min_cap;
	private int max_cap;
	
	public Capacity(int min, int max){
		this.min_cap = min;
		this.max_cap = max;
	}
	
	public Capacity(int val){
		this.min_cap = 500*val;
		this.max_cap = 500*val;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Capacity userCapacity = (Capacity) userPlane.getCapacity();
		if(min_cap >= userCapacity.getMin() && min_cap <= userCapacity.getMax())
			return 0;
		else if(min_cap < userCapacity.getMin()){
			return Ponderation.CAPACITY * (Math.abs(userCapacity.getMin()-min_cap)*100)/userCapacity.getMin();
		} else {
			return Ponderation.CAPACITY * (Math.abs(userCapacity.getMax()-min_cap)*100)/userCapacity.getMax();
		}
			
	}
	
	public int getMin(){
		return this.min_cap;
	}
	
	public int getMax(){
		return this.max_cap;
	}

	@Override
	public String toString() {
		if(min_cap!=max_cap) return min_cap+"-"+max_cap;
		else return Integer.toString(min_cap);
	}

}
