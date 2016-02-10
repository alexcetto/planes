package planes.Criterion;

public class Price extends Criteria {

	private int price;
	
	public Price(int price){
		this.price = price;
	}
	
	@Override
	public int evaluate(Criterion userCriterion) {
		Price userPrice = (Price) userCriterion.getPrice();
		return Math.abs(this.price - userPrice.getPrice())*100/userPrice.getPrice();
	}
	
	public int getPrice(){
		return this.price;
	}

	@Override
	public String toString() {
		return Integer.toString(price);
	}

}
