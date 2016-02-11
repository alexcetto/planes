package planes.Criterion;

import planes.Plane;

public class Weight extends Criteria {

	private String weight;
	private int cat;
	
	public Weight(String weight){
		if(weight!=null && weight.startsWith("CLASS ")){
			this.weight = weight;
			for(int i=1; i<5; i++)
				if(this.weight.indexOf(i)>0) cat = i;
		}else
			this.weight = "-";		
	}
	
	public Weight(int cat){
		this.cat = cat;
		this.weight = "CLASS " + this.cat;
	}

	@Override
	public int evaluate(Plane userPlane) {
		if(weight.equals("-") || userPlane.getWeight().toString().equals("-"))
			return -1;
		else {
			if(weight.equals(userPlane.getWeight().toString()))
				return 0;
			else
				return Ponderation.WEIGHT * 100;
		}
	}

	@Override
	public String toString() {
		return weight;
	}
	
	public int getCat(){
		return cat;
	}
	
}
