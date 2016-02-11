package planes.Criterion;

import planes.Plane;

public class Manufacturer extends Criteria {

	private String name;

	public Manufacturer(String n){
		name = n;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		if(name.equals("") || userPlane.getManufacturer().toString().equals(""))
			return -1;
		else {
			if(name.contains(userPlane.getManufacturer().toString()))
				return 0;
			else
				return Ponderation.MANUFACTURER * 100;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
