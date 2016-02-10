package planes.Criterion;

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
	public int evaluate(Criterion userCriterion) {
		Capacity userCapacity = (Capacity) userCriterion.getCapacity();
		if(this.min_cap > userCapacity.getMin() && this.min_cap < userCapacity.getMax())
			return 0;
		else
			return 100;
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
