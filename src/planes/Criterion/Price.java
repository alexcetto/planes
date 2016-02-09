package planes.Criterion;

public class Price extends Criteria {

	private int price;
	
	public Price(int price){
		this.price = price;
	}
	
	@Override
	public int evaluate(Criterion userCriterion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return Integer.toString(price);
	}

}
