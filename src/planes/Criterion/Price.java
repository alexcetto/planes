package planes.Criterion;

import planes.Plane;

public class Price extends Criteria {

	private int price;
	
	public Price(int price){
		this.price = price;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Price userPrice = (Price) userPlane.getPrice();
		if(price==0 || userPrice.getPrice()==0)
			return -1;
		else
			return Ponderation.PRICE * (Math.abs(userPrice.getPrice()-price)*100)/userPrice.getPrice();
	}
	
	public int getPrice(){
		return this.price;
	}

	@Override
	public String toString() {
		return Integer.toString(price);
	}

}
