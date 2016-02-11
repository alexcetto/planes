package planes.Criterion;

import planes.Plane;

public class Manufacturer extends Criteria {

	private String name;

	public Manufacturer(String n){
		name = n;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		Manufacturer mfrUser = (Manufacturer) userPlane.getManufacturer();
		if(name.contains(mfrUser.toString()))
			return 0;
		else
			return Ponderation.MANUFACTURER * 100;
	}

	@Override
	public String toString() {
		return name;
	}
}
