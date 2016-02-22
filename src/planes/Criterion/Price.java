package planes.Criterion;

import planes.Plane;

public class Price extends Criteria {

	private int price_min;
	private int price_max;
	
	public Price(int price){
		this.price_min = price;
		this.price_max = price;
	}
	
	public Price(int price_min, int price_max){
		this.price_min = price_min;
		this.price_max = price_max;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Price userPrice = (Price) userPlane.getPriceCrit();
		if(price_min==0 || userPrice.getMin()==0)
			return -1;
		else
			return Ponderation.PRICE * (Math.abs(userPrice.getMin()-price_min)*100)/userPrice.getMin();
	}
	
	public int getMin(){
		return this.price_min;
	}
	
	public int getMax(){
		return this.price_max;
	}

	@Override
	public String toString() {
		if(price_min!=price_max) return price_min+"-"+price_max;
		else return Integer.toString(price_min);
	}

}
