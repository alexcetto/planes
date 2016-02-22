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
		if(price_min==0 || userPrice.getMin()==-1)
			return -1;
		else{
			if(price_min >= userPrice.getMin() && price_min <= userPrice.getMax() || userPrice.getMin() == 0 && userPrice.getMax() == 0)
				return 0;
			else if(price_min < userPrice.getMin()){
				return Ponderation.PRICE * (Math.abs(userPrice.getMin()-price_min)*100)/userPrice.getMin();
			} else {
				return Ponderation.PRICE * (Math.abs(userPrice.getMax()-price_min)*100)/userPrice.getMax();
			}
		}
	}
	
	public int getMin(){
		return this.price_min;
	}
	
	public int getMax(){
		return this.price_max;
	}
	
	public int getPoids(){
		return Ponderation.PRICE;
	}

	@Override
	public String toString() {
		if(price_min!=price_max) return price_min+"-"+price_max;
		else return Integer.toString(price_min);
	}

}
